package dm.earth.cabricality.lib.resource.assets.gen.block;

import net.minecraft.block.Block;
import net.minecraft.data.client.model.BlockStateModelGenerator;
import net.minecraft.data.client.model.VariantsBlockStateSupplier;
import net.minecraft.util.Identifier;

public class BlockStatesGenerator {
    public static VariantsBlockStateSupplier simple(Block block, Identifier modelId) {
        return BlockStateModelGenerator.createSingletonBlockState(block, modelId);
    }

	public static VariantsBlockStateSupplier northDefaultHorizontal(Block block, Identifier modelId) {
		return simple(block, modelId)
				.coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates());
	}
}
