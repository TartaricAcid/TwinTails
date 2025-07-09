package com.github.tartaricacid.twintails.item;

import com.github.tartaricacid.twintails.TwinTails;
import com.github.tartaricacid.twintails.client.model.ModelTwinTails;
import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class ItemHairBand extends ArmorItem {
    public static final List<ItemHairBand> ALL_TWIN_TAILS = Lists.newArrayList();
    private final TwinTailType twinTailType;

    public ItemHairBand(TwinTailType twinTailType) {
        super(ArmorMaterials.CHAIN, Type.HELMET, new Properties().durability(0));
        this.twinTailType = twinTailType;
        ALL_TWIN_TAILS.add(this);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slotId, boolean isSelected) {
        if (world.getGameTime() % 15 == 0 && entity instanceof LivingEntity livingEntity && livingEntity.getItemBySlot(EquipmentSlot.HEAD) == stack) {
            if (twinTailType.potions() == null) {
                return;
            }
            for (Holder<MobEffect> mobEffect : twinTailType.potions()) {
                if (mobEffect == null) {
                    continue;
                }
                MobEffectInstance currentEffect = livingEntity.getEffect(mobEffect);
                if (currentEffect == null || currentEffect.getDuration() < 20) {
                    livingEntity.addEffect(new MobEffectInstance(mobEffect, 80, 0));
                }
            }
        }
    }

    @Override
    @Nullable
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return ResourceLocation.fromNamespaceAndPath(TwinTails.MOD_ID, "textures/twintails/%s.png".formatted(twinTailType.name()));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> info, TooltipFlag tooltipFlag) {
        if (twinTailType.potions() == null || twinTailType.potions().length == 0) {
            return;
        }
        info.add(CommonComponents.space());
        info.add(Component.translatable("item.modifiers.head").withStyle(ChatFormatting.GRAY));
        for (Holder<MobEffect> mobEffect : twinTailType.potions()) {
            if (mobEffect == null) {
                continue;
            }
            MutableComponent prefix = Component.literal("+ ").withStyle(ChatFormatting.BLUE);
            MutableComponent base = Component.translatable(mobEffect.value().getDescriptionId()).withStyle(ChatFormatting.BLUE);
            info.add(prefix.append(base));
        }
    }

    @SuppressWarnings("all")
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