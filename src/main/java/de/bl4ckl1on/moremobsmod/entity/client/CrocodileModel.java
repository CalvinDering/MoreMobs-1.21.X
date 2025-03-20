package de.bl4ckl1on.moremobsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.CrocodileEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CrocodileModel<T extends CrocodileEntity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, "crocodile"), "main");

    private final ModelPart crocodile;
    private final ModelPart body;
    private final ModelPart shoulders;
    private final ModelPart chest;
    private final ModelPart legsFront;
    private final ModelPart legFrontLeft;
    private final ModelPart legFrontRight;
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart mouthTop;
    private final ModelPart teethTop;
    private final ModelPart mouthBottom;
    private final ModelPart teethBottom;
    private final ModelPart lowerBody;
    private final ModelPart hip;
    private final ModelPart legsBack;
    private final ModelPart legBackLeft;
    private final ModelPart legBackRight;
    private final ModelPart tail;
    private final ModelPart tailEnd;

        public CrocodileModel(ModelPart root) {
            this.crocodile = root.getChild("crocodile");
            this.body = this.crocodile.getChild("body");
            this.shoulders = this.body.getChild("shoulders");
            this.chest = this.shoulders.getChild("chest");
            this.legsFront = this.shoulders.getChild("legsFront");
            this.legFrontLeft = this.legsFront.getChild("legFrontLeft");
            this.legFrontRight = this.legsFront.getChild("legFrontRight");
            this.head = this.shoulders.getChild("head");
            this.mouth = this.head.getChild("mouth");
            this.mouthTop = this.mouth.getChild("mouthTop");
            this.teethTop = this.mouthTop.getChild("teethTop");
            this.mouthBottom = this.mouth.getChild("mouthBottom");
            this.teethBottom = this.mouthBottom.getChild("teethBottom");
            this.lowerBody = this.body.getChild("lowerBody");
            this.hip = this.lowerBody.getChild("hip");
            this.legsBack = this.lowerBody.getChild("legsBack");
            this.legBackLeft = this.legsBack.getChild("legBackLeft");
            this.legBackRight = this.legsBack.getChild("legBackRight");
            this.tail = this.lowerBody.getChild("tail");
            this.tailEnd = this.tail.getChild("tailEnd");
        }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition crocodile = partdefinition.addOrReplaceChild("crocodile", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = crocodile.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -8.0F, -5.0F, 12.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(54, 26).addBox(-4.0F, -10.0F, -2.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(54, 26).addBox(4.0F, -10.0F, -2.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition shoulders = body.addOrReplaceChild("shoulders", CubeListBuilder.create().texOffs(54, 17).addBox(4.0F, -7.0F, -11.0F, 0.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(54, 17).addBox(-4.0F, -7.0F, -11.0F, 0.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -4.0F));

        PartDefinition chest = shoulders.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 17).addBox(-7.0F, -5.0F, -11.0F, 14.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legsFront = shoulders.addOrReplaceChild("legsFront", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legFrontLeft = legsFront.addOrReplaceChild("legFrontLeft", CubeListBuilder.create().texOffs(71, 13).mirror().addBox(0.0F, -2.0F, -2.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(83, 0).mirror().addBox(0.0F, 3.9F, -5.0F, 7.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, -1.0F, -8.5F));

        PartDefinition legFrontRight = legsFront.addOrReplaceChild("legFrontRight", CubeListBuilder.create().texOffs(71, 13).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(83, 0).addBox(-7.0F, 3.9F, -5.0F, 7.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, -8.5F));

        PartDefinition head = shoulders.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 99).addBox(-4.0F, -2.25F, 0.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 111).addBox(-5.0F, -3.0F, -6.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(28, 99).addBox(-4.0F, -2.0F, -13.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(3.25F, -2.75F, -10.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.25F, -2.75F, -10.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -11.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -12.0F));

        PartDefinition mouthTop = mouth.addOrReplaceChild("mouthTop", CubeListBuilder.create().texOffs(42, 8).addBox(-3.0F, -2.0F, -5.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(44, 2).addBox(-4.0F, -2.75F, -7.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.25F, -1.0F));

        PartDefinition teethTop = mouthTop.addOrReplaceChild("teethTop", CubeListBuilder.create().texOffs(33, 4).addBox(-1.5F, -0.5F, 0.6F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(33, -5).addBox(2.85F, -0.5F, 0.6F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(33, -5).addBox(-2.85F, -0.5F, 0.6F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -7.0F));

        PartDefinition mouthBottom = mouth.addOrReplaceChild("mouthBottom", CubeListBuilder.create().texOffs(41, 16).addBox(-3.5F, 0.0F, -7.0F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.25F, -1.0F));

        PartDefinition teethBottom = mouthBottom.addOrReplaceChild("teethBottom", CubeListBuilder.create().texOffs(41, 26).addBox(-3.5F, -1.5F, 0.1F, 7.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(41, 21).addBox(3.4F, -1.5F, 0.1F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(41, 21).addBox(-3.4F, -1.5F, 0.1F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -7.0F));

        PartDefinition lowerBody = body.addOrReplaceChild("lowerBody", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 4.0F));

        PartDefinition hip = lowerBody.addOrReplaceChild("hip", CubeListBuilder.create().texOffs(0, 38).addBox(-3.0F, 0.0F, -5.5F, 14.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(54, 23).addBox(8.0F, -2.0F, -5.5F, 0.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(54, 23).addBox(0.0F, -2.0F, -5.5F, 0.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -6.0F, 5.5F));

        PartDefinition legsBack = lowerBody.addOrReplaceChild("legsBack", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 4.0F));

        PartDefinition legBackLeft = legsBack.addOrReplaceChild("legBackLeft", CubeListBuilder.create().texOffs(69, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(83, 0).mirror().addBox(0.0F, 3.9F, -6.5F, 7.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, -1.0F, 0.5F));

        PartDefinition legBackRight = legsBack.addOrReplaceChild("legBackRight", CubeListBuilder.create().texOffs(69, 0).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(83, 0).addBox(-7.0F, 3.9F, -6.5F, 7.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -1.0F, 0.5F));

        PartDefinition tail = lowerBody.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 57).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(54, 24).addBox(-4.0F, -6.0F, 0.0F, 0.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(54, 24).addBox(4.0F, -6.0F, 0.0F, 0.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 10.0F));

        PartDefinition tailEnd = tail.addOrReplaceChild("tailEnd", CubeListBuilder.create().texOffs(0, 76).addBox(-4.0F, -4.25F, 0.0F, 8.0F, 5.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(39, 21).addBox(-4.0F, -6.25F, 1.0F, 0.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(39, 21).addBox(4.0F, -6.25F, 1.0F, 0.0F, 2.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 11.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

        @Override
        public void setupAnim(CrocodileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            this.root().getAllParts().forEach(ModelPart::resetPose);
            this.applyHeadRotation(netHeadYaw, headPitch);

            this.animateWalk(CrocodileAnimations.ANIM_CROCODILE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
            this.animate(entity.idleAnimationState, CrocodileAnimations.ANIM_CROCODILE_IDLE, ageInTicks, 1f);

        }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float) Math.PI / 180f);
        this.head.xRot = headPitch * ((float) Math.PI / 180f);
    }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
            crocodile.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }

    @Override
    public ModelPart root() {
        return crocodile;
    }
}
