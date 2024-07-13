package dm.earth.cabricality.config;

import dm.earth.cabricality.Cabricality;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Cabricality.ID)
public class CabfConfig implements ConfigData {

	public static void override() {
		AutoConfig.getConfigHolder(CabfConfig.class).save();
	}

	private boolean includeVersionInWindowTitle = false;

	public static boolean includeVersionInWindowTitle() {
		return CabfConfig.getConfig().includeVersionInWindowTitle;
	}

	public static void includeVersionInWindowTitle(boolean includeVersionInGameTitle) {
		CabfConfig.getConfig().includeVersionInWindowTitle = includeVersionInGameTitle;
		CabfConfig.override();
	}

	public static CabfConfig getConfig() {
		return AutoConfig.getConfigHolder(CabfConfig.class).getConfig();
	}

	private boolean fadeScreenBackground = true;

	public static boolean fadeScreenBackground() {
		return CabfConfig.getConfig().fadeScreenBackground;
	}

	public static void fadeScreenBackground(boolean fadeScreenBackground) {
		CabfConfig.getConfig().fadeScreenBackground = fadeScreenBackground;
		CabfConfig.override();
	}

	@ConfigEntry.Category("debug")
	private boolean debugInfo = false;

	public static boolean debugInfo() {
		return CabfConfig.getConfig().debugInfo;
	}

	public static void debugInfo(boolean debugInfo) {
		CabfConfig.getConfig().debugInfo = debugInfo;
		CabfConfig.override();
	}
}
