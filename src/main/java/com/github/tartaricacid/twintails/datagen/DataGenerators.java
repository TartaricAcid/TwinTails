package com.github.tartaricacid.twintails.datagen;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var helper = event.getExistingFileHelper();
        var block = new TagBlock(generator, helper);

        generator.addProvider(new ModRecipeGenerator(generator));
        generator.addProvider(new TagItem(generator, block, helper));
    }
}
