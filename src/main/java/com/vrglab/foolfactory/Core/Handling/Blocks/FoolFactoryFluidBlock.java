package com.vrglab.foolfactory.Core.Handling.Blocks;

import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.ItemGroup;

public class FoolFactoryFluidBlock extends FluidBlock implements FoolFactoryBlock{
    public FoolFactoryFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public ItemGroup getCreativeTab() {
        return null;
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
