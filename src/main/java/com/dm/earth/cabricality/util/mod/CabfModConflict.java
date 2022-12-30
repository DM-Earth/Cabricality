package com.dm.earth.cabricality.util.mod;

import java.util.stream.Stream;
import org.quiltmc.loader.api.QuiltLoader;

public enum CabfModConflict {
	ESSENTIAL("essential-loader");

	final String modId;

	CabfModConflict(String modId) {
		this.modId = modId;
	}

	public String getModId() {
		return modId;
	}

	public boolean isLoaded() {
		return QuiltLoader.isModLoaded(modId);
	}

	public static boolean isAnyLoaded() {
		return Stream.of(values()).anyMatch(CabfModConflict::isLoaded);
	}

	public static void checkAndExit() {
		if (isAnyLoaded()) {
			System.exit(6);
		}
	}
}
