package dm.earth.cabricality.content.entries;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.extractor.ExtractorMachineBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

@SuppressWarnings("UnstableApiUsage")
public class CabfBlockEntityTypes {
	public static void register() {
		registerBlockEntityType("extractor", ExtractorMachineBlockEntity.TYPE);
		FluidStorage.SIDED.registerForBlockEntity((tank, direction) -> tank.storage, ExtractorMachineBlockEntity.TYPE);
	}

	private static void registerBlockEntityType(String name, BlockEntityType<?> blockEntityType) {
		Registry.register(Registries.BLOCK_ENTITY_TYPE, Cabricality.id(name), blockEntityType);
	}
}
