package de.bl4ckl1on.moremobsmod.utils;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {



        public static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, name));
        }
    }

    public static class Items {

        //public static final TagKey<Item> ITEM_TAG_KEY = createTag("key");

        public static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> SPAWN_PINK_FLAMINGO_BIOMES = createTag("pink_flamingo_biomes");

        public static TagKey<Biome> createTag(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(MoreMobsMod.MOD_ID, name));
        }
    }

}
