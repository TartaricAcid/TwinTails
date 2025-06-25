package com.github.tartaricacid.twintails.client.event;

import com.github.tartaricacid.twintails.TwinTails;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(modid = TwinTails.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelBakerEvent {
    private static final String MODELS = "models/";
    private static final String TWIN_TAILS = MODELS + "twintails";
    private static final String JSON = ".json";

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        resourceManager.listResources(TWIN_TAILS, id -> id.getPath().endsWith(JSON))
                .keySet().stream().map(ModelBakerEvent::handleModelId).forEach(event::register);
    }

    private static ModelResourceLocation handleModelId(ResourceLocation input) {
        String namespace = input.getNamespace();
        String path = input.getPath();
        String substring = path.substring(MODELS.length(), path.length() - JSON.length());
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, substring);
        return ModelResourceLocation.standalone(resourceLocation);
    }
}
