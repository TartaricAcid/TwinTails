package com.github.tartaricacid.twintails.datagen;

import com.github.tartaricacid.twintails.TwinTails;
import com.github.tartaricacid.twintails.init.TailItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagItem extends ItemTagsProvider {
    public static final TagKey<Item> HAIRBAND = ItemTags.create(new ResourceLocation(TwinTails.MOD_ID, "hairband"));

    public TagItem(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(gen, blockTagProvider, TwinTails.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(HAIRBAND).add(
                TailItems.TWIN_TAILS_RED.get(),
                TailItems.TWIN_TAILS_BLUE.get(),
                TailItems.TWIN_TAILS_PINK.get(),
                TailItems.TWIN_TAILS_YELLOW.get(),
                TailItems.TWIN_TAILS_CYAN.get(),
                TailItems.TWIN_TAILS_BLACK.get(),
                TailItems.TWIN_TAILS_BROWN.get()
        );
    }
}
