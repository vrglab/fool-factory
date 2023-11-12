package com.vrglab.foolfactory.Core.Handling.Blocks;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;
import java.util.function.Predicate;

public abstract class FoolFactoryOreBlock extends FoolFactoryBaseBlock{
    String ore_name;
    public FoolFactoryOreBlock(Settings settings, String ore_name) {
        super(settings);
        this.ore_name = ore_name;
    }

    public abstract List<OreFeatureConfig.Target> BlockReplacementList();
    public abstract RegistryEntry<PlacedFeature> GetPlacedFeature();
    public abstract GenerationStep.Feature GenerationFeature();
    public abstract Predicate<BiomeSelectionContext> PlacementContext();

    protected RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> GetOreVein(int size){
        return ConfiguredFeatures.register(ore_name, Feature.ORE, new OreFeatureConfig(BlockReplacementList(), size));
    }

    protected RegistryEntry<PlacedFeature> GetPlacedFeature(int vein_size, int count_of_vein_per_chunk, HeightRangePlacementModifier height_placement) {
        return PlacedFeatures.register(ore_name+"_placed", GetOreVein(vein_size), modifiersWithCount(count_of_vein_per_chunk, height_placement));
    }

    protected static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }
    protected static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }
    protected static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
