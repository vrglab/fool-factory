package com.vrglab.foolfactory.Core.Generators;

import com.vrglab.foolfactory.Core.Database.BlockDatabase;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBlock;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryOreBlock;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void GenerateOres() {
        for (FoolFactoryBlock block: BlockDatabase.getInstance().getDatalist()) {
            if(block instanceof FoolFactoryOreBlock) {
                FoolFactoryOreBlock mapped_block = (FoolFactoryOreBlock)block;

                switch(mapped_block.Dimension()){
                    case 0:
                        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), mapped_block.GenerationFeature(), mapped_block.GetPlacedFeature().getKey().get());
                        break;
                    case 1:
                        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), mapped_block.GenerationFeature(), mapped_block.GetPlacedFeature().getKey().get());
                        break;
                    case 2:
                        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), mapped_block.GenerationFeature(), mapped_block.GetPlacedFeature().getKey().get());
                        break;
                }
            }
        }
    }
}
