package com.github.tartaricacid.twintails.datagen;

import com.github.tartaricacid.twintails.TwinTails;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class TagBlock extends BlockTagsProvider {
    public TagBlock(DataGenerator gen, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, TwinTails.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
    }
}
