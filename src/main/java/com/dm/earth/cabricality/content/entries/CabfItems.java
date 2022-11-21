package com.dm.earth.cabricality.content.entries;

import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.threads.items.MechanismItem;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.item.ProfessionCardItem;
import com.dm.earth.cabricality.content.trading.item.TradeCardItem;
import com.dm.earth.cabricality.resource.assets.gen.item.ItemModelGenerator;

import net.devtech.arrp.json.models.JModel;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class CabfItems {
	public static final Item BASALZ_SHARD = registerItemModeled("basalz_shard", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/basalz_shard"));
	public static final Item BASALZ_POWDER = registerItemModeled("basalz_powder", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/basalz_powder"));
	public static final Item BLIZZ_CUBE = registerItemModeled("blizz_cube", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/blizz_cube"));
	public static final Item BLIZZ_POWDER = registerItemModeled("blizz_powder", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/blizz_powder"));

	public static void register() {
		// Trading Cards
		for (Professions professionEntry : Professions.values()) {
			Profession profession = professionEntry.get();
			registerItemModeled("profession_card_" + profession.hashString(), new ProfessionCardItem(Properties.CARD),
					ItemModelGenerator.parented(Cabricality.id("item/card/profession_card").toString()));
			for (TradingEntry entry : profession.entries())
				registerItemModeled("trade_card_" + entry.hashString(), new TradeCardItem(Properties.CARD),
						ItemModelGenerator.parented(Cabricality.id("item/card/trade_card").toString()));
		}

		// Coins
		for (TradingEntry.CoinTypes coinType : TradingEntry.CoinTypes.values())
			registerItemModeled(coinType.getId().getPath(), new Item(Properties.DEFAULT.maxCount(16)),
					ItemModelGenerator.generated("item/coin", coinType.getId().getPath()));

		// Mechanisms
		for (MechanismItem.Type type : MechanismItem.Type.values()) {
			registerItemModeled(type.getItemId().getPath(), type.getItem(),
					ItemModelGenerator.generated(type.getItem().getTextureId().getPath()));
			registerItemModeled(type.getIncompleteItemId().getPath(), type.getIncompleteItem(),
					ItemModelGenerator.generated(type.getItem().getIncompleteTextureId().getPath()));
		}
	}

	private static Item registerItemModeled(String name, Item item, JModel model) {
		Cabricality.CLIENT_RESOURCES.addModel(model, Cabricality.id("item/" + name));
		return registerItem(name, item);
	}

	private static Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, Cabricality.id(name), item);
	}

	public static final class Properties {
		public static final Item.Settings DEFAULT = new QuiltItemSettings().group(Cabricality.MAIN_GROUP);
		public static final Item.Settings DEFAULT_SINGLE = DEFAULT.maxCount(1);
		public static final Item.Settings CARD = new QuiltItemSettings().maxCount(1);
		public static final Item.Settings JAR = new QuiltItemSettings().group(Cabricality.SUBSTRATES_GROUP).maxCount(16);
	}
}
