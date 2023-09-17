package com.dm.earth.cabricality.lib.resource.assets.gen.fluid;

import com.dm.earth.cabricality.Cabricality;

import com.dm.earth.cabricality.lib.resource.assets.gen.block.BlockStatesGenerator;
import net.minecraft.data.client.model.BlockStateSupplier;

public class FluidBlockStatesGenerator {
	public static BlockStateSupplier simple(String id) {
		return BlockStatesGenerator.simple(Cabricality.id("block/fluid/" + id));
	}
}
