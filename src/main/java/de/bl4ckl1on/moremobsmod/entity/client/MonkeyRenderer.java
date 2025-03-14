package de.bl4ckl1on.moremobsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.MonkeyEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MonkeyRenderer extends MobRenderer<MonkeyEntity, MonkeyModel<MonkeyEntity>> {

    public MonkeyRenderer(EntityRendererProvider.Context context) {
        super(context, new MonkeyModel<>(context.bakeLayer(MonkeyModel.LAYER_LOCATION)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(MonkeyEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "textures/entity/monkey.png");
    }

    @Override
    protected float getShadowRadius(MonkeyEntity entity) {
        return super.getShadowRadius(entity) * entity.getAgeScale();
    }

    @Override
    public void render(MonkeyEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
