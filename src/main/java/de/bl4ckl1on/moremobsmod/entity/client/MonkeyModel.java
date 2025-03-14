package de.bl4ckl1on.moremobsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.MonkeyEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MonkeyModel<T extends MonkeyEntity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "monkey"), "main");

    private final ModelPart monkey;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart brow;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart rightArm;
    private final ModelPart leftArm;

    public MonkeyModel(ModelPart root) {
        this.monkey = root.getChild("monkey");
        this.body = this.monkey.getChild("body");
        this.head = this.body.getChild("head");
        this.nose = this.head.getChild("nose");
        this.brow = this.head.getChild("brow");
        this.leftEar = this.head.getChild("leftEar");
        this.rightEar = this.head.getChild("rightEar");
        this.leftLeg = this.body.getChild("leftLeg");
        this.rightLeg = this.body.getChild("rightLeg");
        this.rightArm = this.body.getChild("rightArm");
        this.leftArm = this.body.getChild("leftArm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition monkey = partdefinition.addOrReplaceChild("monkey", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = monkey.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 15).addBox(-4.5F, -18.0F, -3.0F, 9.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 11.0F, 0.8727F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(23, 0).addBox(-2.5F, 0.0F, -3.5F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -2.5F));

        PartDefinition brow = head.addOrReplaceChild("brow", CubeListBuilder.create().texOffs(30, 6).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition leftEar = head.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(37, 0).addBox(0.0F, -5.0F, 0.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -2.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

        PartDefinition rightEar = head.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(37, 0).mirror().addBox(-2.0F, -5.0F, 0.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -2.0F, 0.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, 1.0F, -3.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, 1.0F, -0.8727F, 0.0F, 0.0F));

        PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-3.0F, 1.0F, -3.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -8.0F, 1.0F, -0.8727F, 0.0F, 0.0F));

        PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(30, 8).mirror().addBox(-0.5F, -3.0F, -2.0F, 4.0F, 19.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -16.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

        PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(30, 8).addBox(-3.5F, -3.0F, -2.0F, 4.0F, 19.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -16.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(MonkeyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(MonkeyAnimations.ANIM_MONKEY_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, MonkeyAnimations.ANIM_MONKEY_IDLE, ageInTicks, 1f);

    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float) Math.PI / 180f);
        this.head.xRot = headPitch * ((float) Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        monkey.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return monkey;
    }
}
