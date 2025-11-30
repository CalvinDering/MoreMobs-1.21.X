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

    public static final Supplier<SoundEvent> CROCODILE_DEATH = registerSoundEvent("crocodile_death");
    public static final Supplier<SoundEvent> CROCODILE_HURT = registerSoundEvent("crocodile_hurt");

    public static final Supplier<SoundEvent> FLAMINGO_AMBIENT = registerSoundEvent("flamingo_ambient");
    public static final Supplier<SoundEvent> FLAMINGO_DEATH = registerSoundEvent("flamingo_death");
    public static final Supplier<SoundEvent> FLAMINGO_HURT = registerSoundEvent("flamingo_hurt");

    public static final Supplier<SoundEvent> GIRAFFE_DEATH = registerSoundEvent("giraffe_death");
    public static final Supplier<SoundEvent> GIRAFFE_HURT = registerSoundEvent("giraffe_hurt");

    public static final Supplier<SoundEvent> KANGAROO_DEATH = registerSoundEvent("kangaroo_death");
    public static final Supplier<SoundEvent> KANGAROO_HURT = registerSoundEvent("kangaroo_hurt");

    public static final Supplier<SoundEvent> PENGUIN_DEATH = registerSoundEvent("penguin_death");
    public static final Supplier<SoundEvent> PENGUIN_HURT = registerSoundEvent("penguin_hurt");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
