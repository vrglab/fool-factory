package com.vrglab.foolfactory.Core.Handling.Fluid;

import com.vrglab.foolfactory.Core.Database.BlockDatabase;
import com.vrglab.foolfactory.Core.Database.ItemDatabase;
import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryFluidBlock;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryBucketItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class FoolFactoryFlowableFluid extends FlowableFluid {

    protected FoolFactoryFluid attachedFluid;

    public FoolFactoryFlowableFluid(FoolFactoryFluid attachedFluid) {
        this.attachedFluid = attachedFluid;
    }

    @Override
    public Fluid getFlowing() {
        return attachedFluid.getFlowingFluid();
    }

    @Override
    public Fluid getStill() {
        return attachedFluid.getStillFluid();
    }

    @Override
    protected boolean isInfinite() {
        return attachedFluid.isInfiniteSource();
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        attachedFluid.beforeBreakingBlockEvent(world, pos, state);
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return attachedFluid.getFluidFlowSpeed(world);
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return attachedFluid.getLevelDecreasePerBlock(world);
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    public Item getBucketItem() {
        return (FoolFactoryBucketItem)ItemDatabase.getInstance().GetEntry(attachedFluid.Name()+"_bucket");
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return attachedFluid.canBeReplacedWith(state, world, pos, fluid, direction);
    }

    @Override
    public int getTickRate(WorldView world) {
        return attachedFluid.getTickRate(world);
    }

    @Override
    protected float getBlastResistance() {
        return 0;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ((Block)BlockDatabase.getInstance().GetEntry(attachedFluid.Name()+"_block")).getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    public static class FoolFactoryFlowing extends FoolFactoryFlowableFluid
    {

        public FoolFactoryFlowing(FoolFactoryFluid attachedFluid) {
            super(attachedFluid);
        }
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }
    }

    public static class FoolFactoryStill extends FoolFactoryFlowableFluid
    {
        public FoolFactoryStill(FoolFactoryFluid attachedFluid) {
            super(attachedFluid);
        }

        @Override
        public int getLevel(FluidState state) {
            return attachedFluid.getBaseLevel(state);
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
