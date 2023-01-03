package com.dm.earth.cabricality.client.rei;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.entries.CabfItemTags;
import com.dm.earth.cabricality.util.debug.CabfDebugger;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.text.TranslatableText;

@SuppressWarnings("UnstableApiUsage")
public class CabricalityREIClientPlugin implements REIClientPlugin {
	@Override
	public void registerCollapsibleEntries(CollapsibleEntryRegistry registry) {
		CabfDebugger.debug("Registering Collapsible Entries");

		// Trading
		registry.group(Cabricality.id("trade_cards"),
				new TranslatableText(Cabricality.genTranslationKey("tag",
						CabfItemTags.TRADE_CARDS.id().getPath())),
				EntryIngredients.ofItemTag(CabfItemTags.TRADE_CARDS));
		registry.group(Cabricality.id("profession_cards"),
				new TranslatableText(Cabricality.genTranslationKey("tag",
						CabfItemTags.PROFESSION_CARDS.id().getPath())),
				EntryIngredients.ofItemTag(CabfItemTags.PROFESSION_CARDS));

		// Alchemist Jars
		registry.group(Cabricality.id("catalyst_jars"),
				new TranslatableText(Cabricality.genTranslationKey("tag",
						CabfItemTags.CATALYST_JARS.id().getPath())),
				EntryIngredients.ofItemTag(CabfItemTags.CATALYST_JARS));
		registry.group(Cabricality.id("reagent_jars"),
				new TranslatableText(Cabricality.genTranslationKey("tag",
						CabfItemTags.REAGENT_JARS.id().getPath())),
				EntryIngredients.ofItemTag(CabfItemTags.REAGENT_JARS));
	}
}
