package com.dm.earth.cabricality.content.trading.core;

import com.dm.earth.cabricality.content.trading.item.AbstractTradeCardItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TradingEntryRegistry {
	public static ArrayList<TradingEntry> entries = new ArrayList<>();

	public static TradingEntry register(TradingEntry entry) {
		entries.add(entry);
		return entry;
	}

	@Nullable
	public static TradingEntry get(Identifier id) {
		for (TradingEntry entry : entries) if (entry.getId().equals(id)) return entry;
		return null;
	}

	public static List<TradingEntry> getEntries() {
		return entries;
	}

	@Nullable
	public static TradingEntry fromHashString(String hash) {
		for (TradingEntry entry : entries) if (Objects.equals(entry.hashString(), hash)) return entry;
		return null;
	}

	public static TradingEntry fromItem(AbstractTradeCardItem item) {
		Identifier id = Registry.ITEM.getId(item);
		return fromHashString(id.getPath().replaceAll(item.getType() + "_", ""));
	}
}
