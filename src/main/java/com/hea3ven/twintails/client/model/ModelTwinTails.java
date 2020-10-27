package com.hea3ven.twintails.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelTwinTails extends ModelBiped {
    private IBakedModel bakedModel;

    public ModelTwinTails() {
    }

    public void setModel(IBakedModel bakedModel) {
        this.bakedModel = bakedModel;
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        GlStateManager.pushMatrix();
        if (entityIn.isSneaking()) {
            GlStateManager.translate(0.0F, 0.275F, 0.0F);
        }

        GlStateManager.rotate(netHeadYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(headPitch, 1.0F, 0.0F, 0.0F);

        GlStateManager.rotate(180, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180, 1.0F, 0.0F, 0.0F);

        Tessellator tess = Tessellator.getInstance();
        VertexBuffer wr = tess.getBuffer();

        wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

        for (BakedQuad b : bakedModel.getQuads(null, null, 0)) {
            wr.addVertexData(b.getVertexData());
        }

        tess.draw();

        GlStateManager.popMatrix();
    }
}
