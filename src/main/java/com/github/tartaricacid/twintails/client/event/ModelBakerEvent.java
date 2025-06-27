package com.github.tartaricacid.twintails.client.event;

import com.github.tartaricacid.twintails.TwinTails;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TwinTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelBakerEvent {
    private static final String MODELS = "models/";
    private static final String TWIN_TAILS = MODELS + "twintails";
    private static final String JSON = ".json";

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        IResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        resourceManager.listResources(TWIN_TAILS, id -> id.endsWith(JSON))
                .stream().map(ModelBakerEvent::handleModelId).forEach(ModelLoader::addSpecialModel);
    }

    private static ResourceLocation handleModelId(ResourceLocation input) {
        String namespace = input.getNamespace();
        String path = input.getPath();
        return new ResourceLocation(namespace, path.substring(MODELS.length(), path.length() - JSON.length()));
    }
}
