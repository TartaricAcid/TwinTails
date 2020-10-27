package com.hea3ven.twintails.init;

import com.hea3ven.twintails.TwinTails;
import com.hea3ven.twintails.item.ItemHairBand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = TwinTails.MOD_ID)
public final class TailItems {
    @GameRegistry.ObjectHolder(TwinTails.MOD_ID + ":" + "hairBand")
    public static Item HAIRBAND;

    public static final CreativeTabs TWIN_TAILS_TAB = new CreativeTabs(TwinTails.MOD_ID) {
        @SideOnly(Side.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(HAIRBAND, 1, 1);
        }
    };

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemHairBand().setRegistryName("hairBand").setTranslationKey(TwinTails.MOD_ID + ".hair_band"));
    }
}
