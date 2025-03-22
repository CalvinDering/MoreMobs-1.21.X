package de.bl4ckl1on.moremobsmod.sound;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MoreMobsMod.MOD_ID);

    public static final Supplier<SoundEvent> MONKEY_AMBIENT = registerSoundEvent("monkey_ambient");
    public static final Supplier<SoundEvent> MONKEY_DEATH = registerSoundEvent("monkey_death");
    public static final Supplier<SoundEvent> MONKEY_HURT = registerSoundEvent("monkey_hurt");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
