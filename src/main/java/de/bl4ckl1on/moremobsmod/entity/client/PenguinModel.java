package de.bl4ckl1on.moremobsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.PenguinEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PenguinModel<T extends PenguinEntity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "penguin"), "main");

    private final ModelPart penguin;
    private final ModelPart body;
    private final ModelPart wings;
    private final ModelPart left;
    private final ModelPart leftWing;
    private final ModelPart right;
    private final ModelPart rightWing;
    private final ModelPart head;
    private final ModelPart beak;
    private final ModelPart legs;
    private final ModelPart leftLeg;
    private final ModelPart leftFoot;
    private final ModelPart rightLeft;
    private final ModelPart rightFoot;

    public PenguinModel(ModelPart root) {
        this.penguin = root.getChild("penguin");
        this.body = this.penguin.getChild("body");
        this.wings = this.body.getChild("wings");
        this.left = this.wings.getChild("left");
        this.leftWing = this.left.getChild("leftWing");
        this.right = this.wings.getChild("right");
        this.rightWing = this.right.getChild("rightWing");
        this.head = this.body.getChild("head");
        this.beak = this.head.getChild("beak");
        this.legs = this.body.getChild("legs");
        this.leftLeg = this.legs.getChild("leftLeg");
        this.leftFoot = this.leftLeg.getChild("leftFoot");
        this.rightLeft = this.legs.getChild("rightLeft");
        this.rightFoot = this.rightLeft.getChild("rightFoot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition penguin = partdefinition.addOrReplaceChild("penguin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = penguin.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -6.5F, -3.5F, 7.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.5F, -1.0F));

        PartDefinition wings = body.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 1.5F));

        PartDefinition left = wings.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 33).addBox(0.0F, -5.5F, -3.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 0.0F, 0.0F));

        PartDefinition leftWing = left.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(14, 33).addBox(0.0F, 0.0F, -2.0F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition right = wings.addOrReplaceChild("right", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(-1.0F, -5.5F, -3.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.5F, 0.0F, 0.0F));

        PartDefinition rightWing = right.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(14, 33).mirror().addBox(-1.0F, 0.0F, -2.0F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 19).addBox(-3.5F, -6.0F, -4.0F, 7.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, 0.5F));

        PartDefinition beak = head.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(22, 19).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -4.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 4.5F, 0.5F));

        PartDefinition leftLeg = legs.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 50).addBox(-1.65F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

        PartDefinition leftFoot = leftLeg.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(0, 56).mirror().addBox(-1.15F, -1.0F, -4.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.5F, 3.0F, 0.5F));

        PartDefinition rightLeft = legs.addOrReplaceChild("rightLeft", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-1.35F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

        PartDefinition rightFoot = rightLeft.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(0, 56).mirror().addBox(-1.85F, -1.0F, -4.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.5F, 3.0F, 0.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }

    @Override
    public void setupAnim(PenguinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        boolean swimming = entity.isInWater() && !entity.onGround();

        if(swimming) {
            this.animate(entity.swimAnimationState, PenguinAnimations.ANIM_PENGUIN_SWIM, ageInTicks, 1f);
        } else {
            this.animateWalk(PenguinAnimations.ANIM_PENGUIN_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
            this.animate(entity.idleAnimationState, PenguinAnimations.ANIM_PENGUIN_IDLE, ageInTicks, 1f);
        }
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float) Math.PI / 180f);
        this.head.xRot = headPitch * ((float) Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        penguin.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return penguin;
    }
}
