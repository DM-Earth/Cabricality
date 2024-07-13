package dm.earth.cabricality.lib.util;

import static dm.earth.cabricality.Mod.Entry.CREATE;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class CobbleGenUtil {
	private static final Map<Block, Block> COBBLE_GEN_BLOCK_MAP = Map.of(Blocks.PACKED_ICE, Blocks.ANDESITE);

	public static BlockState getBlock(WorldAccess world, BlockPos pos) {
		BlockState upState = world.getBlockState(pos.offset(Direction.UP));
		return COBBLE_GEN_BLOCK_MAP.containsKey(upState.getBlock())
				? COBBLE_GEN_BLOCK_MAP.get(upState.getBlock()).getDefaultState()
				: COBBLE_GEN_BLOCK_MAP.values().stream().toList()
				.get((new Random()).nextInt(CobbleGenUtil.COBBLE_GEN_BLOCK_MAP.size())).getDefaultState();
	}
}
