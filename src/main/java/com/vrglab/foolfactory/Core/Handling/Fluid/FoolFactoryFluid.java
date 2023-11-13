package com.vrglab.foolfactory.Core.Handling.Fluid;

import com.vrglab.foolfactory.Core.Database.ItemDatabase;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBlock;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItem;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;


public abstract class FoolFactoryFluid {
    private FlowableFluid StillFluid;
    private FlowableFluid flowingFluid;
    private Block fluidBlock;
    private Item bucket;

    public FlowableFluid getStillFluid() {
        return StillFluid;
    }

    public FlowableFluid getFlowingFluid() {
        return flowingFluid;
    }

    public Block getFluidBlock() {
        return fluidBlock;
    }

    public Item getBucket() {
        return bucket;
    }

    public void setStillFluid(FlowableFluid stillFluid) {
        StillFluid = stillFluid;
    }

    public void setFlowingFluid(FlowableFluid flowingFluid) {
        this.flowingFluid = flowingFluid;
    }

    public void setFluidBlock(Block fluidBlock) {
        this.fluidBlock = fluidBlock;
    }

    public void setBucket(Item bucket) {
        this.bucket = bucket;
    }

    public int ColorTint() {
        return ColorHelper.Argb.getArgb(1,1,1,1);
    }

    public String FlowingModel() {
        return "minecraft:block/water_flow";
    }

    public String StillModel() {
        return "minecraft:block/water_still";
    }

    public abstract String Name();
    public abstract FabricBlockSettings GetBlockSettings();
    public abstract FabricItemSettings GetItemSettings();
    public abstract boolean isInfiniteSource();
    public abstract int getFluidFlowSpeed(WorldView world);
    public abstract void beforeBreakingBlockEvent(WorldAccess world, BlockPos pos, BlockState state);
    public abstract int getLevelDecreasePerBlock(WorldView world);
    public abstract boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction);
    public abstract int getTickRate(WorldView world);
    public abstract int getBaseLevel(FluidState state);
}
