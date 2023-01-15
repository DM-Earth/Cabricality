package com.dm.earth.cabricality.content.entries;

import static com.dm.earth.cabricality.ModEntry.C;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.items.ColoredFernItem;
import com.dm.earth.cabricality.content.core.items.FlippableItem;
import com.dm.earth.cabricality.content.core.items.GlintedItem;
import com.dm.earth.cabricality.content.core.items.MechanismItem;
import com.dm.earth.cabricality.content.core.items.SawItem;
import com.dm.earth.cabricality.content.core.items.ToolMaterialIndex;
import com.dm.earth.cabricality.content.math.item.NumberItem;
import com.dm.earth.cabricality.content.math.item.OperatorItem;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.item.ProfessionCardItem;
import com.dm.earth.cabricality.content.trading.item.TradeCardItem;
import com.dm.earth.cabricality.resource.assets.gen.item.ItemModelGenerator;
import com.dm.earth.tags_binder.api.LoadTagsCallback;
import com.dm.earth.tags_binder.api.ResourceConditionCheckTagCallback;
import com.simibubi.create.AllTags.AllItemTags;
import com.simibubi.create.content.contraptions.itemAssembly.SequencedAssemblyItem;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CabfItems implements LoadTagsCallback<Item>, ResourceConditionCheckTagCallback<Item> {
	public static final class Properties {
		public static final Supplier<Item.Settings> DEFAULT =
				() -> new QuiltItemSettings().group(Cabricality.ItemGroups.MAIN_GROUP);
		public static final Supplier<Item.Settings> DEFAULT_SINGLE =
				() -> DEFAULT.get().maxCount(1);
		public static final Supplier<Item.Settings> CARD =
				() -> new QuiltItemSettings().maxCount(1);
		public static final Supplier<Item.Settings> JAR = () -> new QuiltItemSettings()
				.group(Cabricality.ItemGroups.SUBSTRATES_GROUP).maxCount(16);
		public static final Supplier<Item.Settings> DEFAULT_QUARTER =
				() -> DEFAULT.get().maxCount(16);
	}

	public static final Item SAW_BLADE = registerItemModeled("saw_blade",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "saw_blade"));
	public static final Item BASALZ_SHARD =
			registerItemModeled("basalz_shard", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "basalz_shard"));
	public static final Item BASALZ_POWDER =
			registerItemModeled("basalz_powder", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "basalz_powder"));
	public static final Item BLIZZ_CUBE = registerItemModeled("blizz_cube",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "blizz_cube"));
	public static final Item BLIZZ_POWDER =
			registerItemModeled("blizz_powder", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "blizz_powder"));
	public static final Item ZINC_SHEET = registerItemModeled("zinc_sheet",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "zinc_sheet"));
	public static final Item STONE_ROD = registerItemModeled("stone_rod",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "stone_rod"));
	public static final Item RUBBER = registerItemModeled("rubber",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "rubber"));
	public static final Item CURED_RUBBER =
			registerItemModeled("cured_rubber", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "cured_rubber"));
	public static final Item INVAR_INGOT =
			registerItemModeled("invar_ingot", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "invar_ingot"));
	public static final Item NICKEL_INGOT =
			registerItemModeled("nickel_ingot", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "nickel_ingot"));
	public static final Item NICKEL_NUGGET =
			registerItemModeled("nickel_nugget", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "nickel_nugget"));
	// Placeholder
	public static final Item RAW_NICKEL = registerItemModeled("raw_nickel",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "raw_nickel"));
	public static final Item ENDERIUM_INGOT =
			registerItemModeled("enderium_ingot", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "enderium_ingot"));
	public static final Item NICKEL_COMPOUND =
			registerItemModeled("nickel_compound", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "nickel_compound"));
	public static final Item INVAR_COMPOUND =
			registerItemModeled("invar_compound", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "invar_compound"));
	public static final Item SILICON_COMPOUND =
			registerItemModeled("silicon_compound", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "silicon_compound"));
	public static final Item RUBY = registerItemModeled("ruby", new Item(Properties.DEFAULT.get()),
			ItemModelGenerator.generated("item", "ruby"));
	public static final Item SAPPHIRE = registerItemModeled("sapphire",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "sapphire"));
	public static final Item RADIANT_SHEET =
			registerItemModeled("radiant_sheet", new GlintedItem(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "radiant_sheet"));
	public static final Item RADIANT_COIL =
			registerItemModeled("radiant_coil", new GlintedItem(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "radiant_coil"));
	public static final Item DYE_ENTANGLED_SINGULARITY =
			registerItemModeled("dye_entangled_singularity", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "dye_entangled_singularity"));
	public static final Item CHROMATIC_RESONATOR = registerItemModeled("chromatic_resonator",
			new Item(new QuiltItemSettings().maxDamage(512)
					.group(Cabricality.ItemGroups.MAIN_GROUP)),
			ItemModelGenerator.generated("item", "chromatic_resonator"));
	public static final Item FLASH_DRIVE = registerItemModeled("flash_drive",
			new Item(new QuiltItemSettings().maxDamage(512)
					.group(Cabricality.ItemGroups.MAIN_GROUP)),
			ItemModelGenerator.generated("item", "boot_medium"));
	public static final Item CIRCUIT_SCRAP =
			registerItemModeled("circuit_scrap", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "circuit_scrap"));
	public static final Item SAND_BALL =
			registerItemModeled("sand_ball", new Item(Properties.DEFAULT_QUARTER.get()),
					ItemModelGenerator.generated("item", "sand_ball"));
	public static final Item ROUGH_SAND = registerItemModeled("rough_sand",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "rough_sand"));
	public static final Item PURIFIED_SAND =
			registerItemModeled("purified_sand", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "purified_sand"));
	public static final Item COAL_COKE = registerItemModeled("coal_coke",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "coal_coke"));
	public static final Item COKE_CHUNK = registerItemModeled("coke_chunk",
			new Item(Properties.DEFAULT.get()), ItemModelGenerator.generated("item", "coke_chunk"));
	public static final Item INCOMPLETE_COKE_CHUNK = registerItemModeled("incomplete_coke_chunk",
			new SequencedAssemblyItem(Properties.DEFAULT.get()),
			ItemModelGenerator.generated("item", "incomplete_coke_chunk"));
	public static final Item EARTH_CHARGE =
			registerItemModeled("earth_charge", new Item(Properties.DEFAULT_QUARTER.get()),
					ItemModelGenerator.generated("item", "earth_charge"));
	public static final Item ICE_CHARGE =
			registerItemModeled("ice_charge", new Item(Properties.DEFAULT_QUARTER.get()),
					ItemModelGenerator.generated("item", "ice_charge"));
	public static final Item NAN =
			registerItemModeled("nan", new Item(Properties.DEFAULT_SINGLE.get()),
					ItemModelGenerator.generated("item", "math/nan"));
	public static final Item COMPUTATION_MATRIX =
			registerItem("computation_matrix", new GlintedItem(Properties.DEFAULT.get()));

	public static final Item MATTER_PLASTICS =
			registerItemModeled("matter_plastics", new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "matter_plastics"));
	public static final List<String> CRUSHED_ORES = List.of("desh", "ostrum", "calorite", "cobalt");
	public static final List<String> DUSTS =
			List.of("zinc", "desh", "ostrum", "calorite", "cobalt", "diamond", "emerald", "nickel");
	public static final List<String> PROCESSORS = List.of("calculation", "logic", "engineering");
	public static final Map<String, String> OPERATORS =
			Map.of("plus", "+", "minus", "-", "multiply", "*", "divide", "/");
	public static final List<Integer> NUMBERS = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

	public static final List<String> MATH_CASTS =
			List.of("plus", "minus", "multiply", "divide", "three", "eight");

	public static void register() {
		// Trading Cards
		Arrays.stream(Professions.values()).forEach(professionEntry -> {
			Profession profession = professionEntry.get();
			registerItemModeled("profession_card_" + profession.hashString(),
					new ProfessionCardItem(Properties.CARD.get()), ItemModelGenerator.parented(
							Cabricality.id("item", "card", "profession_card").toString()));
			profession.entries()
					.forEach(entry -> registerItemModeled("trade_card_" + entry.hashString(),
							new TradeCardItem(Properties.CARD.get()), ItemModelGenerator.parented(
									Cabricality.id("item", "card", "trade_card").toString())));
		});

		// Coins
		Arrays.stream(TradingEntry.CoinTypes.values()).forEach(coinType -> {
			registerItemModeled(coinType.getId().getPath(),
					new FlippableItem(Properties.DEFAULT.get().maxCount(16)),
					ItemModelGenerator.generated("item", "coin", coinType.getId().getPath()));

			// For Flipping
			registerItemModeled(coinType.getId().getPath() + "_top",
					new Item(Properties.CARD.get()), ItemModelGenerator.parented(Cabricality
							.id("item", "coin", coinType.getId().getPath() + "_top").toString()));
			registerItemModeled(coinType.getId().getPath() + "_bottom",
					new Item(Properties.CARD.get()),
					ItemModelGenerator.parented(
							Cabricality.id("item", "coin", coinType.getId().getPath() + "_bottom")
									.toString()));
		});

		// Mechanisms
		Arrays.stream(MechanismItem.Type.values()).forEach(type -> {
			registerItemModeled(type.getItemId().getPath(), type.getItem(),
					ItemModelGenerator.generated(type.getItem().getTextureId().getPath()));
			registerItemModeled(type.getIncompleteItemId().getPath(), type.getIncompleteItem(),
					ItemModelGenerator
							.generated(type.getItem().getIncompleteTextureId().getPath()));
		});

		// Saws
		Arrays.stream(ToolMaterialIndex.values()).forEach(materialIndex -> {
			String itemId = materialIndex.getName() + "_saw";
			registerItemModeled(itemId,
					new SawItem(materialIndex.getMaterial(), Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "tool", "saw", itemId));
		});

		// Dusts
		DUSTS.forEach(variant -> {
			String itemId = variant + "_dust";
			registerItemModeled(itemId, new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "dust", itemId));
		});

		// Crushed Ores
		CRUSHED_ORES.forEach(variant -> {
			String itemId = "crushed_" + variant + "_ore";
			registerItemModeled(itemId, new Item(Properties.DEFAULT.get()),
					ItemModelGenerator.generated("item", "crushed_ore", itemId));
		});

		// Colored Ferns
		Arrays.stream(ColoredFernItem.Entry.values()).forEach(entry -> {
			registerItemModeled(entry.name + "_slime_fern_leaf",
					new ColoredFernItem.SlimeFernLeaf(entry.tint),
					ItemModelGenerator.generated("item", "fern", "slime_fern_leaf"));
			registerItemModeled(entry.name + "_slime_fern_paste",
					new ColoredFernItem.SlimeFernPaste(entry.tint),
					ItemModelGenerator.generated("item", "fern", "slime_fern_paste"));
		});

		// Incomplete Processors
		PROCESSORS.forEach(type -> registerItemModeled("incomplete_" + type + "_processor",
				new SequencedAssemblyItem(new QuiltItemSettings()), ItemModelGenerator
						.generated("item/processor", "incomplete_" + type + "_processor")));

		// Math
		NUMBERS.forEach(num -> registerItemModeled(NumberItem.getNumberItemName(num),
				new NumberItem(Properties.DEFAULT_SINGLE.get()),
				ItemModelGenerator.generated("item/math/number", "number_" + num)));
		OPERATORS.forEach((key, value) -> registerItemModeled(key,
				new OperatorItem(value, Properties.DEFAULT_SINGLE.get()),
				ItemModelGenerator.generated("item/math/operator", key)));
		MATH_CASTS.forEach(
				str -> registerItemModeled(str + "_cast", new Item(Properties.DEFAULT_SINGLE.get()),
						ItemModelGenerator.generated("item/math/cast", str + "_cast")));

		var instance = new CabfItems();
		LoadTagsCallback.ITEM.register(instance);
		ResourceConditionCheckTagCallback.ITEM.register(instance);
	}

	private static Item registerItemModeled(String name, Item item, JModel model) {
		Cabricality.RRPs.CLIENT_RESOURCES.addModel(model, Cabricality.id("item", name));
		return registerItem(name, item);
	}

	private static Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, Cabricality.id(name), item);
	}

	@Override
	public ActionResult apply(TagKey<Item> key) {
		Identifier id = key.id();
		if (id.getNamespace().equals("c")) {
			String name = id.getPath();
			if (name.equals("ingots/enderium"))
				return ActionResult.SUCCESS;
			if (name.equals("ingots/invar"))
				return ActionResult.SUCCESS;
			if (name.equals("ingots/nickel"))
				return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}

	@Override
	public void load(TagHandler<Item> handler) {
		Arrays.stream(TradingEntry.CoinTypes.values())
				.forEach(coinType -> handler.register(C.id(coinType.getName() + "_coins"),
						Registry.ITEM.get(coinType.getId())));
		Arrays.stream(ToolMaterialIndex.values())
				.forEach(materialIndex -> handler.register(CabfItemTags.SAWS,
						Registry.ITEM.get(Cabricality.id(materialIndex.getName() + "_saw"))));
		DUSTS.forEach(variant -> handler.register(C.id(variant, "_dusts"),
				Registry.ITEM.get(Cabricality.id(variant + "_dust"))));
		CRUSHED_ORES.forEach(variant -> handler.register(AllItemTags.CRUSHED_ORES.tag,
				Registry.ITEM.get(Cabricality.id("crushed_" + variant + "_ore"))));

		handler.register(C.id("enderium_ingots"), ENDERIUM_INGOT);
		handler.register(C.id("invar_ingots"), INVAR_INGOT);
		handler.register(C.id("nickel_ingots"), NICKEL_INGOT);

		handler.register(C.id("ingots", "enderium"), ENDERIUM_INGOT);
		handler.register(C.id("ingots", "invar"), INVAR_INGOT);
		handler.register(C.id("ingots", "nickel"), NICKEL_INGOT);

		handler.register(C.id("nickel_nuggets"), NICKEL_NUGGET);
		handler.register(C.id("nuggets/nickel"), NICKEL_NUGGET);
	}
}
