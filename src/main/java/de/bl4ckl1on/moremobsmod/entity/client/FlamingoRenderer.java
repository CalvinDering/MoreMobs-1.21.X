package de.bl4ckl1on.moremobsmod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.FlamingoEntity;
import de.bl4ckl1on.moremobsmod.entity.custom.FlamingoVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class FlamingoRenderer extends MobRenderer<FlamingoEntity, FlamingoModel<FlamingoEntity>> {
    private static final Map<FlamingoVariant, ResourceLocation> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(FlamingoVariant.class), map -> {
        map.put(FlamingoVariant.PINK, ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "textures/entity/flamingo_pink.png"));
        map.put(FlamingoVariant.RED, ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "textures/entity/flamingo_red.png"));
        map.put(FlamingoVariant.WHITE, ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "textures/entity/flamingo_white.png"));
        map.put(FlamingoVariant.GOLD, ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "textures/entity/flamingo_gold.png"));
    });

    public FlamingoRenderer(EntityRendererProvider.Context context) {
        super(context, new FlamingoModel<>(context.bakeLayer(FlamingoModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(FlamingoEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
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
