package com.vrglab.foolfactory.Core.Generators;

import com.vrglab.foolfactory.Core.Database.BlockDatabase;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBlock;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryOreBlock;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void GenerateOres() {
        ModInfo.LOGGER.info("Adding ore's for world gen");
        for (FoolFactoryBlock block: BlockDatabase.getInstance().getDatalist()) {
            if(block instanceof FoolFactoryOreBlock) {
                FoolFactoryOreBlock mapped_block = (FoolFactoryOreBlock)block;

                BiomeModifications.addFeature(mapped_block.PlacementContext(), mapped_block.GenerationFeature(), mapped_block.GetPlacedFeature().getKey().get());
            }
        }
    }
}
