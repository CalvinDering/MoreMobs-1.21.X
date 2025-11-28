package de.bl4ckl1on.moremobsmod.entity;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MoreMobsMod.MOD_ID);

    public static final Supplier<EntityType<CrocodileEntity>> CROCODILE =
            ENTITY_TYPES.register("crocodile", () ->
                    EntityType.Builder.of(CrocodileEntity::new, MobCategory.MONSTER).sized(2f, 0.8f).build("crocodile"));
    public static final Supplier<EntityType<MonkeyEntity>> MONKEY =
            ENTITY_TYPES.register("monkey", () ->
                    EntityType.Builder.of(MonkeyEntity::new, MobCategory.CREATURE).sized(0.9f, 1.5f).build("monkey"));
    public static final Supplier<EntityType<FlamingoEntity>> FLAMINGO =
            ENTITY_TYPES.register("flamingo", () ->
                    EntityType.Builder.of(FlamingoEntity::new, MobCategory.CREATURE).sized(0.6f, 1.2f).build("flamingo"));
    public static final Supplier<EntityType<KangarooEntity>> KANGAROO =
            ENTITY_TYPES.register("kangaroo", () ->
                    EntityType.Builder.of(KangarooEntity::new, MobCategory.CREATURE).sized(1f, 1.8f).build("kangaroo"));
    public static final Supplier<EntityType<GiraffeEntity>> GIRAFFE =
            ENTITY_TYPES.register("giraffe", () ->
                    EntityType.Builder.of(GiraffeEntity::new, MobCategory.CREATURE).sized(1.2f, 4f).build("giraffe"));
    public static final Supplier<EntityType<PenguinEntity>> PENGUIN =
            ENTITY_TYPES.register("penguin", () ->
                    EntityType.Builder.of(PenguinEntity::new, MobCategory.CREATURE).sized(0.5f, 1f).build("penguin"));
    public static final Supplier<EntityType<EnderSerpentEntity>> ENDER_SERPENT =
            ENTITY_TYPES.register("ender_serpent", () ->
                    EntityType.Builder.of(EnderSerpentEntity::new, MobCategory.CREATURE).sized(1.5f, 0.7f).build("ender_serpent"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }


}
