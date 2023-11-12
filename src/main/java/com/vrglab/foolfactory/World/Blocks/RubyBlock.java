package com.vrglab.foolfactory.World.Blocks;

import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBaseBlock;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBlockMarker;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;

@FoolFactoryBlockMarker("block_ruby")
public class RubyBlock extends FoolFactoryBaseBlock {
    public RubyBlock() {
        super(FabricBlockSettings.of(Material.METAL).strength(2f).requiresTool());
    }
}
