package com.dm.earth.cabricality.content.skyblock;

import static com.dm.earth.cabricality.ModEntry.AP;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.PMD;
import static com.dm.earth.cabricality.ModEntry.TC;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class SkyblockCobbleGen {
	// Almost vanilla not-too-deep underground materials
	private static final BlockGen STONE_GEN = new BlockGen(0.3f, Map.of(
		Blocks.STONE, Blocks.STONE,
		Blocks.CLAY, Blocks.CLAY,
		Blocks.DRIPSTONE_BLOCK, Blocks.POINTED_DRIPSTONE,
		AP.asBlock("tuff_lamp"), Blocks.GLOW_LICHEN,
		TC.asBlock("grout"), Blocks.GRAVEL
	));

	// Not-too-deep underground decorative blocks
	private static final BlockGen LIMESTONE_GEN = new BlockGen(0.7f,Map.of(
		PMD.asBlock("blunite"), PMD.asBlock("blunite"),
		PMD.asBlock("carbonite"), PMD.asBlock("carbonite"),
		CR.asBlock("veridium"), CR.asBlock("veridium"),
		CR.asBlock("crimsite"), CR.asBlock("crimsite"),
		CR.asBlock("scoria"), CR.asBlock("scoria"),
		CR.asBlock("scorchia"), CR.asBlock("scorchia"),
		CR.asBlock("asurine"), CR.asBlock("asurine"),
		CR.asBlock("ochrum"), CR.asBlock("ochrum")
	));

	private static final Map<Block, BlockGen> BLOCK_GEN_MAP = Map.of(
		Blocks.STONE, STONE_GEN,
		CR.asBlock("limestone"), LIMESTONE_GEN
	);

	public static BlockState getBlock(WorldAccess world, BlockPos pos, BlockState blockState){
		BlockState upState = world.getBlockState(pos.offset(Direction.UP));
		BlockState downState = world.getBlockState(pos.offset(Direction.DOWN));
		if(BLOCK_GEN_MAP.containsKey(downState.getBlock())) {
			BlockState blockGenRandom = BLOCK_GEN_MAP.get(downState.getBlock()).getRandom();
			return blockGenRandom==null?blockState:blockGenRandom;
		}
		return blockState;
	}
}
