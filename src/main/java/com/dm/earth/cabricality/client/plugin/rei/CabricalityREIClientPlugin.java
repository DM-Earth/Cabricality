package com.dm.earth.cabricality.client.plugin.rei;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.entries.CabfItemTags;
import com.dm.earth.cabricality.util.debug.CabfDebugger;
import com.github.alexnijjar.ad_astra.registry.ModItems;
import com.github.reoseah.catwalksinc.CIncItems;
import com.google.common.collect.ImmutableList;
import me.shedaniel.rei.api.client.entry.filtering.base.BasicFilteringRule;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.steven.indrev.registry.IRItemRegistry;
import net.minecraft.util.registry.Registry;

import static com.dm.earth.cabricality.ModEntry.IR;

@SuppressWarnings("UnstableApiUsage")
public class CabricalityREIClientPlugin implements REIClientPlugin {
	@Override
	public void registerCollapsibleEntries(CollapsibleEntryRegistry registry) {
		CabfDebugger.debug("Registering Collapsible Entries");

		// Trading
		registry.group(Cabricality.id("trade_cards"),
				Cabricality.genTranslatableText("tag",
						CabfItemTags.TRADE_CARDS.id().getPath()),
				EntryIngredients.ofItemTag(CabfItemTags.TRADE_CARDS));
		registry.group(Cabricality.id("profession_cards"),
				Cabricality.genTranslatableText("tag",
						CabfItemTags.PROFESSION_CARDS.id().getPath()),
				EntryIngredients.ofItemTag(CabfItemTags.PROFESSION_CARDS));

		// Alchemist Jars
		registry.group(Cabricality.id("catalyst_jars"),
				Cabricality.genTranslatableText("tag",
						CabfItemTags.CATALYST_JARS.id().getPath()),
				EntryIngredients.ofItemTag(CabfItemTags.CATALYST_JARS));
		registry.group(Cabricality.id("reagent_jars"),
				Cabricality.genTranslatableText("tag",
						CabfItemTags.REAGENT_JARS.id().getPath()),
				EntryIngredients.ofItemTag(CabfItemTags.REAGENT_JARS));
	}
}
