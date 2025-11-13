package de.bl4ckl1on.moremobsmod.entity.custom;

import de.bl4ckl1on.moremobsmod.entity.ModEntities;
import de.bl4ckl1on.moremobsmod.item.ModItems;
import de.bl4ckl1on.moremobsmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.UUID;

public class MonkeyEntity extends Animal {

    private static final EntityDimensions BABY_DIMENSIONS = EntityDimensions.scalable(0.9f, 1.5f);

    private static final double climbingSpeed = 0.4D;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private final int idleAnimationInTicks = 20 * 4;

    private UUID motherEntityUUID;

    public MonkeyEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        if (this.isBaby()) {
            this.goalSelector.addGoal(1, new ClingToMotherGoal(this));
        }

        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(ModItems.BANANA), false));

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(4, new MoveThroughVillageGoal(this, 1.0, false, 10, () -> true));
        this.goalSelector.addGoal(5, new OpenDoorGoal(this, false));
        this.goalSelector.addGoal(6, new ClimbTreeGoal(this));

        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        return this.isBaby() ? BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModItems.BANANA.get());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        MonkeyEntity babyMonkey = ModEntities.MONKEY.get().create(level);
        if(babyMonkey != null && otherParent instanceof MonkeyEntity) {
            babyMonkey.setMother((MonkeyEntity) otherParent);
        }

        return babyMonkey;
    }

    private void setMother(MonkeyEntity mother) {
        this.motherEntityUUID = mother.getUUID();
    }

    @Override
    public boolean onClimbable() {
        return this.horizontalCollision || this.isClimbableBlock(getFacingBlockPos());
    }

    private BlockPos getFacingBlockPos() {
        Vec3 direction = this.getLookAngle().normalize();
        Vec3 frontPosition = this.position().add(direction.x * 1.5, 0, direction.z * 1.5);
        return new BlockPos((int) frontPosition.x, (int) frontPosition.y, (int) frontPosition.z);
    }

    private boolean isClimbableBlock(BlockPos blockPos) {
        return this.level().getBlockState(blockPos).is(Blocks.VINE) ||
                this.level().getBlockState(blockPos).is(BlockTags.OVERWORLD_NATURAL_LOGS);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = idleAnimationInTicks;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    static class ClimbTreeGoal extends Goal {

        private final MonkeyEntity monkeyEntity;
        private boolean climbing;
        private BlockPos targetTreePos;

        public ClimbTreeGoal(MonkeyEntity monkeyEntity) {
            this.monkeyEntity = monkeyEntity;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if(climbing) {
                return false;
            }

            this.targetTreePos = findNearestTree(10);
            return targetTreePos != null && monkeyEntity.isClimbableBlock(this.targetTreePos);
        }

        @Override
        public void start() {
            if(this.targetTreePos != null) {
                this.monkeyEntity.getNavigation().moveTo(
                        this.targetTreePos.getX() + 0.5,
                        this.targetTreePos.getY(),
                        this.targetTreePos.getZ() + 0.5,
                        1.2);
            }
        }

        @Override
        public void tick() {
            BlockPos facingBlockPos = monkeyEntity.getFacingBlockPos();

            if(!climbing && facingBlockPos.equals(targetTreePos)) {
                climbing = true;
            }

            if(climbing) {
                BlockPos aboveBlockPos = facingBlockPos.above();
                if(!monkeyEntity.isClimbableBlock(aboveBlockPos)) {
                    stopClimbing();
                } else {
                    this.monkeyEntity.setDeltaMovement(new Vec3(0, climbingSpeed, 0));
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return climbing && monkeyEntity.isClimbableBlock(monkeyEntity.getFacingBlockPos().above());
        }

        private void stopClimbing() {
            climbing = false;
            this.monkeyEntity.setDeltaMovement(Vec3.ZERO);
        }

        private void startClimbing() {
            double dx = this.targetTreePos.getX() + 0.5 - this.monkeyEntity.getX();
            double dz = this.targetTreePos.getZ() + 0.5 - this.monkeyEntity.getZ();
            float targetYaw = (float) (Math.atan2(dz, dx) * (180 / Math.PI)) - 90;
            this.monkeyEntity.setYRot(targetYaw);
            this.monkeyEntity.yHeadRot = targetYaw;
            this.monkeyEntity.yBodyRot = targetYaw;
        }

        private BlockPos findNearestTree(int radius) {
            BlockPos monkeyPos = this.monkeyEntity.blockPosition();
            BlockPos bestPos = null;
            double closestDist = Double.MAX_VALUE;

            for(int x = -radius; x <= radius; x++) {
                for(int y = -2; y <= 2; y++) {
                    for(int z = -radius; z <= radius; z++) {
                        BlockPos checkPos = monkeyPos.offset(x, y, z);
                        if(this.monkeyEntity.isClimbableBlock(checkPos)) {
                            double dist = monkeyPos.distSqr(checkPos);
                            if(dist < closestDist) {
                                closestDist = dist;
                                bestPos = checkPos;
                            }
                        }
                    }
                }
            }
            return bestPos;
        }
    }

    public class ClingToMotherGoal extends Goal {
        private final MonkeyEntity baby;
        private MonkeyEntity mother;
        private boolean isClinging;

        public ClingToMotherGoal(MonkeyEntity baby) {
            this.baby = baby;
        }

        @Override
        public boolean canUse() {
            if(!this.baby.isBaby() ||this.baby.isLeashed() || this.baby.getMotherUUID() == null) {
                return false;
            }

            if(this.mother == null || !this.mother.isAlive()) {
                this.mother = findMother();
            }

            return this.mother != null;
        }

        @Override
        public boolean isInterruptable() {
            return !this.isClinging;
        }

        @Override
        public void start() {
            this.isClinging = false;
        }

        @Override
        public void tick() {
            if(!this.isClinging && this.mother != null) {
                if(this.baby.getBoundingBox().intersects(this.mother.getBoundingBox())) {
                    this.isClinging = true;
                    this.attachToMother();
                }
            }

            if(this.isClinging) {
                this.holdPosition();
            }
        }

        private MonkeyEntity findMother() {
            return this.baby.level().getEntitiesOfClass(MonkeyEntity.class,
                    this.baby.getBoundingBox().inflate(10.0),
                    monkey -> monkey.getUUID().equals(this.baby.getMotherUUID()))
                    .stream().findFirst().orElse(null);
        }

        private void attachToMother() {
            this.baby.setNoGravity(true);
        }

        private void holdPosition() {
            if(this.mother == null || !this.mother.isAlive()) {
                this.baby.setNoGravity(false);
                this.isClinging = false;
                return;
            }

            Vec3 motherPos = this.mother.position();
            Vec3 offset = new Vec3(0, -0.5, 0.3);
            this.baby.setPos(motherPos.add(offset));
            this.baby.setDeltaMovement(Vec3.ZERO);
        }
    }

    public UUID getMotherUUID() {
        return this.motherEntityUUID;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.MONKEY_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.MONKEY_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MONKEY_DEATH.get();
    }
}
