package com.dm.earth.cabricality.content.trading.data.tag;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import net.devtech.arrp.json.tags.JTag;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class TradeTags {
	public static final TagKey<Item> TRADE_CARDS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("trade_cards"));
	public static final TagKey<Item> PROFESSION_CARDS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("profession_cards"));

	public static void load() {
		JTag tradeCardTags = new JTag();
		JTag professionCardTags = new JTag();
		for (Professions professionEntry : Professions.values()) {
			professionCardTags.add(Cabricality.id("profession_card_" + professionEntry.get().hashString()));
			for (TradingEntry entry : professionEntry.get().entries())
				tradeCardTags.add(Cabricality.id("trade_card_" + entry.hashString()));
		}
		Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/" + TRADE_CARDS.id().getPath()), tradeCardTags);
		Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/" + PROFESSION_CARDS.id().getPath()), professionCardTags);
	}
}
