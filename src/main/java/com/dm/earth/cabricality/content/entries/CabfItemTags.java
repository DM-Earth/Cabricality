package com.dm.earth.cabricality.content.entries;

import static com.dm.earth.cabricality.ModEntry.*;
import com.dm.earth.cabricality.Cabricality;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CabfItemTags {
	public static final TagKey<Item> SAWS = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "saws"));
	// Trading Cards
	public static final TagKey<Item> TRADE_CARDS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("trade_cards"));
	public static final TagKey<Item> PROFESSION_CARDS = TagKey.of(Registry.ITEM_KEY,
			Cabricality.id("profession_cards"));
	// Jars
	public static final TagKey<Item> JARS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("jars"));
	public static final TagKey<Item> REAGENT_JARS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("jars/reagent"));
	public static final TagKey<Item> CATALYST_JARS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("jars/catalyst"));

	// Stripped
	public static final TagKey<Item> STRIPPED_LOGS = TagKey.of(Registry.ITEM_KEY, C.id("stripped_logs"));
	public static final TagKey<Item> STRIPPED_WOODS = TagKey.of(Registry.ITEM_KEY, C.id("stripped_wood"));
}
