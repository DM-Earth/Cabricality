package com.dm.earth.cabricality.lib.util;

import static com.dm.earth.cabricality.ModEntry.AP;
import static com.dm.earth.cabricality.ModEntry.CR;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class CobbleGenUtil {
	private static final Map<Block, Block> COBBLE_GEN_BLOCK_MAP = Map.of(Blocks.PACKED_ICE, Blocks.ANDESITE,
			AP.asBlock("polished_packed_ice"), Blocks.GRANITE, AP.asBlock("chiseled_packed_ice"), Blocks.DIORITE,
			AP.asBlock("packed_ice_pillar"), CR.asBlock("limestone"));

	public static BlockState getBlock(WorldAccess world, BlockPos pos) {
		BlockState upState = world.getBlockState(pos.offset(Direction.UP));
		return COBBLE_GEN_BLOCK_MAP.containsKey(upState.getBlock())
				? COBBLE_GEN_BLOCK_MAP.get(upState.getBlock()).getDefaultState()
				: COBBLE_GEN_BLOCK_MAP.values().stream().toList()
						.get((new Random()).nextInt(CobbleGenUtil.COBBLE_GEN_BLOCK_MAP.size())).getDefaultState();
	}
}
