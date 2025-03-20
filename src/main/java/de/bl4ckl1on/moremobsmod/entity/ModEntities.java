package de.bl4ckl1on.moremobsmod.entity;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.custom.CrocodileEntity;
import de.bl4ckl1on.moremobsmod.entity.custom.MonkeyEntity;
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


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }


}
