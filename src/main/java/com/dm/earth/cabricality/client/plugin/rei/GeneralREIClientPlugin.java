package com.dm.earth.cabricality.client.plugin.rei;

import com.dm.earth.cabricality.util.debug.CabfDebugger;
import com.github.alexnijjar.ad_astra.registry.ModItems;
import com.github.reoseah.catwalksinc.CIncItems;
import com.google.common.collect.ImmutableList;
import io.github.coolmineman.bitsandchisels.BitItem;
import io.github.coolmineman.bitsandchisels.BitsAndChisels;
import me.shedaniel.rei.api.client.entry.filtering.base.BasicFilteringRule;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.steven.indrev.registry.IRItemRegistry;
import net.minecraft.util.registry.Registry;

import static com.dm.earth.cabricality.ModEntry.IR;

@SuppressWarnings("UnstableApiUsage")
public class GeneralREIClientPlugin implements REIClientPlugin {
	@Override
	public void registerBasicEntryFiltering(BasicFilteringRule<?> rule) {
		CabfDebugger.debug("Filtering Entries");

		// Deprecations
		rule.hide(EntryIngredients.ofItems(ImmutableList.of(
				// Wrenches
				ModItems.WRENCH, IRItemRegistry.INSTANCE.getWRENCH(), CIncItems.WRENCH, BitsAndChisels.WRENCH_ITEM,

				// Hammers
				ModItems.HAMMER, IRItemRegistry.INSTANCE.getHAMMER(),

				// Indrev
				Registry.ITEM.get(IR.id("gold_plate")), Registry.ITEM.get(IR.id("iron_plate")), Registry.ITEM.get(IR.id("copper_plate")),

				// Ad Astra
				ModItems.COMPRESSED_STEEL, ModItems.IRON_PLATE
		)));
	}
}
