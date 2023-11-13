package com.vrglab.foolfactory.World.Fluids;

import com.vrglab.foolfactory.Core.Handling.Fluid.FoolFactoryFluid;
import com.vrglab.foolfactory.Core.Handling.Fluid.FoolFactoryFluidMarker;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@FoolFactoryFluidMarker("oil")
public class OilFluid extends FoolFactoryFluid {

    @Override
    public String Name() {
        return "oil";
    }

    @Override
    public FabricBlockSettings GetBlockSettings() {
        return FabricBlockSettings.copyOf(Blocks.WATER);
    }

    @Override
    public FabricItemSettings GetItemSettings() {
        return new FabricItemSettings();
    }

    @Override
    public boolean isInfiniteSource() {
        return true;
    }

    @Override
    public int getFluidFlowSpeed(WorldView world) {
        return 3;
    }

    @Override
    public void beforeBreakingBlockEvent(WorldAccess world, BlockPos pos, BlockState state) {

    }

    @Override
    public int getLevelDecreasePerBlock(WorldView world) {
        return 15;
    }

    @Override
    public boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 1;
    }

    @Override
    public int getBaseLevel(FluidState state) {
        return 8;
    }
}
