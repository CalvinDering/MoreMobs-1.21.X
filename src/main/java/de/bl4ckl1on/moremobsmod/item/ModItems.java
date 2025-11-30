package de.bl4ckl1on.moremobsmod.item;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.block.ModBlocks;
import de.bl4ckl1on.moremobsmod.block.custom.BananaBlock;
import de.bl4ckl1on.moremobsmod.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreMobsMod.MOD_ID);

    public static final DeferredItem<Item> BANANA = ITEMS.register("banana",
            () -> new ItemNameBlockItem(ModBlocks.BANANA_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> PINK_FEATHER = ITEMS.register("pink_feather",
            () -> new Item(new Item.Properties().stacksTo(32)));

    public static final DeferredItem<Item> CROCODILE_SPAWN_EGG = ITEMS.register("crocodile_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.CROCODILE, 0x484a30, 0x757d55, new Item.Properties()));
    public static final DeferredItem<Item> MONKEY_SPAWN_EGG = ITEMS.register("monkey_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MONKEY, 0x020403, 0x272c3a, new Item.Properties()));
    public static final DeferredItem<Item> FLAMINGO_SPAWN_EGG = ITEMS.register("flamingo_spawn_egg",
                () -> new DeferredSpawnEggItem(ModEntities.FLAMINGO, 0xf39ddd, 0x991977, new Item.Properties()));
    public static final DeferredItem<Item> KANGAROO_SPAWN_EGG = ITEMS.register("kangaroo_spawn_egg",
                () -> new DeferredSpawnEggItem(ModEntities.KANGAROO, 0x53290b, 0xd56314, new Item.Properties()));
    public static final DeferredItem<Item> GIRAFFE_SPAWN_EGG = ITEMS.register("giraffe_spawn_egg",
                () -> new DeferredSpawnEggItem(ModEntities.GIRAFFE, 0x975824, 0xdcc160, new Item.Properties()));
    public static final DeferredItem<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg",
                () -> new DeferredSpawnEggItem(ModEntities.PENGUIN, 0xaaa098, 0xcfc1b5, new Item.Properties()));
    public static final DeferredItem<Item> ENDER_SERPENT_SPAWN_EGG = ITEMS.register("ender_serpent_spawn_egg",
                () -> new DeferredSpawnEggItem(ModEntities.ENDER_SERPENT, 0x123a26, 0x26784f, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
