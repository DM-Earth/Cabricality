package com.dm.earth.cabricality.content.skyblock;

import net.minecraft.client.world.GeneratorType;

import org.quiltmc.qsl.lifecycle.api.event.ServerWorldLoadEvents;

import java.util.Objects;

public class WorldStatus {
	public static void load(){
		ServerWorldLoadEvents.LOAD.register((server, world)->{
			//if(Objects.equals(server.getSaveProperties().getGeneratorOptions().HOW_THE_FUCK_DO_I_GET_THE_REGISTRY()
			//		, "skyblockcreator:structure_chunk_generator")){
				Skyblock.isSkyblock = true;
			//}
		});
	}
}
