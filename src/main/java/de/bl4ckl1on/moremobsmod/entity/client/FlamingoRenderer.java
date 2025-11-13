package de.bl4ckl1on.moremobsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.FlamingoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FlamingoRenderer extends MobRenderer<FlamingoEntity, FlamingoModel<FlamingoEntity>> {
    public FlamingoRenderer(EntityRendererProvider.Context context) {
        super(context, new FlamingoModel<>(context.bakeLayer(FlamingoModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(FlamingoEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "textures/entity/flamingo.png");
    }

    @Override
    protected float getShadowRadius(FlamingoEntity entity) {
        return super.getShadowRadius(entity) * entity.getAgeScale();
    }

    @Override
    public void render(FlamingoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

}
