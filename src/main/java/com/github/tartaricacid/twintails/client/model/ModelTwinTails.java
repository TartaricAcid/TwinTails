package com.github.tartaricacid.twintails.client.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class ModelTwinTails extends BipedModel<LivingEntity> {
    private final ResourceLocation modelResource;

    public ModelTwinTails(ResourceLocation modelResource) {
        super(0.5f);
        this.modelResource = modelResource;
    }

    @Override
    public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    }

    @Override
    public void renderToBuffer(MatrixStack poseStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ModelRenderer headPart = this.head;
        if (headPart == null) {
            return;
        }

        poseStack.pushPose();

        poseStack.translate(headPart.x / 16.0F, headPart.y / 16.0F, headPart.z / 16.0F);
        if (headPart.zRot != 0.0F) {
            poseStack.mulPose(Vector3f.ZP.rotation(headPart.zRot));
        }
        if (headPart.yRot != 0.0F) {
            poseStack.mulPose(Vector3f.YP.rotation(headPart.yRot));
        }
        if (headPart.xRot != 0.0F) {
            poseStack.mulPose(Vector3f.XP.rotation(headPart.xRot));
        }
        poseStack.scale(-1, -1, 1);

        RenderType renderType = Atlases.cutoutBlockSheet();
        IRenderTypeBuffer.Impl bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        IVertexBuilder vertexConsumer = ItemRenderer.getFoilBufferDirect(bufferSource, renderType, true, false);
        IBakedModel bakedModel = itemRenderer.getItemModelShaper().getModelManager().getModel(modelResource);
        itemRenderer.renderModelLists(bakedModel, ItemStack.EMPTY, packedLight, packedOverlay, poseStack, vertexConsumer);

        poseStack.popPose();
    }
}