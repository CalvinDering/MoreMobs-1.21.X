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

    public static final DeferredItem<Item> CROCODILE_SPAWN_EGG = ITEMS.register("crocodile_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.CROCODILE, 0x484a30, 0x757d55, new Item.Properties()));
    public static final DeferredItem<Item> MONKEY_SPAWN_EGG = ITEMS.register("monkey_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MONKEY, 0x020403, 0x272c3a, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
