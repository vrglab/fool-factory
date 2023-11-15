package com.vrglab.foolfactory.Core.Handling.Blocks;

import com.vrglab.foolfactory.Core.Database.ItemGroupDatabase;
import com.vrglab.foolfactory.Core.Handling.ItemGroups.ItemGroupLoader;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public abstract class FoolFactoryBaseBlock extends Block implements FoolFactoryBlock {

    public FoolFactoryBaseBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemGroup getCreativeTab() {
        return ItemGroupDatabase.getInstance().GetEntry(ModInfo.MOD_ID).getRegisteredItemGroup();
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
