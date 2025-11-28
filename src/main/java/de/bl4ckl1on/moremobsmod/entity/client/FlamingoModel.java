package de.bl4ckl1on.moremobsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.FlamingoEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FlamingoModel<T extends FlamingoEntity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "flamingo"), "main");

    private final ModelPart flamingo;
    private final ModelPart body;
    private final ModelPart wings;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart beak;
    private final ModelPart legs;
    private final ModelPart leftLeg;
    private final ModelPart leftBottomLeg;
    private final ModelPart leftFoot;
    private final ModelPart rightLeft;
    private final ModelPart rightBottomLeg;
    private final ModelPart rightFoot;
    private final ModelPart tail;

    public FlamingoModel(ModelPart root) {
        this.flamingo = root.getChild("flamingo");
        this.body = this.flamingo.getChild("body");
        this.wings = this.body.getChild("wings");
        this.leftWing = this.wings.getChild("leftWing");
        this.rightWing = this.wings.getChild("rightWing");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.beak = this.head.getChild("beak");
        this.legs = this.body.getChild("legs");
        this.leftLeg = this.legs.getChild("leftLeg");
        this.leftBottomLeg = this.leftLeg.getChild("leftBottomLeg");
        this.leftFoot = this.leftBottomLeg.getChild("leftFoot");
        this.rightLeft = this.legs.getChild("rightLeft");
        this.rightBottomLeg = this.rightLeft.getChild("rightBottomLeg");
        this.rightFoot = this.rightBottomLeg.getChild("rightFoot");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition flamingo = partdefinition.addOrReplaceChild("flamingo", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = flamingo.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -6.5F, 8.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

        PartDefinition wings = body.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 1.0F));

        PartDefinition leftWing = wings.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(4, 32).addBox(0.0F, -3.0F, -1.5F, 1.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.0F, -5.0F));

        PartDefinition rightWing = wings.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(4, 32).mirror().addBox(-1.0F, -3.0F, -1.5F, 1.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -1.0F, -5.0F));

        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 21).addBox(-2.0F, -16.0F, -2.5F, 4.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -6.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 21).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, -2.5F));

        PartDefinition beak = head.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 21).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftLeg = legs.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 48).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 0.0F));

        PartDefinition leftBottomLeg = leftLeg.addOrReplaceChild("leftBottomLeg", CubeListBuilder.create().texOffs(0, 53).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition leftFoot = leftBottomLeg.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(-1, 50).mirror().addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition rightLeft = legs.addOrReplaceChild("rightLeft", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.5F, 0.0F, 0.0F));

        PartDefinition rightBottomLeg = rightLeft.addOrReplaceChild("rightBottomLeg", CubeListBuilder.create().texOffs(0, 53).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition rightFoot = rightBottomLeg.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(-1, 50).mirror().addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 29).addBox(-2.5F, -1.75F, -1.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 6.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(FlamingoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(FlamingoAnimations.ANIM_FLAMINGO_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, FlamingoAnimations.ANIM_FLAMINGO_IDLE, ageInTicks, 1f);
        this.animate(entity.standOnOneLegAnimationState, FlamingoAnimations.ANIM_FLAMINGO_STAND, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float) Math.PI / 180f);
        this.head.xRot = headPitch * ((float) Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        flamingo.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return flamingo;
    }
}
