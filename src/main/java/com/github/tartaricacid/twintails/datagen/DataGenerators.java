package com.github.tartaricacid.twintails.datagen;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var registries = event.getLookupProvider();
        var vanillaPack = generator.getVanillaPack(true);
        var helper = event.getExistingFileHelper();
        var block = vanillaPack.addProvider(packOutput -> new TagBlock(packOutput, registries, helper));

        generator.addProvider(event.includeServer(), new ModRecipeGenerator(generator, registries));
        vanillaPack.addProvider(packOutput -> new TagItem(packOutput, registries, block.contentsGetter(), helper));
    }
}
