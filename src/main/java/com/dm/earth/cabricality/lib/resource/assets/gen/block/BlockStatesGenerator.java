package com.dm.earth.cabricality.lib.resource.assets.gen.block;

import net.minecraft.block.Block;
import net.minecraft.data.client.model.BlockStateModelGenerator;
import net.minecraft.data.client.model.BlockStateSupplier;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;

public class BlockStatesGenerator {
    public static BlockStateSupplier fourDirections(Block block, Identifier modelId) {
        return BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(block, modelId);
    }

    public static BlockStateSupplier simple(Block block, Identifier modelId) {
        return BlockStateModelGenerator.createSingletonBlockState(block, modelId);
    }
}
