package com.dm.earth.cabricality;

import java.util.Arrays;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.entrypoint.PreLaunchEntrypoint;

import com.dm.earth.cabricality.lib.util.mod.CabfModConflict;

public class CabricalityPreInit implements PreLaunchEntrypoint {
	@Override
	public void onPreLaunch(ModContainer mod) {
		CabfModConflict.checkAndExit();

		String launcherBrand = System.getProperty("minecraft.launcher.brand");
		if (launcherBrand.contains("PCL")) {
			throw new RuntimeException("PCL2 is not suportted.");
		}
	}
}
