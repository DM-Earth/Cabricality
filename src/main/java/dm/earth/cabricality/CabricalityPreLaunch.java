package dm.earth.cabricality;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.jetbrains.annotations.Nullable;

public class CabricalityPreLaunch implements PreLaunchEntrypoint {
	@Override
	public void onPreLaunch() {
		Mod.Conflict.checkAndExit();

		@Nullable String launcherBrand = System.getProperty("minecraft.launcher.brand");
		if (launcherBrand != null && launcherBrand.contains("PCL")) {
			throw new RuntimeException("PCL2 is not supported. Have a hug.");
		}
	}
}
