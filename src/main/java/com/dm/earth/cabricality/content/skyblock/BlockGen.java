package com.dm.earth.cabricality.content.skyblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.Random;

public class BlockGen {
	private static Float Probablity = 0f;
	private static Map<Block, Block> BlockMap = Map.of();

	public BlockGen(Float probablity, Map<Block, Block> blockMap){
		Probablity = probablity;
		BlockMap = blockMap;
	}
	public Map<Block,Block> getMap(){
		return BlockMap;
	}
	public BlockState getRandom(){
		if((new Random()).nextFloat()<=Probablity){
			return BlockMap.values().stream().toList()
					.get((new Random()).nextInt(BlockMap.size())).getDefaultState();
		}
		return null;
	}
}
