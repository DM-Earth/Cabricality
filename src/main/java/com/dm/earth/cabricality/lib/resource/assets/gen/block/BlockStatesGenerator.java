package com.dm.earth.cabricality.lib.resource.assets.gen.block;

import net.devtech.arrp.json.blockstate.JBlockStates;
import net.minecraft.data.client.model.BlockStateSupplier;
import net.minecraft.util.Identifier;

public class BlockStatesGenerator {
    public static BlockStateSupplier fourDirections(Identifier modelId) {
        return JBlockStates.simpleHorizontalFacing(modelId, false);
    }

    public static BlockStateSupplier simple(Identifier modelId) {
        return JBlockStates.simple(modelId);
    }
}
