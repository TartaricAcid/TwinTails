package com.github.tartaricacid.twintails.item;

import com.github.tartaricacid.twintails.client.model.ModelTwinTails;
import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class ItemHairBand extends ArmorItem {
    public static final List<ItemHairBand> ALL_TWIN_TAILS = Lists.newArrayList();
    private final TwinTailType twinTailType;

    public ItemHairBand(TwinTailType twinTailType) {
        super(ArmorMaterials.CHAIN, Type.HELMET, new Item.Properties().durability(0));
        this.twinTailType = twinTailType;
        ALL_TWIN_TAILS.add(this);
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
        info.add(CommonComponents.space());
        info.add(Component.translatable("item.modifiers.head").withStyle(ChatFormatting.GRAY));
        for (MobEffect mobEffect : twinTailType.potions()) {
            MutableComponent prefix = Component.literal("+ ").withStyle(ChatFormatting.BLUE);
            MutableComponent base = Component.translatable(mobEffect.getDescriptionId()).withStyle(ChatFormatting.BLUE);
            info.add(prefix.append(base));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> clientExtensionsConsumer) {
        clientExtensionsConsumer.accept(new IClientItemExtensions() {
            private ModelTwinTails modelTwinTailsCache = null;

            @Override
            public @NotNull Model getGenericArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> originalModel) {
                if (modelTwinTailsCache == null) {
                    modelTwinTailsCache = new ModelTwinTails(twinTailType.getModelLocation());
                }
                modelTwinTailsCache.copyFrom(originalModel.head);
                return modelTwinTailsCache;
            }
        });
    }
}