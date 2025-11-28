package de.bl4ckl1on.moremobsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.GiraffeEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GiraffeModel<T extends GiraffeEntity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "giraffe"), "main");

    private final ModelPart giraffe;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart neck;
    private final ModelPart nose;
    private final ModelPart ears;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart leftHump;
    private final ModelPart rightHump;
    private final ModelPart legs;
    private final ModelPart leftFrontLeg;
    private final ModelPart leftFrontLowerLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart rightFrontLowerLeg;
    private final ModelPart leftBackLeg;
    private final ModelPart leftBackLowerLeg;
    private final ModelPart rightBackLeg;
    private final ModelPart rightBackLowerLeg;
    private final ModelPart tail;

    public GiraffeModel(ModelPart root) {
        this.giraffe = root.getChild("giraffe");
        this.body = this.giraffe.getChild("body");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.nose = this.head.getChild("nose");
        this.ears = this.head.getChild("ears");
        this.leftEar = this.ears.getChild("leftEar");
        this.rightEar = this.ears.getChild("rightEar");
        this.leftHump = this.ears.getChild("leftHump");
        this.rightHump = this.ears.getChild("rightHump");
        this.legs = this.body.getChild("legs");
        this.leftFrontLeg = this.legs.getChild("leftFrontLeg");
        this.leftFrontLowerLeg = this.leftFrontLeg.getChild("leftFrontLowerLeg");
        this.rightFrontLeg = this.legs.getChild("rightFrontLeg");
        this.rightFrontLowerLeg = this.rightFrontLeg.getChild("rightFrontLowerLeg");
        this.leftBackLeg = this.legs.getChild("leftBackLeg");
        this.leftBackLowerLeg = this.leftBackLeg.getChild("leftBackLowerLeg");
        this.rightBackLeg = this.legs.getChild("rightBackLeg");
        this.rightBackLowerLeg = this.rightBackLeg.getChild("rightBackLowerLeg");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition giraffe = partdefinition.addOrReplaceChild("giraffe", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = giraffe.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F, -7.0F, -8.0F, 10.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.0F, 0.0F));

        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 36).addBox(-2.0F, -21.0F, -2.5F, 4.0F, 21.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -6.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(16, 36).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(16, 46).addBox(-1.5F, 0.0F, -3.5F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -3.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 2.0F));

        PartDefinition leftEar = ears.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(12, 37).addBox(0.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.5F, -1.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition rightEar = ears.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(12, 37).mirror().addBox(-3.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 1.5F, -1.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition leftHump = ears.addOrReplaceChild("leftHump", CubeListBuilder.create().texOffs(31, 38).addBox(0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));

        PartDefinition rightHump = ears.addOrReplaceChild("rightHump", CubeListBuilder.create().texOffs(31, 38).mirror().addBox(-1.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, -0.5F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -6.0F));

        PartDefinition leftFrontLeg = legs.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(4, 0).addBox(1.0F, -0.5F, -1.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftFrontLowerLeg = leftFrontLeg.addOrReplaceChild("leftFrontLowerLeg", CubeListBuilder.create().texOffs(4, 15).addBox(1.0F, 0.5F, -1.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

        PartDefinition rightFrontLeg = legs.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(4, 0).mirror().addBox(-4.0F, -0.5F, -1.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightFrontLowerLeg = rightFrontLeg.addOrReplaceChild("rightFrontLowerLeg", CubeListBuilder.create().texOffs(4, 15).mirror().addBox(-4.0F, 0.5F, -1.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 11.0F, 0.0F));

        PartDefinition leftBackLeg = legs.addOrReplaceChild("leftBackLeg", CubeListBuilder.create().texOffs(36, 0).addBox(1.0F, -0.5F, -1.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.0F));

        PartDefinition leftBackLowerLeg = leftBackLeg.addOrReplaceChild("leftBackLowerLeg", CubeListBuilder.create().texOffs(36, 15).addBox(1.0F, 0.5F, -1.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

        PartDefinition rightBackLeg = legs.addOrReplaceChild("rightBackLeg", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(-4.0F, -0.5F, -1.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 11.0F));

        PartDefinition rightBackLowerLeg = rightBackLeg.addOrReplaceChild("rightBackLowerLeg", CubeListBuilder.create().texOffs(36, 15).mirror().addBox(-4.0F, 0.5F, -1.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 11.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(36, 36).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 8.5F, 0.3054F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }

    @Override
    public void setupAnim(GiraffeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(GiraffeAnimations.ANIM_GIRAFFE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, GiraffeAnimations.ANIM_GIRAFFE_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float) Math.PI / 180f);
        this.head.xRot = headPitch * ((float) Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        giraffe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return giraffe;
    }
}
