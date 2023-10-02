package dm.earth.cabricality.content.core.blocks.machine;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.math.VoxelShapeUtil;
import dm.earth.cabricality.lib.resource.ResourcedBlock;
import dm.earth.cabricality.lib.resource.assets.gen.block.BlockStatesGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.data.client.model.BlockStateSupplier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMachineBlock extends HorizontalFacingBlock implements ResourcedBlock, Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public AbstractMachineBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    public boolean doBlockStates() {
        return true;
    }

    @Override
    public boolean doItemModel() {
        return ResourcedBlock.super.doItemModel();
    }

    @Override
    public boolean doLootTable() {
        return true;
    }

    @Override
    public Identifier getBlockModelId() {
        return Cabricality.id("block", "machine", Registries.BLOCK.getId(this).getPath());
    }

    @Override
    public BlockStateSupplier getBlockStates() {
        return this.isFull()
				? BlockStatesGenerator.simple(getBaseBlock(), getBlockModelId())
                : BlockStatesGenerator.northDefaultHorizontal(getBaseBlock(), getBlockModelId());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite())
                .with(WATERLOGGED, this.isWaterLoggable() && ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
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

	/*
    @Override
    public boolean canFillWithFluid(@Nullable PlayerEntity playerEntity, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return this.isWaterLoggable() && Waterloggable.super.canFillWithFluid(playerEntity, world, pos, state, fluid);
    }

	 */

	@Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, WATERLOGGED);
    }

    protected abstract boolean isWaterLoggable();

    protected abstract boolean isFull();
}
