package com.dm.earth.cabricality.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.dm.earth.cabricality.lib.util.CobbleGenUtil;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

@Mixin(LavaFluid.class)
public class LavaFluidMixin {
	@Redirect(method = "flow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldAccess;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
	protected boolean setState(WorldAccess instance, BlockPos pos, BlockState blockState, int i) {
		return instance.getBlockState(pos.offset(Direction.DOWN)).isOf(Blocks.BEDROCK)
				? instance.setBlockState(pos, CobbleGenUtil.getBlock(instance, pos), i)
				: instance.setBlockState(pos, blockState, i);
	}
}
