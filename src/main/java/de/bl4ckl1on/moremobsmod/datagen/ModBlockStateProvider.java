package de.bl4ckl1on.moremobsmod.datagen;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.block.ModBlocks;
import de.bl4ckl1on.moremobsmod.block.custom.BananaBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MoreMobsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        makeCustomFruit(ModBlocks.BANANA_BLOCK.get(), BananaBlock.AGE, List.of(
                new ModelFile.UncheckedModelFile(modLoc("block/banana_block_stage0")),
                new ModelFile.UncheckedModelFile(modLoc("block/banana_block_stage1")),
                new ModelFile.UncheckedModelFile(modLoc("block/banana_block_stage2"))
        ));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void makeCustomFruit(Block block, Property<Integer> property, List<ModelFile> modelFiles) {
        for (int i = 0; i < modelFiles.size(); i++) {
            ConfiguredModel[] model = new ConfiguredModel[modelFiles.size()];
            model[i] = new ConfiguredModel((modelFiles.get(i)));

            getVariantBuilder(block).partialState().with(property, i).addModels(new ConfiguredModel(modelFiles.get(i)));
        }
    }
}
