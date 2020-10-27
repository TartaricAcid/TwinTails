package com.hea3ven.twintails.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import static com.hea3ven.twintails.config.MainConfig.TWIN_TAILS_EFFECTS;
import static com.hea3ven.twintails.init.TailItems.TWIN_TAILS_TAB;

public class ItemHairBand extends ItemArmor {
    public static TwinTailType[] TWIN_TAIL_TYPES =
            new TwinTailType[]{new TwinTailType(0, "white", new Potion[]{}, "dyeWhite", EnumDyeColor.WHITE),
                    new TwinTailType(1, "red", new Potion[]{MobEffects.SPEED, MobEffects.JUMP_BOOST},
                            "dyeRed", EnumDyeColor.RED),
                    new TwinTailType(2, "blue",
                            new Potion[]{MobEffects.WATER_BREATHING, MobEffects.STRENGTH}, "dyeBlue",
                            EnumDyeColor.BLUE),
                    new TwinTailType(3, "yellow",
                            new Potion[]{MobEffects.RESISTANCE, MobEffects.FIRE_RESISTANCE}, "dyeYellow",
                            EnumDyeColor.YELLOW),
                    new TwinTailType(4, "cyan", new Potion[]{}, "dyeCyan", EnumDyeColor.CYAN),
                    new TwinTailType(5, "pink", new Potion[]{}, "dyePink", EnumDyeColor.PINK),
                    new TwinTailType(6, "black", new Potion[]{}, "dyeBlack", EnumDyeColor.BLACK),
                    new TwinTailType(7, "brown", new Potion[]{}, "dyeBrown", EnumDyeColor.BROWN)};

    public ItemHairBand() {
        super(ArmorMaterial.CHAIN, 0, EntityEquipmentSlot.HEAD);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(TWIN_TAILS_TAB);
    }

    private int getTypeOffset(ItemStack itemStack) {
        return getTypeOffset(itemStack.getItemDamage());
    }

    private int getTypeOffset(int meta) {
        return (meta < TWIN_TAIL_TYPES.length) ? meta : 0;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (!world.isRemote && TWIN_TAILS_EFFECTS) {
            refreshPotionsEffects(player, TWIN_TAIL_TYPES[getTypeOffset(itemStack)].getPotions());
        }
    }

    private void refreshPotionsEffects(EntityPlayer player, Potion[] potions) {
        for (Potion potion : potions) {
            PotionEffect effect = player.getActivePotionEffect(potion);
            if (effect == null || effect.getDuration() < 10) {
                player.addPotionEffect(new PotionEffect(potion, 80, 1));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack,
                                    EntityEquipmentSlot armorSlot, ModelBiped defaultModel) {
        return TWIN_TAIL_TYPES[getTypeOffset(itemStack)].getModel();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (TwinTailType twinTailType : TWIN_TAIL_TYPES) {
                items.add(new ItemStack(this, 1, twinTailType.getOrdinal()));
            }
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return getTranslationKey() + "." + TWIN_TAIL_TYPES[getTypeOffset(stack)].getName();
    }
}
