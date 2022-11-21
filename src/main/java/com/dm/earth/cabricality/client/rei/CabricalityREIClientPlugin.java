package com.dm.earth.cabricality.client.rei;

import static com.dm.earth.cabricality.content.alchemist.data.JarData.CATALYST_JARS;
import static com.dm.earth.cabricality.content.alchemist.data.JarData.REAGENT_JARS;
import static com.dm.earth.cabricality.content.trading.data.tag.TradeTags.PROFESSION_CARDS;
import static com.dm.earth.cabricality.content.trading.data.tag.TradeTags.TRADE_CARDS;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.util.CabfDebugger;

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
		registry.group(Cabricality.id("trade_cards"), new TranslatableText(CabricalityClient.genTranslationKey("tag", TRADE_CARDS.id().getPath())), EntryIngredients.ofItemTag(TRADE_CARDS));
		registry.group(Cabricality.id("profession_cards"), new TranslatableText(CabricalityClient.genTranslationKey("tag", PROFESSION_CARDS.id().getPath())), EntryIngredients.ofItemTag(PROFESSION_CARDS));

		// Alchemist Jars
		registry.group(Cabricality.id("catalyst_jars"), new TranslatableText(CabricalityClient.genTranslationKey("tag", CATALYST_JARS.id().getPath())), EntryIngredients.ofItemTag(CATALYST_JARS));
		registry.group(Cabricality.id("reagent_jars"), new TranslatableText(CabricalityClient.genTranslationKey("tag", REAGENT_JARS.id().getPath())), EntryIngredients.ofItemTag(REAGENT_JARS));
	}
}
