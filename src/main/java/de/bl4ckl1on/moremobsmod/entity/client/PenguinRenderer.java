package de.bl4ckl1on.moremobsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.PenguinEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PenguinRenderer extends MobRenderer<PenguinEntity, PenguinModel<PenguinEntity>> {
    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel<>(context.bakeLayer(PenguinModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PenguinEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "textures/entity/penguin.png");
    }

    @Override
    protected float getShadowRadius(PenguinEntity entity) {
        return super.getShadowRadius(entity) * entity.getAgeScale();
    }

    @Override
    public void render(PenguinEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

}
