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

    private float rotationX = 0.0F;
    private float rotationY = 0.0F;
    private float rotationZ = 0.0F;
    private float offsetX = 0;
    private float offsetY = 0;
    private float offsetZ = 0;

    public ModelTwinTails(ResourceLocation modelResource) {
        super(0.5f);
        this.modelResource = modelResource;
    }

    @Override
    public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    }

    @Override
    public void renderToBuffer(MatrixStack poseStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();

        poseStack.mulPose(Vector3f.ZP.rotation(rotationZ));
        poseStack.mulPose(Vector3f.YP.rotation(rotationY));
        poseStack.mulPose(Vector3f.XP.rotation(rotationX));
        poseStack.translate(offsetX, offsetY, offsetZ);
        poseStack.scale(-1, -1, 1);

        RenderType renderType = Atlases.cutoutBlockSheet();
        IRenderTypeBuffer.Impl bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        IVertexBuilder vertexConsumer = ItemRenderer.getFoilBufferDirect(bufferSource, renderType, true, false);
        IBakedModel bakedModel = itemRenderer.getItemModelShaper().getModelManager().getModel(modelResource);
        itemRenderer.renderModelLists(bakedModel, ItemStack.EMPTY, packedLight, packedOverlay, poseStack, vertexConsumer);

        poseStack.popPose();
    }

    public void copyFrom(ModelRenderer headPart) {
        this.rotationX = headPart.xRot;
        this.rotationY = headPart.yRot;
        this.rotationZ = headPart.zRot;
        this.offsetX = headPart.x;
        this.offsetY = headPart.y;
        this.offsetZ = headPart.z;
    }
}