package dm.earth.cabricality.lib.resource.assets.gen.fluid;

import dm.earth.cabricality.Cabricality;

import dm.earth.cabricality.lib.resource.assets.gen.block.BlockStatesGenerator;
import net.minecraft.block.Block;
import net.minecraft.data.client.model.BlockStateSupplier;

public class FluidBlockStatesGenerator {
	public static BlockStateSupplier simple(Block block, String id) {
		return BlockStatesGenerator.simple(block, Cabricality.id("block/fluid/" + id));
	}
}
