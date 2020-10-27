package com.hea3ven.twintails.proxy;

import com.hea3ven.twintails.init.TailItems;
import com.hea3ven.twintails.item.TwinTailType;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static com.hea3ven.twintails.item.ItemHairBand.TWIN_TAIL_TYPES;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
    }

    public void init(FMLInitializationEvent event) {
        ItemStack anyHairTie = new ItemStack(TailItems.HAIRBAND, 1, OreDictionary.WILDCARD_VALUE);

        for (TwinTailType twinTailType : TWIN_TAIL_TYPES) {
            ItemStack wool = new ItemStack(Blocks.WOOL, 1, twinTailType.getColor().getMetadata());
            ItemStack typeHairTie = new ItemStack(TailItems.HAIRBAND, 1, twinTailType.getOrdinal());
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(typeHairTie, " s ", "sbs", " s ", 's', wool, 'b', "slimeball"));
            GameRegistry.addRecipe(
                    new ShapelessOreRecipe(typeHairTie, anyHairTie, twinTailType.getRecipeIngredient()));
        }
    }
}
