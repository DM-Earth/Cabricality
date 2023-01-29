package com.dm.earth.cabricality.lib.resource.assets.gen.block;

import net.devtech.arrp.json.blockstate.JBlockStates;
import net.minecraft.util.Identifier;

public class BlockStatesGenerator {
    public static JBlockStates fourDirections(Identifier modelId) {
        return JBlockStates.simpleHorizontalFacing(modelId, false);
    }

    public static JBlockStates simple(Identifier modelId) {
        return JBlockStates.simple(modelId);
    }
}
