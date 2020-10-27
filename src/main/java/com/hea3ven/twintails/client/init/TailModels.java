package com.hea3ven.twintails.client.init;


import com.hea3ven.twintails.TwinTails;
import com.hea3ven.twintails.init.TailItems;
import com.hea3ven.twintails.item.ItemHairBand;
import com.hea3ven.twintails.item.TwinTailType;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = TwinTails.MOD_ID, value = Side.CLIENT)
public class TailModels {
    @SubscribeEvent
    public static void register(ModelRegistryEvent event) {
        ModelResourceLocation[] model = new ModelResourceLocation[ItemHairBand.TWIN_TAIL_TYPES.length];
        int i = 0;
        for (TwinTailType type : ItemHairBand.TWIN_TAIL_TYPES) {
            model[i++] = new ModelResourceLocation(new ResourceLocation(TwinTails.MOD_ID, "hairband_" + type.getName()), null);
        }
        ModelBakery.registerItemVariants(TailItems.HAIRBAND, model);
        ModelLoader.setCustomMeshDefinition(TailItems.HAIRBAND, stack -> model[MathHelper.clamp(stack.getMetadata(), 0, model.length)]);
    }
}
