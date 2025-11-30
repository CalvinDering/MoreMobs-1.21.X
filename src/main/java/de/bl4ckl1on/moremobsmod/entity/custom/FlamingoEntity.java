package de.bl4ckl1on.moremobsmod.entity.custom;

import de.bl4ckl1on.moremobsmod.entity.ModEntities;
import de.bl4ckl1on.moremobsmod.entity.ai.StayWithGroupGoal;
import de.bl4ckl1on.moremobsmod.sound.ModSounds;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;

public class FlamingoEntity extends Animal {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState standOnOneLegAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private final int idleAnimationInTicks = 20 * 4;
    private int standCooldown = 0;

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(FlamingoEntity.class, EntityDataSerializers.INT);

    public FlamingoEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0f, Ingredient.of(Items.COD, Items.SALMON), false));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0f, 240));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new StayWithGroupGoal(this, FlamingoEntity.class, 1.0f, 6.0f));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.COD);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        FlamingoEntity baby = ModEntities.FLAMINGO.get().create(level);
        if(baby == null) {
            return null;
        }

        FlamingoVariant parent1 = this.getVariant();
        FlamingoVariant parent2 = ((FlamingoEntity) otherParent).getVariant();

        // Both gold
        if(parent1 == FlamingoVariant.GOLD && parent2 == FlamingoVariant.GOLD) {
            baby.setVariant(FlamingoVariant.GOLD);
            return baby;
        }

        // One is gold
        if(parent1 == FlamingoVariant.GOLD) {
            baby.setVariant(parent2);
            return baby;
        }
        if(parent2 == FlamingoVariant.GOLD) {
            baby.setVariant(parent1);
            return baby;
        }

        // Special gold case
        boolean chanceGoldColor = (parent1 == FlamingoVariant.WHITE && (parent2 == FlamingoVariant.PINK || parent2 == FlamingoVariant.RED)) ||
                (parent2 == FlamingoVariant.WHITE && (parent1 == FlamingoVariant.PINK || parent1 == FlamingoVariant.RED));
        if(chanceGoldColor && this.random.nextInt(100) < 5) {
            baby.setVariant(FlamingoVariant.GOLD);
            return baby;
        }

        boolean chanceWhiteColor = ((parent1 == FlamingoVariant.PINK || parent1 == FlamingoVariant.RED) &&
                (parent2 == FlamingoVariant.PINK || parent2 == FlamingoVariant.RED));
        if(chanceWhiteColor && this.random.nextInt(100) == 0) {
            baby.setVariant(FlamingoVariant.WHITE);
            return baby;
        }

        baby.setVariant(this.random.nextBoolean() ? parent1 : parent2);
        return baby;
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = idleAnimationInTicks;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.standOnOneLegAnimationState.isStarted()) {
            if(this.getDeltaMovement().horizontalDistanceSqr() > 0.0001) {
                this.standOnOneLegAnimationState.stop();
                this.standCooldown = 100;
                return;
            }

            if(--this.standCooldown <= 0) {
                this.standOnOneLegAnimationState.stop();
                this.standCooldown = 200 + this.random.nextInt(200);
            }
            return;
        }

        if(this.standCooldown-- <= 0 && this.random.nextInt(100) == 0) {
            this.standOnOneLegAnimationState.start(this.tickCount);
            this.standCooldown = 200 + this.random.nextInt(200);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.FLAMINGO_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.FLAMINGO_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.FLAMINGO_DEATH.get();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public FlamingoVariant getVariant() {
        return FlamingoVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(FlamingoVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(VARIANT, compound.getInt("Variant"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @org.jetbrains.annotations.Nullable SpawnGroupData spawnGroupData) {

        FlamingoVariant variant;
        if(this.random.nextInt(100) == 0) {
            variant = FlamingoVariant.WHITE;
        } else {
            Holder<Biome> biomeHolder = level.getBiome(this.blockPosition());
            variant = FlamingoVariant.byBiome(biomeHolder);
        }

        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
}
