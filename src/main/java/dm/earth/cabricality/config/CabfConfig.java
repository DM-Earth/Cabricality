package dm.earth.cabricality.config;

import net.fabricmc.loader.api.FabricLoader;
import net.krlite.pierced.annotation.Silent;
import net.krlite.pierced.annotation.Table;
import net.krlite.pierced.config.Pierced;

import java.io.File;

public class CabfConfig extends Pierced {
	private static @Silent final File FILE = FabricLoader.getInstance().getConfigDir().resolve("cabricality.toml").toFile();
	private static @Silent final CabfConfig INSTANCE = new CabfConfig();

	private CabfConfig() {
		super(CabfConfig.class, FILE);
		load();
	}

	public static void override() {
		INSTANCE.save();
	}

	private boolean includeVersionInWindowTitle = false;

	public static boolean includeVersionInWindowTitle() {
		return INSTANCE.includeVersionInWindowTitle;
	}

	public static void includeVersionInWindowTitle(boolean includeVersionInGameTitle) {
		INSTANCE.includeVersionInWindowTitle = includeVersionInGameTitle;
		INSTANCE.save();
	}

	private boolean fadeScreenBackground = true;

	public static boolean fadeScreenBackground() {
		return INSTANCE.fadeScreenBackground;
	}

	public static void fadeScreenBackground(boolean fadeScreenBackground) {
		INSTANCE.fadeScreenBackground = fadeScreenBackground;
		INSTANCE.save();
	}

	@Table("debug")
	private boolean debugInfo = false;

	public static boolean debugInfo() {
		return INSTANCE.debugInfo;
	}

	public static void debugInfo(boolean debugInfo) {
		INSTANCE.debugInfo = debugInfo;
		INSTANCE.save();
	}
}
