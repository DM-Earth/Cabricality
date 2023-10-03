package dm.earth.cabricality.lib.util.debug;

import dm.earth.cabricality.Cabricality;
import net.fabricmc.loader.api.FabricLoader;

public class CabfDebugger {
	public static boolean enabled = false;

	public static void debug(String bug) {
		if (enabled || FabricLoader.getInstance().isDevelopmentEnvironment())
			Cabricality.LOGGER.info("[" + Cabricality.NAME + "/DEBUG] " + bug);
	}
}
