package com.dm.earth.cabricality.content.skyblock;

import com.dm.earth.cabricality.util.debug.CabfLogger;


import org.quiltmc.qsl.lifecycle.api.event.ServerWorldLoadEvents;


import static com.dm.earth.cabricality.content.skyblock.Skyblock.isSkyblock;

public class WorldStatus {
	public static void load(){
		ServerWorldLoadEvents.LOAD.register((server, world)->{
			//if(Objects.equals(server.getSaveProperties().getGeneratorOptions().HOW_THE_FUCK_DO_I_GET_THE_REGISTRY()
			//		, "skyblockcreator:structure_chunk_generator")){
				isSkyblock = true;
			//}
			if(isSkyblock){
				CabfLogger.logInfo("This is a Skyblock save!");
			}
		});
	}
}
