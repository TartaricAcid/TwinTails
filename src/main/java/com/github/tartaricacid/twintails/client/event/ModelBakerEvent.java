package com.github.tartaricacid.twintails.client.event;

import com.github.tartaricacid.twintails.TwinTails;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TwinTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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

    private static ResourceLocation handleModelId(ResourceLocation input) {
        String namespace = input.getNamespace();
        String path = input.getPath();
        return new ResourceLocation(namespace, path.substring(MODELS.length(), path.length() - JSON.length()));
    }
}
