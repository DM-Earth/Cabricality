package com.dm.earth.cabricality.content.extractor;

import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.lib.resource.ResourcedBlock;
import com.dm.earth.cabricality.lib.util.ItemStackUtil;

import io.github.fabricators_of_create.porting_lib.transfer.TransferUtil;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ExtractorMachineBlock extends BlockWithEntity implements ResourcedBlock {
	public ExtractorMachineBlock(Settings settings) {
		super(settings);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new ExtractorMachineBlockEntity(pos, state);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
			BlockEntityType<T> type) {
		return checkType(type, ExtractorMachineBlockEntity.TYPE, ExtractorMachineBlockEntity::tick);
	}

	@Override
	@SuppressWarnings("UnstableApiUsage")
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hit) {
		if (!(hit.getSide() == Direction.UP || hit.getSide() == Direction.DOWN || Registry.ITEM
				.getId(player.getStackInHand(hand).getItem()).equals(new Identifier("minecraft", "bucket"))))
			return ActionResult.PASS;
		ExtractorMachineBlockEntity extractor = (ExtractorMachineBlockEntity) world.getBlockEntity(pos);
		assert extractor != null;
		ItemStack stack = player.getStackInHand(hand);
		if (extractor.storage.getAmount() >= FluidConstants.BUCKET
				&& extractor.storage.getResource().isOf(CabfFluids.RESIN)) {
			TransferUtil.extract(extractor.storage, FluidVariant.of(CabfFluids.RESIN), FluidConstants.BUCKET);
			ItemStackUtil.replaceItemStack(stack, new ItemStack(CabfFluids.RESIN.getBucketItem()), 1, player, hand);
			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}

	@Override
	public boolean doLootTable() {
		return true;
	}

	@Override
	public boolean doItemModel() {
		return false;
	}
}
