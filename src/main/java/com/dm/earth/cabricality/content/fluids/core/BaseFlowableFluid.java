package com.dm.earth.cabricality.content.fluids.core;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.FluidColorRegistry;
import com.dm.earth.cabricality.client.FluidRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class BaseFlowableFluid extends FlowableFluid implements IFluid {
	private final boolean isStill;
	private final String name;

	public BaseFlowableFluid(String name, boolean isStill) {
		this.isStill = isStill;
		this.name = name;
	}

	public BaseFlowableFluid color(int tint) {
		FluidColorRegistry.register(name, tint);
		return this;
	}

	@Override
	protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
		final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropStacks(state, world, pos, blockEntity);
	}

	@Override
	protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
		return false;
	}

	@Override
	public int getTickRate(WorldView world) {
		return 5;
	}

	@Override
	protected float getBlastResistance() {
		return 100.0F;
	}

	@Override
	protected int getLevelDecreasePerBlock(WorldView world) {
		return 1;
	}

	@Override
	protected boolean isInfinite() {
		return false;
	}

	@Override
	protected BlockState toBlockState(FluidState state) {
		Block block = Registry.BLOCK.get(((IFluid) this.getTypical()).getId());
		if (block == Blocks.AIR) return Blocks.AIR.getDefaultState();
		return block.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
	}

	@Override
	public Identifier getRegistryName() {
		return this.getId();
	}

	@Override
	public Fluid getTypical() {
		return this.getStill();
	}

	@Override
	public Identifier getId() {
		return Cabricality.id(this.isStill(null) ? this.getName() : (this.getName() + "_flowing"));
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setupRendering() {
		if (this.isStill(null))
			FluidRendererRegistry.register(this.getName(), this.getTextureName(), this.getTypical(), this.getFlowing(), true);
	}

	@Override
	public boolean hasBucketItem() {
		return true;
	}

	@Override
	public boolean isStill(FluidState state) {
		return this.isStill;
	}

	@Override
	public boolean matchesType(Fluid fluid) {
		return fluid == getStill() || fluid == getFlowing();
	}

	@Override
	public Item getBucketItem() {
		Identifier id = Cabricality.id(this.getName() + "_bucket");
		if (Registry.ITEM.containsId(id)) return Registry.ITEM.get(id);
		return Items.AIR;
	}

	@Override
	protected int getFlowSpeed(WorldView world) {
		return 4;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
		super.appendProperties(builder);
		if (!this.isStill(null)) builder.add(LEVEL);
	}

	@Override
	public int getLevel(FluidState state) {
		if (!this.isStill(null)) return 8;
		return state.get(LEVEL);
	}

	@Override
	public Fluid getFlowing() {
		if (!this.isStill(null)) return this;
		return Registry.FLUID.get(Cabricality.id(this.getName() + "_flowing"));
	}

	@Override
	public Fluid getStill() {
		if (this.isStill(null)) return this;
		return Registry.FLUID.get(Cabricality.id(this.getName()));
	}
}
