package de.bl4ckl1on.moremobsmod.entity.custom;

import de.bl4ckl1on.moremobsmod.utils.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;

import java.util.Arrays;
import java.util.Comparator;

public enum FlamingoVariant {
    PINK(0),
    RED(1),
    WHITE(2),
    GOLD(3);

    private static final FlamingoVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(FlamingoVariant::getId)).toArray(FlamingoVariant[]::new);
    private final int id;

    FlamingoVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static FlamingoVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

    public static FlamingoVariant byBiome(Holder<Biome> biome) {
        return biome.is(ModTags.Biomes.SPAWN_PINK_FLAMINGO_BIOMES) ? PINK : RED;
    }
}
