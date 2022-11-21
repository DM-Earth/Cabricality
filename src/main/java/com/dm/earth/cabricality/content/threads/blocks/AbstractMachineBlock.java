package com.dm.earth.cabricality.content.threads.blocks;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.resource.ResourcedBlock;
import com.dm.earth.cabricality.resource.assets.gen.block.BlockStatesGenerator;
import com.dm.earth.cabricality.util.VoxelShapeUtil;

import net.devtech.arrp.json.blockstate.JBlockStates;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public abstract class AbstractMachineBlock extends HorizontalFacingBlock implements ResourcedBlock, Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public AbstractMachineBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    public boolean doBlockStates() {
        return true;
    }

    @Override
    public boolean doItemModel() {
        return true;
    }

    @Override
    public boolean doLootTable() {
        return true;
    }

    @Override
    public Identifier getBlockModelId() {
        return Cabricality.id("block/machine/" + Registry.BLOCK.getId(this).getPath());
    }

    @Override
    public JBlockStates getBlockStates() {
        return this.isFull() ? BlockStatesGenerator.simple(this.getBlockModelId())
                : BlockStatesGenerator.fourDirections(this.getBlockModelId());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite())
                .with(WATERLOGGED,
                        this.isWaterLoggable()
                                ? ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER
                                : false);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getDefaultState() : Fluids.EMPTY.getDefaultState();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapeUtil.simpleBox(1, 0, 1, 15, this.isFull() ? 16 : 14, 15);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
            WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED))
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        return state;
    }

    @Override
    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return this.isWaterLoggable() ? Waterloggable.super.canFillWithFluid(world, pos, state, fluid) : false;
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, WATERLOGGED);
    }

    protected abstract boolean isWaterLoggable();

    protected abstract boolean isFull();

}
