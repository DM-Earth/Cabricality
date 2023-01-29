package com.dm.earth.cabricality.lib.util.debug;

import org.quiltmc.loader.api.QuiltLoader;

import com.dm.earth.cabricality.Cabricality;

public class CabfDebugger {
	public static boolean enabled = false;

	public static void debug(String bug) {
		if (enabled || QuiltLoader.isDevelopmentEnvironment())
			Cabricality.LOGGER.info("[" + Cabricality.NAME + "/DEBUG] " + bug);
	}
}
