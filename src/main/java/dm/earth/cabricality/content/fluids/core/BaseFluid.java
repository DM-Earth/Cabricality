package dm.earth.cabricality.content.fluids.core;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.client.FluidColorRegistry;
import dm.earth.cabricality.client.FluidRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class BaseFluid extends Fluid implements IFluid {
	private final String name;

	public BaseFluid(String name) {
		this.name = name;
	}

	public BaseFluid color(int tint) {
		FluidColorRegistry.register(name, tint);
		return this;
	}

	@Override
	protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid,
			Direction direction) {
		return true;
	}

	@Override
	protected Vec3d getVelocity(BlockView world, BlockPos pos, FluidState state) {
		return Vec3d.ZERO;
	}

	@Override
	public int getTickRate(WorldView world) {
		return 0;
	}

	@Override
	protected float getBlastResistance() {
		return 0;
	}

	@Override
	public float getHeight(FluidState state, BlockView world, BlockPos pos) {
		return 0;
	}

	@Override
	public float getHeight(FluidState state) {
		return 0;
	}

	@Override
	public BlockState toBlockState(FluidState state) {
		Block block = Registries.BLOCK.get(((IFluid) this.getTypical()).getId());
		if (block == Blocks.AIR)
			return Blocks.AIR.getDefaultState();
		return Registries.BLOCK.get(getId()).getDefaultState().with(FluidBlock.LEVEL, getLevel(state));
	}

	@Override
	public BlockState toBlockState() {
		return toBlockState(getDefaultState());
	}

	@Override
	public Block toBlock(FluidState state) {
		return toBlockState(getDefaultState()).getBlock();
	}

	@Override
	public Block toBlock() {
		return toBlockState().getBlock();
	}

	@Override
	public boolean isSource(FluidState state) {
		return true;
	}

	@Override
	public int getLevel(FluidState state) {
		return 8;
	}

	@Override
	public VoxelShape getShape(FluidState state, BlockView world, BlockPos pos) {
		return VoxelShapes.empty();
	}

	@Override
	public Identifier getId() {
		return Cabricality.id(this.getName());
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Fluid getFlowing() {
		return this.getTypical();
	}

	@Override
	public void setupRendering() {
		FluidRendererRegistry.register(this.getName(), this.getTextureName(), this.getTypical(), this.getFlowing(),
				false);
	}

	@Override
	public boolean hasBucketItem() {
		return true;
	}

	@Override
	public Fluid getTypical() {
		return this;
	}

	@Override
	public Item getBucketItem() {
		Identifier id = Cabricality.id(this.getName() + "_bucket");
		if (Registries.ITEM.containsId(id))
			return Registries.ITEM.get(id);
		return Items.AIR;
	}
}
