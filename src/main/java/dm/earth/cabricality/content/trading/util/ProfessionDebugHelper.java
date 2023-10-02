package dm.earth.cabricality.content.trading.util;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.trading.core.TradingEntry;
import dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import net.minecraft.registry.Registries;

public class ProfessionDebugHelper {
	public static void load() {
		boolean fine = true;
		for (TradingEntry entry : TradingEntryRegistry.getEntries()) {
			if (!Registries.ITEM.containsId(entry.getItemId())) {
				Cabricality.LOGGER.error("Item " + entry.getItemId() + " from Cabricality trading system don't exist!");
				fine = false;
			}
		}
		if (!fine) throw new NullPointerException();
	}
}
