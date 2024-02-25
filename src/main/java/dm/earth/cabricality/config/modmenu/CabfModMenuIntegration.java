package dm.earth.cabricality.config.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dm.earth.cabricality.config.CabfConfig;
import me.shedaniel.autoconfig.AutoConfig;

public class CabfModMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(CabfConfig.class, parent).get();
	}
}
