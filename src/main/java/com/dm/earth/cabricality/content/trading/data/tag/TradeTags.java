package com.dm.earth.cabricality.content.trading.data.tag;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.entries.CabfItemTags;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;

import net.devtech.arrp.json.tags.JTag;

public class TradeTags {
	public static void load() {
		JTag tradeCardTags = new JTag();
		JTag professionCardTags = new JTag();
		for (Professions professionEntry : Professions.values()) {
			professionCardTags.add(Cabricality.id("profession_card_" + professionEntry.get().hashString()));
			for (TradingEntry entry : professionEntry.get().entries())
				tradeCardTags.add(Cabricality.id("trade_card_" + entry.hashString()));
		}
		Cabricality.RRPs.SERVER_RESOURCES.addTag(Cabricality.id("items/" + CabfItemTags.TRADE_CARDS.id().getPath()), tradeCardTags);
		Cabricality.RRPs.SERVER_RESOURCES.addTag(Cabricality.id("items/" + CabfItemTags.PROFESSION_CARDS.id().getPath()), professionCardTags);
	}
}
