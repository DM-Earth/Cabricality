package com.dm.earth.cabricality.content.entries;

import static com.dm.earth.cabricality.ModEntry.C;

import java.util.List;

import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.items.MechanismItem;
import com.dm.earth.cabricality.content.core.items.SawItem;
import com.dm.earth.cabricality.content.core.items.ToolMaterialIndex;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.item.ProfessionCardItem;
import com.dm.earth.cabricality.content.trading.item.TradeCardItem;
import com.dm.earth.cabricality.resource.assets.gen.item.ItemModelGenerator;
import com.simibubi.create.AllTags.AllItemTags;

import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.tags.JTag;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CabfItems {
	public static final Item SAW_BLADE = registerItemModeled("saw_blade", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/saw_blade"));
	public static final Item BASALZ_SHARD = registerItemModeled("basalz_shard", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/basalz_shard"));
	public static final Item BASALZ_POWDER = registerItemModeled("basalz_powder", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/basalz_powder"));
	public static final Item BLIZZ_CUBE = registerItemModeled("blizz_cube", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/blizz_cube"));
	public static final Item BLIZZ_POWDER = registerItemModeled("blizz_powder", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/blizz_powder"));
	public static final Item ZINC_SHEET = registerItemModeled("zinc_sheet", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/zinc_sheet"));
	public static final Item STONE_ROD = registerItemModeled("stone_rod", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/stone_rod"));
	public static final Item RUBBER = registerItemModeled("rubber", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/rubber"));
	public static final Item CURED_RUBBER = registerItemModeled("cured_rubber", new Item(Properties.DEFAULT),
			ItemModelGenerator.generated("item/cured_rubber"));

	public static final List<String> CRUSHED_ORES = List.of("desh", "ostrum", "calorite", "cobalt");
	public static final List<String> DUSTS = List.of("zinc", "desh", "ostrum", "calorite", "cobalt", "diamond",
			"emerald", "nickel");

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

		// Saws
		JTag sawsTag = new JTag();
		for (ToolMaterialIndex materialIndex : ToolMaterialIndex.values()) {
			String itemId = materialIndex.getName() + "_saw";
			registerItemModeled(itemId, new SawItem(materialIndex.getMaterial(), Properties.DEFAULT_SINGLE),
					ItemModelGenerator.generated("item/tool/saw", itemId));
			sawsTag.add(Cabricality.id(itemId));
		}
		Cabricality.SERVER_RESOURCES.addTag(C.id("items/" + CabfItemTags.SAWS.id().getPath()), sawsTag);

		// Dusts
		for (String variant : DUSTS) {
			JTag tagT = new JTag();
			String itemId = variant + "_dust";
			registerItemModeled(itemId, new Item(Properties.DEFAULT),
					ItemModelGenerator.generated("item/dust", itemId));
			tagT.add(Cabricality.id(itemId));
			Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/" + variant + "_dusts"), tagT);
			Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/dusts/" + variant), tagT);
		}

		// Crushed Ores
		JTag crushedOresTag = new JTag();
		for (String variant : CRUSHED_ORES) {
			String itemId = "crushed_" + variant + "_ore";
			registerItemModeled(itemId, new Item(Properties.DEFAULT),
					ItemModelGenerator.generated("item/crushed_ore", itemId));
			crushedOresTag.add(Cabricality.id(itemId));
		}
		Cabricality.SERVER_RESOURCES.addTag(
				new Identifier("create", "items/" + AllItemTags.CRUSHED_ORES.tag.id().getPath()), crushedOresTag);
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
		public static final Item.Settings JAR = new QuiltItemSettings().group(Cabricality.SUBSTRATES_GROUP)
				.maxCount(16);
	}
}
