package de.bl4ckl1on.moremobsmod.entity.custom;

import de.bl4ckl1on.moremobsmod.entity.ModEntities;
import de.bl4ckl1on.moremobsmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class PenguinEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState swimAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private final int idleAnimationInTicks = 20 * 4;

    public static final int TOTAL_AIR_SUPPLY = 4800;
    public static final float LAND_MOVEMENT_SPEED = 0.2f;
    public static final float WATER_MOVEMENT_SPEED = 0.25f;

    private boolean wantsToBeInWater = true;
    private int waterTime = 0;
    private int landTime = 0;
    private static final int MAX_WATER_TIME = 60;
    private static final int MAX_LAND_TIME = 120;

    public PenguinEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.moveControl = new PenguinEntity.PenguinEntityMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.2f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2f, Ingredient.of(Items.SALMON), false));
        this.goalSelector.addGoal(3, new PenguinEntity.PenguinSwimGoal(this));
        this.goalSelector.addGoal(4, new PenguinEntity.PenguinStrollGoal(this, 0.6f));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1f));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Player.class, 4.0f, 1.2, 1.5));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Fox.class, 6.0f, 1.2, 1.5));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.STEP_HEIGHT, 1.0);
    }

    @Override
    public int getMaxAirSupply() {
        return TOTAL_AIR_SUPPLY;
    }

    protected void handleAirSupply(int airSupply) {
    }

    @Override
    protected int increaseAirSupply(int currentAir) {
        return this.getMaxAirSupply();
    }

    @Override
    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        this.handleAirSupply(i);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.SALMON);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.PENGUIN.get().create(level);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = idleAnimationInTicks;
            this.idleAnimationState.start(this.tickCount);
            this.swimAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        }

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        if(wantsToBeInWater) {
            if (this.isInWater()) {
                waterTime++;
                if(waterTime >= MAX_WATER_TIME) {
                    wantsToBeInWater = false;
                    waterTime = 0;
                }
            }
        } else {
            if (!this.isInWater()) {
                landTime++;
                if(landTime >= MAX_LAND_TIME) {
                    wantsToBeInWater = true;
                    landTime = 0;
                }
            }
        }

        /*if(this.isInWater()) {
            waterTime++;
            landTime = 0;
        } else {
            landTime++;
            waterTime = 0;
        }*/
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.PENGUIN_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.PENGUIN_DEATH.get();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return  new AmphibiousPathNavigation(this, level);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if(this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.05f, travelVector);

            Vec3 look = this.getLookAngle();
            Vec3 glideForce = new Vec3(look.x * 0.02, look.y * 0.03, look.z * 0.02);
            this.setDeltaMovement(this.getDeltaMovement().add(glideForce));

            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if(this.level().getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(travelVector);
        }
    }

    static class PenguinEntityMoveControl extends MoveControl {
        private final PenguinEntity penguin;

        PenguinEntityMoveControl(PenguinEntity penguin) {
            super(penguin);
            this.penguin = penguin;
        }

        private void updateSpeed() {
            if(this.penguin.isInWater()) {
                this.penguin.setDeltaMovement(this.penguin.getDeltaMovement().add(0.0, 0.005, 0.0));
                this.penguin.setSpeed(Math.max(this.penguin.getSpeed() / 2.0F, WATER_MOVEMENT_SPEED));
            } else if(this.penguin.onGround()) {
                this.penguin.setSpeed(Math.max(this.penguin.getSpeed() / 2.0F, LAND_MOVEMENT_SPEED));
            }
        }

        @Override
        public void tick() {
            this.updateSpeed();
            if(this.operation == Operation.MOVE_TO && !this.penguin.getNavigation().isDone()) {
                double dX = this.wantedX - this.penguin.getX();
                double dY = this.wantedY - this.penguin.getY();
                double dZ = this.wantedZ - this.penguin.getZ();
                double d = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
                if (d < 1.0E-5F) {
                    this.mob.setSpeed(0.0F);
                } else {
                    dY /= d;

                    float targetRot = (float) (Mth.atan2(dZ, dX) * 180f / (float) Math.PI) - 90.0f;
                    this.penguin.setYRot(this.rotlerp(this.penguin.getYRot(), targetRot, 90.0f));
                    this.penguin.yBodyRot = this.penguin.getYRot();

                    float f1 = (float)(this.speedModifier * this.penguin.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    this.penguin.setSpeed(Mth.lerp(0.125F, this.penguin.getSpeed(), f1));
                }
            } else {
                this.penguin.setSpeed(0.0f);
            }
        }
    }

    static class PenguinSwimGoal extends RandomSwimmingGoal {
        private final PenguinEntity penguin;

        public PenguinSwimGoal(PenguinEntity penguin) {
            super(penguin, 1.0, 10);
            this.penguin = penguin;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && penguin.wantsToBeInWater;
        }
    }

    static class PenguinStrollGoal extends RandomStrollGoal {
        private final PenguinEntity penguin;

        public PenguinStrollGoal(PenguinEntity penguin, double speedModifier) {
            super(penguin, speedModifier);
            this.penguin = penguin;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !penguin.wantsToBeInWater;
        }
    }

    static class PenguinLeaveWaterGoal extends Goal {
        private final PenguinEntity penguin;

        public PenguinLeaveWaterGoal(PenguinEntity penguin) {
            this.penguin = penguin;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return penguin.isInWater() && !penguin.wantsToBeInWater;
        }

        @Override
        public void start() {
            Vec3 landPos = DefaultRandomPos.getPosTowards(penguin, 10, 4, Vec3.atBottomCenterOf(penguin.blockPosition()), 1.0);
            if (landPos != null) {
                penguin.getNavigation().moveTo(landPos.x, landPos.y, landPos.z, PenguinEntity.LAND_MOVEMENT_SPEED);
            }
        }

        @Override
        public boolean canContinueToUse() {
            return penguin.isInWater() && !penguin.getNavigation().isDone();
        }
    }
}
