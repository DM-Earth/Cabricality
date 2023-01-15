package com.dm.earth.cabricality.content.trading.data.tag;

import java.util.Arrays;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.entries.CabfItemTags;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.tags_binder.api.LoadTagsCallback;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class TradeTags implements LoadTagsCallback<Item> {
	public static void load() {
		LoadTagsCallback.ITEM.register(new TradeTags());
	}

	@Override
	public void load(TagHandler<Item> handler) {
		Arrays.stream(Professions.values()).forEach(professionEntry -> {
			handler.register(CabfItemTags.PROFESSION_CARDS, Registry.ITEM
					.get(Cabricality.id("profession_card_" + professionEntry.get().hashString())));
			professionEntry.get().entries()
					.forEach(entry -> handler.register(CabfItemTags.TRADE_CARDS,
							Registry.ITEM.get(Cabricality.id("trade_card_" + entry.hashString()))));
		});
	}
}
