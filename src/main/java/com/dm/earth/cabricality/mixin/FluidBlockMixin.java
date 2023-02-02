package com.dm.earth.cabricality.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.dm.earth.cabricality.lib.util.CobbleGenUtil;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {
	@Shadow
	@Final
	protected FlowableFluid fluid;

	@SuppressWarnings("deprecation")
	@Inject(method = "receiveNeighborFluids", at = @At("HEAD"), cancellable = true)
	private void cabfReceiveNeighborFluids(World world, BlockPos pos, BlockState state,
			CallbackInfoReturnable<Boolean> cir) {
		if (this.fluid.isIn(FluidTags.LAVA) && world.getBlockState(pos.offset(Direction.DOWN)).isOf(Blocks.BEDROCK)) {
			for (Direction direction : FluidBlock.FLOW_DIRECTIONS) {
				FluidState targetState = world.getFluidState(pos.offset(direction));
				if (targetState.isOf(Fluids.WATER) || targetState.isOf(Fluids.FLOWING_WATER)) {
					world.setBlockState(pos, CobbleGenUtil.getBlock(world, pos));
					world.syncWorldEvent(1501, pos, 0);
					cir.setReturnValue(false);
				}
			}
		}
	}
}
