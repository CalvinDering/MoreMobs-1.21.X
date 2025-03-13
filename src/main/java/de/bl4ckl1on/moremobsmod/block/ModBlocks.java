package de.bl4ckl1on.moremobsmod.block;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MoreMobsMod.MOD_ID);


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
