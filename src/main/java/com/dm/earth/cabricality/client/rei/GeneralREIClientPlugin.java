package com.dm.earth.cabricality.client.rei;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.debug.CabfDebugger;
import com.github.alexnijjar.ad_astra.registry.ModItems;
import com.github.reoseah.catwalksinc.CIncItems;
import com.google.common.collect.ImmutableList;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.steven.indrev.registry.IRItemRegistry;
import net.minecraft.util.registry.Registry;

import static com.dm.earth.cabricality.ModEntry.IR;

@SuppressWarnings("UnstableApiUsage")
public class GeneralREIClientPlugin implements REIClientPlugin {
	@Override
	public void registerCollapsibleEntries(CollapsibleEntryRegistry registry) {
		CabfDebugger.debug("Registering Collapsible Entries for Generals");

		// Deprecations
		registry.group(Cabricality.id("deprecations"),
				Cabricality.genTranslatableText("tag", "deprecations"),
				EntryIngredients.ofItems(ImmutableList.of(
						// Wrenches
						ModItems.WRENCH, IRItemRegistry.INSTANCE.getWRENCH(), CIncItems.WRENCH,

						// Hammers
						ModItems.HAMMER, IRItemRegistry.INSTANCE.getHAMMER(),

						// Indrev
						Registry.ITEM.get(IR.id("gold_plate")), Registry.ITEM.get(IR.id("iron_plate")), Registry.ITEM.get(IR.id("copper_plate")),

						// Ad Astra
						ModItems.COMPRESSED_STEEL, ModItems.IRON_PLATE
				)));
	}
}
