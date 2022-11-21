package com.dm.earth.cabricality.server;

import com.dm.earth.cabricality.util.ModChecker;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.server.DedicatedServerModInitializer;

public class CabricalityServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer(ModContainer mod) {
		ModChecker.check();
		if (!ModChecker.isFullLoaded())
			throw new RuntimeException(ModChecker.getMods() + " is missing for Cabricality Modpack!");
	}
}
