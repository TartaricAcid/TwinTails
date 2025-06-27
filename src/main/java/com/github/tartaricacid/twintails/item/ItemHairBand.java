package com.github.tartaricacid.twintails.item;

import com.github.tartaricacid.twintails.client.model.ModelTwinTails;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

import static com.github.tartaricacid.twintails.init.TailTabs.TWIN_TAILS_TAB;

public class ItemHairBand extends ArmorItem {
    private final TwinTailType twinTailType;

    public ItemHairBand(TwinTailType twinTailType) {
        super(ArmorMaterials.CHAIN, EquipmentSlot.HEAD, new Item.Properties().durability(0).tab(TWIN_TAILS_TAB));
        this.twinTailType = twinTailType;
    }

    @SuppressWarnings("all")
    @Override
    public void onArmorTick(ItemStack itemStack, Level world, Player player) {
        if (world.getGameTime() % 15 == 0) {
            for (MobEffect mobEffect : twinTailType.potions()) {
                MobEffectInstance currentEffect = player.getEffect(mobEffect);
                if (currentEffect == null || currentEffect.getDuration() < 20) {
                    player.addEffect(new MobEffectInstance(mobEffect, 80, 0));
                }
            }
        }
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot equipmentSlot, String type) {
        return "twintails:textures/twintails/%s.png".formatted(twinTailType.name());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> info, TooltipFlag isAdvanced) {
        if (twinTailType.potions().length == 0) {
            return;
        }
        info.add(TextComponent.EMPTY);
        info.add(new TranslatableComponent("item.modifiers.head").withStyle(ChatFormatting.GRAY));
        for (MobEffect mobEffect : twinTailType.potions()) {
            MutableComponent prefix = new TextComponent("+ ").withStyle(ChatFormatting.BLUE);
            MutableComponent base = new TranslatableComponent(mobEffect.getDescriptionId()).withStyle(ChatFormatting.BLUE);
            info.add(prefix.append(base));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IItemRenderProperties> clientExtensionsConsumer) {
        clientExtensionsConsumer.accept(new IItemRenderProperties() {
            private ModelTwinTails modelTwinTailsCache = null;

            @Override
            public @NotNull Model getBaseArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> originalModel) {
                if (modelTwinTailsCache == null) {
                    modelTwinTailsCache = new ModelTwinTails(twinTailType.getModelLocation());
                }
                modelTwinTailsCache.copyFrom(originalModel.head);
                return modelTwinTailsCache;
            }
        });
    }
}