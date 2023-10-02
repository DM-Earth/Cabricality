package dm.earth.cabricality.server;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.util.debug.CabfLogger;
import dm.earth.cabricality.lib.util.mod.CabfModDeps;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.server.DedicatedServerModInitializer;

public class CabricalityServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer(ModContainer mod) {
		if (!CabfModDeps.isLoaded(true, true))
			throw new RuntimeException(CabfModDeps.asString(true, true) + " is missing for " + Cabricality.NAME + "!");
		else if (!CabfModDeps.isLoaded(false, true))
			CabfLogger.logWarn("Recommended mods " + CabfModDeps.asString(false, true) + " is missing for " + Cabricality.NAME + "!");
	}
}
