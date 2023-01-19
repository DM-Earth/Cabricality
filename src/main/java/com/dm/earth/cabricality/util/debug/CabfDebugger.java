package com.dm.earth.cabricality.util.debug;

import org.quiltmc.loader.api.QuiltLoader;

import com.dm.earth.cabricality.Cabricality;

public class CabfDebugger {
	public static boolean debug = false;

	public static void debug(String bug) {
		if (debug || QuiltLoader.isDevelopmentEnvironment())
			Cabricality.LOGGER.info("[" + Cabricality.NAME + "/DEBUG] " + bug);
	}
}
