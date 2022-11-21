package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.machine.extractor.ExtractorMachineBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("UnstableApiUsage")
public class CabfBlockEntityTypes {
	public static void register() {
		registerBlockEntityType("extractor", ExtractorMachineBlockEntity.TYPE);
		FluidStorage.SIDED.registerForBlockEntity((tank, direction) -> tank.storage, ExtractorMachineBlockEntity.TYPE);
	}

	private static void registerBlockEntityType(String name, BlockEntityType<?> blockEntityType) {
		Registry.register(Registry.BLOCK_ENTITY_TYPE, Cabricality.id(name), blockEntityType);
	}
}
