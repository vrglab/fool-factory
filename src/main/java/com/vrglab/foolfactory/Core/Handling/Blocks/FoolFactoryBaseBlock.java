package com.vrglab.foolfactory.Core.Handling.Blocks;

import com.vrglab.foolfactory.Core.Handling.ItemGroups.ItemGroupsRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public abstract class FoolFactoryBaseBlock extends Block implements FoolFactoryBlock {

    public FoolFactoryBaseBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemGroup getCreativeTab() {
        return ItemGroupsRegistry.FOOLFACTORY_GROUP;
    }

    @Override
    public boolean HasItem() {
        return true;
    }

    @Override
    public boolean HasTransparentTexture() {
        return false;
    }
}
