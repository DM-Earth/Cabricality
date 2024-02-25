package dm.earth.cabricality.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "cabricality")
public class CabfConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip
	public boolean includesVersionInWindowTitle = false;
	public boolean debugInfoEnabled = false;
}
