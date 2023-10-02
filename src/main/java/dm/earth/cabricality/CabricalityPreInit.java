package dm.earth.cabricality;

import dm.earth.cabricality.lib.util.mod.CabfModConflict;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class CabricalityPreInit implements PreLaunchEntrypoint {
	@Override
	public void onPreLaunch(ModContainer mod) {
		CabfModConflict.checkAndExit();

		// Prevents PCL2 from launching
		@Nullable String launcherBrand = System.getProperty("minecraft.launcher.brand");
		if (launcherBrand != null && launcherBrand.contains("PCL")) {
			throw new RuntimeException("PCL2 is not supported. Have a hug.");
		}
	}
}
