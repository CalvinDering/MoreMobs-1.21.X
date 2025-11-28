package de.bl4ckl1on.moremobsmod.worldgen;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> SPAWN_CROCODILE = registerKey("spawn_crocodile");
    public static final ResourceKey<BiomeModifier> SPAWN_CROCODILE_BEACH = registerKey("spawn_crocodile_beach");
    public static final ResourceKey<BiomeModifier> SPAWN_MONKEY = registerKey("spawn_monkey");
    public static final ResourceKey<BiomeModifier> SPAWN_FLAMINGO = registerKey("spawn_flamingo");
    public static final ResourceKey<BiomeModifier> SPAWN_GIRAFFE = registerKey("spawn_giraffe");
    public static final ResourceKey<BiomeModifier> SPAWN_KANGAROO = registerKey("spawn_kangaroo");
    public static final ResourceKey<BiomeModifier> SPAWN_PENGUIN = registerKey("spawn_penguin");
    public static final ResourceKey<BiomeModifier> SPAWN_ENDER_SERPENT = registerKey("spawn_ender_serpent");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var biomes = context.lookup(Registries.BIOME);

        context.register(SPAWN_CROCODILE, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.CROCODILE.get(), 20, 2, 4))));
        context.register(SPAWN_CROCODILE_BEACH, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BEACH)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.CROCODILE.get(), 5, 2, 4))));

        context.register(SPAWN_MONKEY, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.SPARSE_JUNGLE), biomes.getOrThrow(Biomes.SAVANNA_PLATEAU)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MONKEY.get(), 20, 2, 4))));
        context.register(SPAWN_FLAMINGO, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BEACH), biomes.getOrThrow(Biomes.RIVER)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.FLAMINGO.get(), 5, 5, 8))));
        context.register(SPAWN_GIRAFFE, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SAVANNA), biomes.getOrThrow(Biomes.SAVANNA_PLATEAU)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GIRAFFE.get(), 10, 2, 5))));
        context.register(SPAWN_KANGAROO, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SAVANNA), biomes.getOrThrow(Biomes.SAVANNA_PLATEAU)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.KANGAROO.get(), 10, 1, 3))));
        context.register(SPAWN_PENGUIN, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FROZEN_OCEAN), biomes.getOrThrow(Biomes.FROZEN_PEAKS), biomes.getOrThrow(Biomes.ICE_SPIKES),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS), biomes.getOrThrow(Biomes.SNOWY_SLOPES)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.PENGUIN.get(), 20, 4, 8))));

        context.register(SPAWN_ENDER_SERPENT, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.END_MIDLANDS), biomes.getOrThrow(Biomes.SMALL_END_ISLANDS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ENDER_SERPENT.get(), 20, 2, 5))));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, name));
    }
}
