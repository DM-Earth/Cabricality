package dm.earth.cabricality.server;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.Mod;
import dm.earth.cabricality.lib.util.log.CabfLogger;
import net.fabricmc.api.DedicatedServerModInitializer;

public class CabricalityServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer() {
		if (!Mod.Dependency.isLoaded(true, true))
			throw new RuntimeException("Required mods " + Mod.Dependency.asString(true, true) + " are missing for " + Cabricality.NAME + "!");
		else if (!Mod.Dependency.isLoaded(false, true))
			CabfLogger.warn("Recommended mods " + Mod.Dependency.asString(false, true) + " are missing for " + Cabricality.NAME + "!");
	}
}
