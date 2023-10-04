package dm.earth.cabricality.config.modmenu;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.config.CabfConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;

public class CabfConfigScreen {
	private final ConfigBuilder builder = ConfigBuilder.create()
			.setTitle(Cabricality.genTranslatableText("screen", "config", "title"))
			.transparentBackground()
			.setSavingRunnable(CabfConfig::override);
	private final ConfigEntryBuilder entryBuilder = builder.entryBuilder();

	public CabfConfigScreen(Screen parent) {
		builder.setParentScreen(parent);
		initEntries();
	}

	public Screen build() {
		return builder.build();
	}

	private final ConfigCategory general = builder.getOrCreateCategory(
			Cabricality.genTranslatableText("config", "category", "general")
	);
	private final ConfigCategory debug = builder.getOrCreateCategory(
			Cabricality.genTranslatableText("config", "category", "debug")
	);

	private void initEntries() {
		// General
		general.addEntry(
				entryBuilder.startBooleanToggle(
								Cabricality.genTranslatableText("config", "general", "include_version_in_window_title"),
								CabfConfig.includeVersionInWindowTitle()
						)
						.setDefaultValue(true)
						.setTooltip(Cabricality.genTranslatableText("config", "general", "include_version_in_window_title", "tooltip"))
						.setSaveConsumer(CabfConfig::includeVersionInWindowTitle)
						.build()
		);

		general.addEntry(
				entryBuilder.startBooleanToggle(
								Cabricality.genTranslatableText("config", "general", "fade_screen_background"),
								CabfConfig.fadeScreenBackground()
						)
						.setDefaultValue(true)
						.setTooltip(Cabricality.genTranslatableText("config", "general", "fade_screen_background", "tooltip"))
						.setSaveConsumer(CabfConfig::fadeScreenBackground)
						.build()
		);

		// Debug
		debug.addEntry(
				entryBuilder.startBooleanToggle(
								Cabricality.genTranslatableText("config", "debug", "debug_info"),
								CabfConfig.debugInfo()
						)
						.setDefaultValue(false)
						.setSaveConsumer(CabfConfig::debugInfo)
						.build()
		);
	}
}
