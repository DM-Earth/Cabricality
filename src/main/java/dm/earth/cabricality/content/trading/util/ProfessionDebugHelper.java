package dm.earth.cabricality.content.trading.util;

import dm.earth.cabricality.content.trading.core.TradingEntry;
import dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import dm.earth.cabricality.lib.util.log.CabfLogger;
import net.minecraft.registry.Registries;

public class ProfessionDebugHelper {
	public static void load() {
		boolean fine = true;
		for (TradingEntry entry : TradingEntryRegistry.getEntries()) {
			if (!Registries.ITEM.containsId(entry.getItemId())) {
				CabfLogger.error("Item " + entry.getItemId() + " from Cabricality trading system don't exist!");
				fine = false;
			}
		}
		if (!fine) throw new NullPointerException();
	}
}
