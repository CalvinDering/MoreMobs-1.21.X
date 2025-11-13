package de.bl4ckl1on.moremobsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.GiraffeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GiraffeRenderer extends MobRenderer<GiraffeEntity, GiraffeModel<GiraffeEntity>> {
    public GiraffeRenderer(EntityRendererProvider.Context context) {
        super(context, new GiraffeModel<>(context.bakeLayer(GiraffeModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(GiraffeEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "textures/entity/giraffe.png");
    }

    @Override
    protected float getShadowRadius(GiraffeEntity entity) {
        return super.getShadowRadius(entity) * entity.getAgeScale();
    }

    @Override
    public void render(GiraffeEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

}
