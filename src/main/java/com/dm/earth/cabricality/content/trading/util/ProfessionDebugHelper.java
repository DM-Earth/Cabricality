package com.dm.earth.cabricality.content.trading.util;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import net.minecraft.util.registry.Registry;

public class ProfessionDebugHelper {
	public static void load() {
		boolean fine = true;
		for (TradingEntry entry : TradingEntryRegistry.getEntries()) {
			if (!Registry.ITEM.containsId(entry.getItemId())) {
				Cabricality.LOGGER.error("Item " + entry.getItemId() + " from Cabricality trading system don't exist!");
				fine = false;
			}
		}
		if (!fine) throw new NullPointerException();
	}
}
