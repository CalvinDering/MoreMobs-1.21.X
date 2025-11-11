package de.bl4ckl1on.moremobsmod.datagen;

import de.bl4ckl1on.moremobsmod.block.ModBlocks;
import de.bl4ckl1on.moremobsmod.block.custom.BananaBlock;
import de.bl4ckl1on.moremobsmod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder lootItemConditionBuilder_Banana = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.BANANA_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BananaBlock.AGE, 2));

        this.add(ModBlocks.BANANA_BLOCK.get(), createPlantDrops(ModBlocks.BANANA_BLOCK.get(), ModItems.BANANA.get(), lootItemConditionBuilder_Banana));
    }

    protected LootTable.Builder createPlantDrops(Block block, Item item, LootItemCondition.Builder dropCondition) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .when(dropCondition)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
