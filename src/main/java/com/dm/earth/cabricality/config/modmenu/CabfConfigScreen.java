package com.dm.earth.cabricality.config.modmenu;

import com.dm.earth.cabricality.Cabricality;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;

import static com.dm.earth.cabricality.Cabricality.CONFIG;

public class CabfConfigScreen extends Screen {
	private final ConfigBuilder builder = ConfigBuilder.create()
												  .setTitle(Cabricality.genTranslatableText("screen", "config", "title"))
												  .transparentBackground()
												  .setSavingRunnable(CONFIG::save);
	private final ConfigEntryBuilder entryBuilder = builder.entryBuilder();

	public CabfConfigScreen(Screen parent) {
		super(Cabricality.genTranslatableText("screen", "config", "title"));
		this.builder.setParentScreen(parent);
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
		general.addEntry(entryBuilder.startBooleanToggle(Cabricality.genTranslatableText("config", "general", "background_blur"), CONFIG.backgroundBlur)
								 .setDefaultValue(true)
								 .setTooltip(Cabricality.genTranslatableText("config", "general", "background_blur", "tooltip"))
								 .setSaveConsumer(value -> CONFIG.backgroundBlur = value)
								 .build());

		general.addEntry(entryBuilder.startIntSlider(Cabricality.genTranslatableText("config", "general", "background_blur_radius"), Math.round(CONFIG.backgroundBlurRadius), 1, 128)
								 .setDefaultValue(35)
								 .setTooltip(Cabricality.genTranslatableText("config", "general", "background_blur_radius", "tooltip"))
								 .setSaveConsumer(value -> CONFIG.backgroundBlurRadius = value.floatValue())
								 .build());

		// Debug
		debug.addEntry(entryBuilder.startBooleanToggle(Cabricality.genTranslatableText("config", "debug", "debug_info"), CONFIG.debugInfo)
							   .setDefaultValue(false)
							   .setSaveConsumer(value -> CONFIG.debugInfo = value)
							   .build());

		debug.addEntry(entryBuilder.startBooleanToggle(Cabricality.genTranslatableText("config", "debug", "cleaner_log"), CONFIG.cleanerLog)
							   .setDefaultValue(true)
							   .setSaveConsumer(value -> CONFIG.cleanerLog = value)
							   .build());
	}
}
