package com.dm.earth.cabricality.content.machine.extractor;

import static com.dm.earth.cabricality.util.CabfDebugger.debug;

import java.util.Arrays;
import java.util.List;

import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.PlayerLookup;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.entries.CabfBlocks;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.util.TransferUtil;
import com.simibubi.create.content.contraptions.goggles.IHaveGoggleInformation;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
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
			assert world != null;
			if (!world.isClient()) {
				PacketByteBuf buf = PacketByteBufs.create();
				PlayerLookup.tracking(ExtractorMachineBlockEntity.this)
						.forEach(player -> ServerPlayNetworking.send(player, Cabricality.id("extractor_buf"), buf));
			}
		}
	};
	public static final BlockEntityType<ExtractorMachineBlockEntity> TYPE = QuiltBlockEntityTypeBuilder
			.create(ExtractorMachineBlockEntity::new, CabfBlocks.EXTRACTOR).build();

	public ExtractorMachineBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(TYPE, blockPos, blockState);
	}

	public static void tick(World world, BlockPos blockPos, BlockState blockState,
			ExtractorMachineBlockEntity blockEntity) {
		if (!world.isClient()) {
			ExtractorMachineBlock.ticks++;
			if (ExtractorMachineBlock.ticks >= 360)
				ExtractorMachineBlock.ticks = 0;
			else
				return;
			debug("randomTick from extractor block entity at " + blockPos.toShortString() + " capacity: "
					+ blockEntity.storage.getCapacity());
			float f = isNextToTree(world, blockPos, blockState, blockEntity);
			if (f > 0.0F && blockEntity.storage.amount < blockEntity.storage.getCapacity()) {
				debug("extractor block entity: inserting to storage");
				blockEntity.storage.insert(FluidVariant.of(CabfFluids.RESIN), (long) (f * FluidConstants.INGOT),
						TransferUtil.getTransaction());
			}
		}
	}

	private static float isNextToTree(World world, BlockPos blockPos, BlockState blockState,
			ExtractorMachineBlockEntity blockEntity) {
		assert world != null;
		for (Direction direction : Arrays.stream(Direction.values())
				.filter((direction -> direction != Direction.UP && direction != Direction.DOWN))
				.toArray(Direction[]::new)) {
			BlockState targetState = world.getBlockState(blockPos.offset(direction));
			if (isVecLog(targetState)) {
				debug("extractor block entity: found log at " + blockPos.offset(direction).toShortString());
				// check if there are enough logs
				boolean enoughLogs = false;
				BlockPos targetPos = blockPos.offset(direction);
				int i = 1;
				int ii = 1;
				BlockPos upPos = blockPos;
				while (true) {
					if (ii >= 4) {
						upPos = targetPos.offset(Direction.UP, i - 1);
						enoughLogs = true;
					}
					if (isVecLog(world.getBlockState(targetPos.offset(Direction.UP, i)))) {
						i++;
						ii++;
					} else
						break;
				}
				i = 1;
				while (true) {
					if (ii >= 4) {
						enoughLogs = true;
					}
					if (isVecLog(world.getBlockState(targetPos.offset(Direction.DOWN, i)))) {
						i++;
						ii++;
					} else
						break;
				}
				if (enoughLogs) {
					debug("extractor block entity: found enough logs at " + targetPos.toShortString());
					// check if there are leaves
					boolean enoughLeaves = true;
					for (Direction leafDirection : Arrays.stream(Direction.values())
							.filter((leafDirection -> leafDirection != Direction.DOWN)).toArray(Direction[]::new)) {
						if (!isPersistentLeaves(world, upPos, leafDirection))
							enoughLeaves = false;
					}
					if (enoughLeaves) {
						debug("extractor block entity: found enough leaves at " + upPos.toShortString());
						if (Registry.BLOCK.getId(world.getBlockState(targetPos).getBlock()).getPath()
								.contains("rubber"))
							return 1.5F;
						else
							return 1.0F;
					} else
						debug("extractor block entity: not enough leaves at " + upPos.toShortString());
				} else
					return 0.0F;
			}
		}
		return 0.0F;
	}

	private static boolean isPersistentLeaves(World world, BlockPos blockPos, Direction direction) {
		BlockState blockState = world.getBlockState(blockPos.offset(direction));
		return Registry.BLOCK.getTag(BlockTags.LEAVES).get().stream().anyMatch(
				blockHolder -> blockHolder.value() == blockState.getBlock()) && !blockState.get(LeavesBlock.PERSISTENT);
	}

	private static boolean isVecLog(BlockState blockState) {
		return blockState.getBlock() instanceof PillarBlock block
				&& Registry.BLOCK.getTag(BlockTags.LOGS).get().stream()
						.anyMatch(blockHolder -> blockHolder.value() == block)
				&& blockState.get(PillarBlock.AXIS) == Direction.Axis.Y;
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		storage.variant = FluidVariant.fromNbt(nbt.getCompound("fluid"));
		storage.amount = nbt.getLong("amount");
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		nbt.put("fluid", storage.variant.toNbt());
		nbt.putLong("amount", storage.amount);
	}

	@Override
	public boolean addToGoggleTooltip(List<Text> tooltip, boolean isPlayerSneaking) {
		return true;
	}

}
