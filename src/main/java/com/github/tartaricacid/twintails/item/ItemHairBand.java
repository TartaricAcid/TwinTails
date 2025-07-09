package com.github.tartaricacid.twintails.item;

import com.github.tartaricacid.twintails.client.model.ModelTwinTails;
import com.google.common.collect.Lists;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static com.github.tartaricacid.twintails.init.TailTabs.TWIN_TAILS_TAB;

public class ItemHairBand extends ArmorItem {
    public static final List<ItemHairBand> ALL_TWIN_TAILS = Lists.newArrayList();
    private final TwinTailType twinTailType;
    private ModelTwinTails modelTwinTailsCache = null;

    public ItemHairBand(TwinTailType twinTailType) {
        super(ArmorMaterial.CHAIN, EquipmentSlotType.HEAD, new Item.Properties().durability(0).tab(TWIN_TAILS_TAB));
        this.twinTailType = twinTailType;
        ALL_TWIN_TAILS.add(this);
    }

    @SuppressWarnings("all")
    @Override
    public void onArmorTick(ItemStack itemStack, World world, PlayerEntity player) {
        if (world.getGameTime() % 15 == 0) {
            for (Effect mobEffect : twinTailType.getPotions()) {
                EffectInstance currentEffect = player.getEffect(mobEffect);
                if (currentEffect == null || currentEffect.getDuration() < 20) {
                    player.addEffect(new EffectInstance(mobEffect, 80, 0));
                }
            }
        }
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType equipmentSlot, String type) {
        return String.format("twintails:textures/twintails/%s.png", twinTailType.getName());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> info, ITooltipFlag isAdvanced) {
        if (twinTailType.getPotions().length == 0) {
            return;
        }
        info.add(StringTextComponent.EMPTY);
        info.add(new TranslationTextComponent("item.modifiers.head").withStyle(TextFormatting.GRAY));
        for (Effect mobEffect : twinTailType.getPotions()) {
            IFormattableTextComponent prefix = new StringTextComponent("+ ").withStyle(TextFormatting.BLUE);
            IFormattableTextComponent base = new TranslationTextComponent(mobEffect.getDescriptionId()).withStyle(TextFormatting.BLUE);
            info.add(prefix.append(base));
        }
    }

    @Override
    @Nullable
    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("unchecked")
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A originalModel) {
        if (modelTwinTailsCache == null) {
            modelTwinTailsCache = new ModelTwinTails(twinTailType.getModelLocation());
        }
        //modelTwinTailsCache.copyFrom(originalModel.head);
        return (A) modelTwinTailsCache;
    }
}