package com.dm.earth.cabricality.server;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.server.DedicatedServerModInitializer;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.ModDeps;

public class CabricalityServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer(ModContainer mod) {
		if (ModDeps.isLoaded(true, true))
			throw new RuntimeException(ModDeps.asString(true, true) + " is missing for " + Cabricality.NAME + "!");
		else if (ModDeps.isLoaded(false, true))
			Cabricality.LOGGER.warn(
					"Recommended mods " + ModDeps.asString(false, true) + " is missing for " + Cabricality.NAME + "!");
	}
}
