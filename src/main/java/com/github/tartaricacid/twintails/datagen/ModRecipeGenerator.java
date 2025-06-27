package com.github.tartaricacid.twintails.datagen;

import com.github.tartaricacid.twintails.init.TailItems;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class ModRecipeGenerator extends RecipeProvider {
    public ModRecipeGenerator(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        registerHairband(TailItems.TWIN_TAILS_RED, Items.RED_WOOL, Tags.Items.DYES_RED, consumer);
        registerHairband(TailItems.TWIN_TAILS_BLUE, Items.BLUE_WOOL, Tags.Items.DYES_BLUE, consumer);
        registerHairband(TailItems.TWIN_TAILS_YELLOW, Items.YELLOW_WOOL, Tags.Items.DYES_YELLOW, consumer);
        registerHairband(TailItems.TWIN_TAILS_CYAN, Items.CYAN_WOOL, Tags.Items.DYES_CYAN, consumer);
        registerHairband(TailItems.TWIN_TAILS_PINK, Items.PINK_WOOL, Tags.Items.DYES_PINK, consumer);
        registerHairband(TailItems.TWIN_TAILS_BLACK, Items.BLACK_WOOL, Tags.Items.DYES_BLACK, consumer);
        registerHairband(TailItems.TWIN_TAILS_BROWN, Items.BROWN_WOOL, Tags.Items.DYES_BROWN, consumer);
    }

    private void registerHairband(RegistryObject<Item> item, Item wool, ITag<Item> dye, Consumer<IFinishedRecipe> consumer) {
        Item result = item.get();
        ResourceLocation key = ForgeRegistries.ITEMS.getKey(result);
        if (key == null) {
            return;
        }
        String name = key.getPath();

        ShapedRecipeBuilder.shaped(result)
                .pattern(" W ")
                .pattern("WSW")
                .pattern(" W ")
                .define('W', wool)
                .define('S', Items.SLIME_BALL)
                .unlockedBy("has_wool", has(wool))
                .save(consumer, name + "_shaped");

        ShapelessRecipeBuilder.shapeless(result)
                .requires(TagItem.HAIRBAND).requires(dye)
                .unlockedBy("has_wool", has(wool))
                .save(consumer, name + "_shapeless");
    }
}
