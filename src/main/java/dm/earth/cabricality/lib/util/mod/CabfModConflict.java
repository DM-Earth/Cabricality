package dm.earth.cabricality.lib.util.mod;

import java.util.stream.Stream;

import net.fabricmc.loader.api.FabricLoader;

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
		return FabricLoader.getInstance().isModLoaded(modId);
	}

	public static boolean isAnyLoaded() {
		return Stream.of(values()).anyMatch(CabfModConflict::isLoaded);
	}

	public static void checkAndExit() {
		if (isAnyLoaded())
			throw new RuntimeException();
	}
}
