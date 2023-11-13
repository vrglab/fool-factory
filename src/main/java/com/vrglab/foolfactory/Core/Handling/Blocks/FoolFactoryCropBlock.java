package com.vrglab.foolfactory.Core.Handling.Blocks;

import com.vrglab.foolfactory.Core.Database.ItemGroupDatabase;
import com.vrglab.foolfactory.Core.Handling.ItemGroups.ItemGroupLoader;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemGroup;

public abstract class FoolFactoryCropBlock extends CropBlock implements FoolFactoryBlock {
    public FoolFactoryCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemGroup getCreativeTab() {
        return ItemGroupDatabase.getInstance().GetEntry("foolfactory").getRegisteredItemGroup();
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
