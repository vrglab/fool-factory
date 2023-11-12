package com.vrglab.foolfactory.Core.Handling.Blocks;

import com.vrglab.foolfactory.Core.Handling.ItemGroups.ItemGroupsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemGroup;

public abstract class FoolFactoryCropBlock extends CropBlock implements FoolFactoryBlock {
    public FoolFactoryCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemGroup getCreativeTab() {
        return ItemGroupsRegistry.FOOLFACTORY_GROUP;
    }

    @Override
    public boolean HasItem() {
        return false;
    }

    @Override
    public boolean HasTransparentTexture() {
        return false;
    }
}
