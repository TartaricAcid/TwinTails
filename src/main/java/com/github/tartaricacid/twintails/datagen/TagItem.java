package com.github.tartaricacid.twintails.datagen;

import com.github.tartaricacid.twintails.TwinTails;
import com.github.tartaricacid.twintails.init.TailItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TagItem extends ItemTagsProvider {
    public static final TagKey<Item> HAIRBAND = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TwinTails.MOD_ID, "hairband"));

    public TagItem(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                   CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, TwinTails.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
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
