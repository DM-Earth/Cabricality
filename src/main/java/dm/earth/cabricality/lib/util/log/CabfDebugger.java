package dm.earth.cabricality.lib.util.log;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.config.CabfConfig;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CabfDebugger {
	private static final Logger LOGGER = LoggerFactory.getLogger(Cabricality.ID);
	private static final String PREFIX = "[" + Cabricality.NAME + "/DEBUG] ";

	public static void debug(@NotNull String message) {
		if (Cabricality.CONFIG.debugInfoEnabled || FabricLoader.getInstance().isDevelopmentEnvironment())
			LOGGER.info("[" + Cabricality.NAME + "/DEBUG] " + message);
	}
}
