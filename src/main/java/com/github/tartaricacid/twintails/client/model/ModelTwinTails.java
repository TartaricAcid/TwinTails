package com.github.tartaricacid.twintails.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;

public class ModelTwinTails extends EntityModel<Entity> {
    private final ResourceLocation modelResource;

    private float scaleX = 1.0F;
    private float scaleY = 1.0F;
    private float scaleZ = 1.0F;
    private float rotationX = 0.0F;
    private float rotationY = 0.0F;
    private float rotationZ = 0.0F;
    private float offsetX = 0;
    private float offsetY = 0;
    private float offsetZ = 0;

    public ModelTwinTails(ResourceLocation modelResource) {
        super(RenderType::entityTranslucent);
        this.modelResource = modelResource;
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();

        poseStack.translate(offsetX / 16.0F, offsetY / 16.0F, offsetZ / 16.0F);
        if (this.rotationX != 0.0F || this.rotationY != 0.0F || this.rotationZ != 0.0F) {
            poseStack.mulPose((new Quaternionf()).rotationZYX(this.rotationZ, this.rotationY, this.rotationX));
        }
        if (this.scaleX != 1.0F || this.scaleY != 1.0F || this.scaleZ != 1.0F) {
            poseStack.scale(scaleX, scaleY, scaleZ);
        }
        poseStack.scale(-1, -1, 1);

        RenderType renderType = Sheets.cutoutBlockSheet();
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(bufferSource, renderType, true, false);
        BakedModel bakedModel = itemRenderer.getItemModelShaper().getModelManager().getModel(modelResource);
        itemRenderer.renderModelLists(bakedModel, ItemStack.EMPTY, packedLight, packedOverlay, poseStack, vertexConsumer);

        poseStack.popPose();
    }

    public void copyFrom(ModelPart headPart) {
        this.scaleX = headPart.xScale;
        this.scaleY = headPart.yScale;
        this.scaleZ = headPart.zScale;
        this.rotationX = headPart.xRot;
        this.rotationY = headPart.yRot;
        this.rotationZ = headPart.zRot;
        this.offsetX = headPart.x;
        this.offsetY = headPart.y;
        this.offsetZ = headPart.z;
    }
}