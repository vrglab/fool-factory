package com.vrglab.foolfactory.World.Blocks;

import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBaseBlock;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBlockMarker;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryOreBlock;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightmapPlacementModifier;

import java.util.List;
import java.util.function.Predicate;

@FoolFactoryBlockMarker(UraniumOre.BLOCK_ID)
public class UraniumOre extends FoolFactoryOreBlock {
    public static final String BLOCK_ID = "uranium_ore";

    public UraniumOre() {
        super(FabricBlockSettings.of(Material.STONE).strength(2f).requiresTool(), UraniumOre.BLOCK_ID);
    }

    @Override
    public List<OreFeatureConfig.Target> BlockReplacementList() {
        return List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, this.getDefaultState()),
                OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, this.getDefaultState()));
    }

    @Override
    public RegistryEntry<PlacedFeature> GetPlacedFeature() {
        return GetPlacedFeature(10, 9, HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80)));
    }

    @Override
    public GenerationStep.Feature GenerationFeature() {
        return GenerationStep.Feature.UNDERGROUND_ORES;
    }

    @Override
    public Predicate<BiomeSelectionContext> PlacementContext() {
        return BiomeSelectors.foundInOverworld();
    }
}
