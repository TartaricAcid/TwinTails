package com.github.tartaricacid.twintails.datagen;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var helper = event.getExistingFileHelper();
        var block = new TagBlock(generator, helper);

        generator.addProvider(event.includeServer(), new ModRecipeGenerator(generator));
        generator.addProvider(event.includeServer(), new TagItem(generator, block, helper));
    }
}
