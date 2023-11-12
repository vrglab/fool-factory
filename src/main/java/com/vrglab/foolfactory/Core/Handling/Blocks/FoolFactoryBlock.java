package com.vrglab.foolfactory.Core.Handling.Blocks;

import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public interface FoolFactoryBlock{
    ItemGroup getCreativeTab();
    boolean HasItem();
    boolean HasTransparentTexture();
}
