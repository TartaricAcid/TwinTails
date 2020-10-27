package com.hea3ven.twintails.client.event;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.hea3ven.twintails.TwinTails;
import com.hea3ven.twintails.item.ItemHairBand;
import com.hea3ven.twintails.item.TwinTailType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Map;

@Mod.EventBusSubscriber(modid = TwinTails.MOD_ID, value = Side.CLIENT)
public class ModelBakerEvent {
    private static final Map<ResourceLocation, TextureAtlasSprite> TEXTURES = Maps.newHashMap();

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        for (TwinTailType type : ItemHairBand.TWIN_TAIL_TYPES) {
            String path = String.format("models/twintails_%s", type.getName());
            ResourceLocation location = new ResourceLocation(TwinTails.MOD_ID, path);
            TEXTURES.put(location, event.getMap().registerSprite(location));
        }
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        for (final TwinTailType type : ItemHairBand.TWIN_TAIL_TYPES) {
            String objPath = String.format("twintails_%s.obj", type.getName());
            IModel model = getModel(new ResourceLocation(TwinTails.MOD_ID, objPath));
            if (model instanceof OBJModel) {
                model = ((OBJModel) model).process(ImmutableMap.of("flip-v", "true"));
            }
            if (!TEXTURES.containsKey(new ResourceLocation("missingno"))) {
                TEXTURES.put(new ResourceLocation("missingno"),
                        Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite());
            }
            if (!TEXTURES.containsKey(new ResourceLocation("builtin/white"))) {
                TEXTURES.put(new ResourceLocation("builtin/white"),
                        Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite());
            }
            IBakedModel bakedModel = model.bake(model.getDefaultState(), DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL, TEXTURES::get);
            type.getModel().setModel(bakedModel);
        }
    }

    private static IModel getModel(ResourceLocation modelLoc) {
        try {
            return ModelLoaderRegistry.getModel(modelLoc);
        } catch (Exception e) {
            return ModelLoaderRegistry.getMissingModel();
        }
    }
}
