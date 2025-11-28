package de.bl4ckl1on.moremobsmod.entity.custom;

import de.bl4ckl1on.moremobsmod.entity.ModEntities;
import de.bl4ckl1on.moremobsmod.entity.ai.StayWithGroupGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class FlamingoEntity extends Animal {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState standOnOneLegAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private final int idleAnimationInTicks = 20 * 4;
    private int standCooldown = 0;

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
        this.goalSelector.addGoal(4, new StayWithGroupGoal<FlamingoEntity>(this, FlamingoEntity.class, 1.0f, 6.0f));
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
        return ModEntities.FLAMINGO.get().create(level);
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

}
