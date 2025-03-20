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
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, name));
    }
}
