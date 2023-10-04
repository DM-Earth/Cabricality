package dm.earth.cabricality.content.entries;

import dm.earth.cabricality.Cabricality;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static dm.earth.cabricality.Mod.Entry.C;

public class CabfItemTags {
	public static final TagKey<Item> SAWS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "saws"));
	// Trading Cards
	public static final TagKey<Item> TRADE_CARDS = TagKey.of(RegistryKeys.ITEM, Cabricality.id("trade_cards"));
	public static final TagKey<Item> PROFESSION_CARDS = TagKey.of(RegistryKeys.ITEM,
			Cabricality.id("profession_cards"));
	// Jars
	public static final TagKey<Item> JARS = TagKey.of(RegistryKeys.ITEM, Cabricality.id("jars"));
	public static final TagKey<Item> REAGENT_JARS = TagKey.of(RegistryKeys.ITEM, Cabricality.id("jars/reagent"));
	public static final TagKey<Item> CATALYST_JARS = TagKey.of(RegistryKeys.ITEM, Cabricality.id("jars/catalyst"));

	// Stripped
	public static final TagKey<Item> STRIPPED_LOGS = TagKey.of(RegistryKeys.ITEM, C.id("stripped_logs"));
	public static final TagKey<Item> STRIPPED_WOODS = TagKey.of(RegistryKeys.ITEM, C.id("stripped_wood"));
}
