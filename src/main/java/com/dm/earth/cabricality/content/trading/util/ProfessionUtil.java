package com.dm.earth.cabricality.content.trading.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ProfessionUtil {
	@Nullable
	public static Profession fromItem(Item item) {
		Optional<Professions> professions = Arrays.stream(Professions.values())
				.filter(p -> Objects.equals(p.get().hashString(), getHashString(Registry.ITEM.getId(item))))
				.findFirst();
		return professions.map(Professions::get).orElse(null);
	}

	public static Profession fromTradingEntry(TradingEntry entry) {
		return Arrays.stream(Professions.values())
				.filter(p -> p.get().entries().stream().anyMatch(b -> b == entry || b.getId().equals(entry.getId())))
				.findFirst().map(Professions::get).orElse(null);
	}

	private static String getHashString(Identifier id) {
		return id.getPath().replaceAll("profession_card_", "");
	}
}
