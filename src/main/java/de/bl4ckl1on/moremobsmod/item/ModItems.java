package de.bl4ckl1on.moremobsmod.item;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreMobsMod.MOD_ID);

    public static final DeferredItem<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
