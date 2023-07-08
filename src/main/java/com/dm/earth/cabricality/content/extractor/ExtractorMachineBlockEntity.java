package com.dm.earth.cabricality.content.extractor;

import static com.dm.earth.cabricality.lib.util.debug.CabfDebugger.debug;

import java.util.Arrays;
import java.util.List;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

import com.dm.earth.cabricality.content.entries.CabfBlocks;
import com.dm.earth.cabricality.content.entries.CabfFluids;

import io.github.fabricators_of_create.porting_lib.transfer.TransferUtil;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

@SuppressWarnings("UnstableApiUsage")
public class ExtractorMachineBlockEntity extends BlockEntity implements IHaveGoggleInformation {
	public final SingleVariantStorage<FluidVariant> storage = new SingleVariantStorage<>() {
		@Override
		protected FluidVariant getBlankVariant() {
			return FluidVariant.blank();
		}

		@Override
		protected long getCapacity(FluidVariant variant) {
			return FluidConstants.BOTTLE * 4;
		}

		@Override
		protected void onFinalCommit() {
			markDirty();
		}
	};

	public int ticks = 0;

	public static final BlockEntityType<ExtractorMachineBlockEntity> TYPE = QuiltBlockEntityTypeBuilder
			.create(ExtractorMachineBlockEntity::new, CabfBlocks.EXTRACTOR).build();

	public ExtractorMachineBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(TYPE, blockPos, blockState);
	}

	public static void tick(World world, BlockPos blockPos, BlockState blockState,
			ExtractorMachineBlockEntity blockEntity) {
		if (!world.isClient()) {
			blockEntity.ticks++;
			if (blockEntity.ticks >= 360) {
				blockEntity.ticks = 0;
				debug("tick from extractor block entity at " + blockPos.toShortString() + " capacity: "
						+ blockEntity.storage.getCapacity());

				float f = isNextToTree(world, blockPos);
				if (f > 0.0F && blockEntity.storage.amount < blockEntity.storage.getCapacity()) {
					debug("extractor block entity: inserting to storage");
					TransferUtil.insert(blockEntity.storage, FluidVariant.of(CabfFluids.RESIN),
							(long) (f * FluidConstants.INGOT));
				}
			}
		}
	}

	private static float isNextToTree(World world, BlockPos blockPos) {
		assert world != null;

		for (Direction direction : Arrays.stream(Direction.values())
				.filter((direction -> direction != Direction.UP && direction != Direction.DOWN))
				.toArray(Direction[]::new)) {

			BlockPos targetPos = blockPos.offset(direction);
			BlockState targetState = world.getBlockState(targetPos);

			if (isVecLog(targetState)) {
				debug("extractor block entity: found log at " + blockPos.offset(direction).toShortString());
				// check if there are enough logs
				BlockPos topBlock = targetPos;
				BlockPos bottomBlock = targetPos;

				// Get top and bottom coordinates of the tree
				while (isVecLog(world.getBlockState(topBlock.offset(Direction.UP, 1)))) {
					topBlock = topBlock.offset(Direction.UP, 1);
				}

				while (isVecLog(world.getBlockState(bottomBlock.offset(Direction.DOWN, 1)))) {
					bottomBlock = bottomBlock.offset(Direction.DOWN, 1);
				}

				// Add an extra block, since we're measuring from the top of topBlock to the bottom of bottomBlock (inclusive)
				int difference = topBlock.getY() - bottomBlock.getY() + 1;
				debug("extractor block entity: tree spans from " + bottomBlock.toShortString() + " to " + topBlock.toShortString() + " (" + difference + " blocks)");

				if (difference >= 5) {
					debug("extractor block entity: found enough logs at " + targetPos.toShortString());

					// check if there are leaves
					var count = 0;

					// Get a range of 2 blocks in every direction
					var bottomPoint = topBlock.offset(Direction.DOWN, 2).offset(Direction.WEST, 2).offset(Direction.SOUTH, 2);
					var topPoint = topBlock.offset(Direction.UP, 2).offset(Direction.EAST, 2).offset(Direction.NORTH, 2);
					for (BlockPos value : BlockPos.iterate(bottomPoint, topPoint)) {
						if (isPersistentLeaves(world.getBlockState(value)))
							count++;
					}

					if (count > 4) {
						debug("extractor block entity: found enough leaves at " + topBlock.toShortString());
						return isRubberTree(world.getBlockState(targetPos)) ? 1.5F : 1.0F;
					} else {
						debug("extractor block entity: not enough leaves at " + topBlock.toShortString());
					}
				}
			}
		}
		return 0.0F;
	}

	private static boolean isPersistentLeaves(BlockState state) {
		return state.isIn(BlockTags.LEAVES) && !state.get(LeavesBlock.PERSISTENT);
	}

	private static boolean isVecLog(BlockState blockState) {
		return blockState.getBlock() instanceof PillarBlock block
				&& Registry.BLOCK.getTag(BlockTags.LOGS).get().stream()
						.anyMatch(blockHolder -> blockHolder.value() == block)
				&& blockState.get(PillarBlock.AXIS) == Direction.Axis.Y;
	}

	private static boolean isRubberTree(BlockState state) {
		return Registry.BLOCK.getId(state.getBlock()).getPath().contains("rubber");
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		storage.variant = FluidVariant.fromNbt(nbt.getCompound("fluid"));
		storage.amount = nbt.getLong("amount");

		ticks = nbt.getInt("ticks");
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		nbt.put("fluid", storage.variant.toNbt());
		nbt.putLong("amount", storage.amount);

		nbt.putInt("ticks", ticks);
	}

	@Override
	public boolean addToGoggleTooltip(List<Text> tooltip, boolean isPlayerSneaking) {
		return true;
	}

}
