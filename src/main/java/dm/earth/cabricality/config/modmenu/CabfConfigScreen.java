package dm.earth.cabricality.config.modmenu;

import dm.earth.cabricality.Cabricality;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;

public class CabfConfigScreen {
	private final ConfigBuilder builder = ConfigBuilder.create()
												  .setTitle(Cabricality.genTranslatableText("screen", "config", "title"))
												  .transparentBackground()
												  .setSavingRunnable(Cabricality.CONFIG::save);
	private final ConfigEntryBuilder entryBuilder = builder.entryBuilder();

	public CabfConfigScreen(Screen parent) {
		builder.setParentScreen(parent);
		initEntries();
	}

	public Screen build() {
		return builder.build();
	}

	/*
	 * CATEGORIES
	 */

	private final ConfigCategory general = builder.getOrCreateCategory(Cabricality.genTranslatableText("config", "category", "general"));
	private final ConfigCategory debug = builder.getOrCreateCategory(Cabricality.genTranslatableText("config", "category", "debug"));

	private void initEntries() {
		// General
		general.addEntry(entryBuilder.startBooleanToggle(Cabricality.genTranslatableText("config", "general", "include_version_in_window_title"), Cabricality.CONFIG.includeVersionInWindowTitle())
								 .setDefaultValue(true)
								 .setTooltip(Cabricality.genTranslatableText("config", "general", "include_version_in_window_title", "tooltip"))
								 .setSaveConsumer(Cabricality.CONFIG::includeVersionInWindowTitle)
								 .build());

		general.addEntry(entryBuilder.startBooleanToggle(Cabricality.genTranslatableText("config", "general", "fade_screen_background"), Cabricality.CONFIG.fadeScreenBackground())
								 .setDefaultValue(true)
								 .setTooltip(Cabricality.genTranslatableText("config", "general", "fade_screen_background", "tooltip"))
								 .setSaveConsumer(Cabricality.CONFIG::fadeScreenBackground)
								 .build());

		// Debug
		debug.addEntry(entryBuilder.startBooleanToggle(Cabricality.genTranslatableText("config", "debug", "debug_info"), Cabricality.CONFIG.debugInfo())
							   .setDefaultValue(false)
							   .setSaveConsumer(Cabricality.CONFIG::debugInfo)
							   .build());

		debug.addEntry(entryBuilder.startBooleanToggle(Cabricality.genTranslatableText("config", "debug", "cleaner_log"), Cabricality.CONFIG.cleanerLog())
							   .setDefaultValue(true)
							   .setSaveConsumer(Cabricality.CONFIG::cleanerLog)
							   .build());
	}
}
