package dm.earth.cabricality.content.entries;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.core.items.ColoredFernItem;
import dm.earth.cabricality.content.core.items.FlippableItem;
import dm.earth.cabricality.content.core.items.GlintedItem;
import dm.earth.cabricality.content.core.items.MechanismItem;
import dm.earth.cabricality.content.core.items.SawItem;
import dm.earth.cabricality.content.core.items.ToolMaterialIndex;
import dm.earth.cabricality.content.math.item.NumberItem;
import dm.earth.cabricality.content.math.item.OperatorItem;
import dm.earth.cabricality.content.trading.Professions;
import dm.earth.cabricality.content.trading.core.Profession;
import dm.earth.cabricality.content.trading.core.TradingEntry;
import dm.earth.cabricality.content.trading.item.ProfessionCardItem;
import dm.earth.cabricality.content.trading.item.TradeCardItem;
import dm.earth.cabricality.lib.resource.assets.gen.item.ItemModelGenerator;
import com.dm.earth.tags_binder.api.LoadTagsCallback;
import com.dm.earth.tags_binder.api.ResourceConditionCheckTagCallback;
import com.simibubi.create.AllTags.AllItemTags;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static dm.earth.cabricality.Mod.Entry.C;

public class CabfItems implements LoadTagsCallback<Item>, ResourceConditionCheckTagCallback<Item> {
	private static final HashMap<Optional<RegistryKey<Item>>, Optional<RegistryKey<ItemGroup>>> ITEM_GROUPING_MAP = new HashMap<java.util.Optional<RegistryKey<Item>>, java.util.Optional<RegistryKey<ItemGroup>>>();

	public static final class Suppliers {
		public static final Supplier<Item.Settings> DEFAULT = FabricItemSettings::new;
		public static final Supplier<Item.Settings> DEFAULT_SINGLE = () -> DEFAULT.get().maxCount(1);
		public static final Supplier<Item.Settings> DEFAULT_QUARTER = () -> DEFAULT.get().maxCount(16);
	}

	public static final Item SAW_BLADE = registerItemModeled(
			"saw_blade",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "saw_blade"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item BASALZ_SHARD = registerItemModeled(
			"basalz_shard",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "basalz_shard"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item BASALZ_POWDER = registerItemModeled(
			"basalz_powder",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "basalz_powder"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item BLIZZ_CUBE = registerItemModeled(
			"blizz_cube",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "blizz_cube"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item BLIZZ_POWDER = registerItemModeled(
			"blizz_powder",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "blizz_powder"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item ZINC_SHEET = registerItemModeled(
			"zinc_sheet",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "zinc_sheet"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item STONE_ROD = registerItemModeled(
			"stone_rod",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "stone_rod"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item RUBBER = registerItemModeled(
			"rubber",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "rubber"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item CURED_RUBBER = registerItemModeled(
			"cured_rubber",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "cured_rubber"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item INVAR_INGOT = registerItemModeled(
			"invar_ingot",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "invar_ingot"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item NICKEL_INGOT = registerItemModeled(
			"nickel_ingot",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "nickel_ingot"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item NICKEL_NUGGET = registerItemModeled(
			"nickel_nugget",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "nickel_nugget"),
			Cabricality.ItemGroups.GENERAL
	);
	// Placeholder
	public static final Item RAW_NICKEL = registerItemModeled(
			"raw_nickel",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "raw_nickel"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item ENDERIUM_INGOT = registerItemModeled(
			"enderium_ingot",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "enderium_ingot"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item NICKEL_COMPOUND = registerItemModeled(
			"nickel_compound",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "nickel_compound"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item INVAR_COMPOUND = registerItemModeled(
			"invar_compound",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "invar_compound"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item SILICON_COMPOUND = registerItemModeled(
			"silicon_compound",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "silicon_compound"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item RUBY = registerItemModeled(
			"ruby",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "ruby"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item SAPPHIRE = registerItemModeled(
			"sapphire",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "sapphire"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item RADIANT_SHEET = registerItemModeled(
			"radiant_sheet",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "radiant_sheet"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item RADIANT_COIL = registerItemModeled(
			"radiant_coil",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "radiant_coil"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item DYE_ENTANGLED_SINGULARITY = registerItemModeled(
			"dye_entangled_singularity",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "dye_entangled_singularity"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item CHROMATIC_RESONATOR = registerItemModeled(
			"chromatic_resonator",
			new Item(Suppliers.DEFAULT.get().maxDamage(512)),
			ItemModelGenerator.generated("item", "chromatic_resonator"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item FLASH_DRIVE = registerItemModeled(
			"flash_drive",
			new Item(Suppliers.DEFAULT.get().maxDamage(512)),
			ItemModelGenerator.generated("item", "boot_medium"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item CIRCUIT_SCRAP = registerItemModeled(
			"circuit_scrap",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "circuit_scrap"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item SAND_BALL = registerItemModeled(
			"sand_ball",
			new Item(Suppliers.DEFAULT_QUARTER.get()),
			ItemModelGenerator.generated("item", "sand_ball"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item ROUGH_SAND = registerItemModeled(
			"rough_sand",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "rough_sand"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item PURIFIED_SAND = registerItemModeled(
			"purified_sand",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "purified_sand"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item COAL_COKE = registerItemModeled(
			"coal_coke",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "coal_coke"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item COKE_CHUNK = registerItemModeled(
			"coke_chunk",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "coke_chunk"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item INCOMPLETE_COKE_CHUNK = registerItemModeled(
			"incomplete_coke_chunk",
			new SequencedAssemblyItem(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "incomplete_coke_chunk"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item EARTH_CHARGE = registerItemModeled(
			"earth_charge",
			new Item(Suppliers.DEFAULT_QUARTER.get()),
			ItemModelGenerator.generated("item", "earth_charge"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item ICE_CHARGE = registerItemModeled(
			"ice_charge",
			new Item(Suppliers.DEFAULT_QUARTER.get()),
			ItemModelGenerator.generated("item", "ice_charge"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item NAN = registerItemModeled(
			"nan",
			new Item(Suppliers.DEFAULT_SINGLE.get()),
			ItemModelGenerator.generated("item", "math/nan"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item COMPUTATION_MATRIX = registerItem(
			"computation_matrix",
			new GlintedItem(Suppliers.DEFAULT.get()),
			Cabricality.ItemGroups.GENERAL
	);
	public static final Item AQUAMARINE_QUARTZ = registerItemModeled(
			"aquamarine_quartz",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "aquamarine_quartz"),
			Cabricality.ItemGroups.GENERAL
	);

	public static final Item MATTER_PLASTICS = registerItemModeled(
			"matter_plastics",
			new Item(Suppliers.DEFAULT.get()),
			ItemModelGenerator.generated("item", "matter_plastics"),
			Cabricality.ItemGroups.GENERAL
	);
	public static final List<String> CRUSHED_RAW_MATERIALS = List.of("desh", "ostrum", "calorite", "cobalt");
	public static final List<String> DUSTS = List.of("zinc", "desh", "ostrum", "calorite", "cobalt", "diamond", "emerald", "nickel");
	public static final List<String> PROCESSORS = List.of("calculation", "logic", "engineering");
	public static final Map<String, String> OPERATORS = Map.of(
			"plus", "+",
			"minus", "-",
			"multiply", "*",
			"divide", "/"
	);
	public static final List<Integer> NUMBERS = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	public static final List<String> MATH_CASTS = List.of("plus", "minus", "multiply", "divide", "three", "eight");

	public static void registerItemGroupingEvent() {
		ITEM_GROUPING_MAP.entrySet().stream()
				.filter(entry -> entry.getKey().isPresent() && entry.getValue().isPresent())
				.map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey().get(), entry.getValue().get()))
				.collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
				.forEach((itemGroup, items) -> ItemGroupEvents.modifyEntriesEvent(itemGroup)
						.register(content ->
								items.forEach(itemRegistryKey -> content.addItem(Registries.ITEM.get(itemRegistryKey)))));
	}

	public static void register() {
		// Trading Cards
		Arrays.stream(Professions.values()).forEach(professionEntry -> {
			Profession profession = professionEntry.get();
			registerItemModeled(
					"profession_card_" + profession.hashString(),
					new ProfessionCardItem(Suppliers.DEFAULT_SINGLE.get()),
					ItemModelGenerator.parented(Cabricality.id("item", "card", "profession_card").toString()),
					Cabricality.ItemGroups.GENERAL
			);

			profession.entries().forEach(entry -> registerItemModeled(
					"trade_card_" + entry.hashString(),
					new TradeCardItem(Suppliers.DEFAULT_SINGLE.get()),
					ItemModelGenerator.parented(Cabricality.id("item", "card", "trade_card").toString()),
					Cabricality.ItemGroups.GENERAL
			));
		});

		// Coins
		Arrays.stream(TradingEntry.CoinTypes.values()).forEach(coinType -> {
			registerItemModeled(
					coinType.getId().getPath(),
					new FlippableItem(Suppliers.DEFAULT.get().maxCount(16)),
					ItemModelGenerator.generated("item", "coin", coinType.getId().getPath()),
					Cabricality.ItemGroups.GENERAL
			);

			// For Flipping
			registerItemModeled(
					coinType.getId().getPath() + "_top",
					new Item(Suppliers.DEFAULT_SINGLE.get()),
					ItemModelGenerator.parented(Cabricality
							.id("item", "coin", coinType.getId().getPath() + "_top")
							.toString())
			);
			registerItemModeled(
					coinType.getId().getPath() + "_bottom",
					new Item(Suppliers.DEFAULT_SINGLE.get()),
					ItemModelGenerator.parented(Cabricality
							.id("item", "coin", coinType.getId().getPath() + "_bottom")
							.toString())
			);
		});

		// Mechanisms
		Arrays.stream(MechanismItem.Type.values()).forEach(type -> {
			registerItemModeled(
					type.getItemId().getPath(),
					type.getItem(),
					ItemModelGenerator.generated(type.getItem().getTextureId().getPath()),
					Cabricality.ItemGroups.GENERAL
			);
			registerItemModeled(
					type.getIncompleteItemId().getPath(),
					type.getIncompleteItem(),
					ItemModelGenerator.generated(type.getItem().getIncompleteTextureId().getPath()),
					Cabricality.ItemGroups.GENERAL
			);
		});

		// Saws
		Arrays.stream(ToolMaterialIndex.values()).forEach(materialIndex -> {
			String itemId = materialIndex.getName() + "_saw";
			registerItemModeled(
					itemId,
					new SawItem(materialIndex.getMaterial(), Suppliers.DEFAULT.get()),
					ItemModelGenerator.generated("item", "tool", "saw", itemId),
					Cabricality.ItemGroups.GENERAL
			);
		});

		// Dusts
		DUSTS.forEach(variant -> {
			String itemId = variant + "_dust";
			registerItemModeled(
					itemId,
					new Item(Suppliers.DEFAULT.get()),
					ItemModelGenerator.generated("item", "dust", itemId),
					Cabricality.ItemGroups.GENERAL
			);
		});

		// Crushed Ores
		CRUSHED_RAW_MATERIALS.forEach(variant -> {
			String itemId = "crushed_raw_" + variant;
			registerItemModeled(
					itemId,
					new Item(Suppliers.DEFAULT.get()),
					ItemModelGenerator.generated("item", "crushed_raw_material", itemId),
					Cabricality.ItemGroups.GENERAL
			);
		});

		// Colored Ferns
		Arrays.stream(ColoredFernItem.Entry.values()).forEach(entry -> {
			registerItemModeled(
					entry.name + "_slime_fern_leaf",
					new ColoredFernItem.SlimeFernLeaf(entry.tint),
					ItemModelGenerator.generated("item", "fern", "slime_fern_leaf")
			);
			registerItemModeled(
					entry.name + "_slime_fern_paste",
					new ColoredFernItem.SlimeFernPaste(entry.tint),
					ItemModelGenerator.generated("item", "fern", "slime_fern_paste")
			);
		});

		// Incomplete Processors
		PROCESSORS.forEach(type -> registerItemModeled(
				"incomplete_" + type + "_processor",
				new SequencedAssemblyItem(new FabricItemSettings()),
				ItemModelGenerator.generated("item/processor", "incomplete_" + type + "_processor")
		));

		// Math
		NUMBERS.forEach(number -> registerItemModeled(
				NumberItem.getNumberItemName(number),
				new NumberItem(Suppliers.DEFAULT_SINGLE.get()),
				ItemModelGenerator.generated("item/math/number", "number_" + number),
				Cabricality.ItemGroups.GENERAL
		));

		OPERATORS.forEach((operator, value) -> registerItemModeled(
				operator,
				new OperatorItem(value, Suppliers.DEFAULT_SINGLE.get()),
				ItemModelGenerator.generated("item/math/operator", operator),
				Cabricality.ItemGroups.GENERAL
		));

		MATH_CASTS.forEach(cast -> registerItemModeled(
				cast + "_cast",
				new Item(Suppliers.DEFAULT_SINGLE.get()),
				ItemModelGenerator.generated("item/math/cast", cast + "_cast"),
				Cabricality.ItemGroups.GENERAL
		));

		var instance = new CabfItems();
		LoadTagsCallback.ITEM.register(instance);
		ResourceConditionCheckTagCallback.ITEM.register(instance);

		registerItemGroupingEvent();
	}

	private static Item registerItemModeled(String name, Item item, ModelJsonBuilder model, @Nullable ItemGroup itemGroup) {
		Cabricality.RRPs.CLIENT_RESOURCES.addModel(Cabricality.id("item", name), model);
		return registerItem(name, item, itemGroup);
	}

	private static Item registerItemModeled(String name, Item item, ModelJsonBuilder model) {
		return registerItemModeled(name, item, model, null);
	}

	private static Item registerItem(String name, Item item, @Nullable ItemGroup itemGroup) {
		if (itemGroup != null) ITEM_GROUPING_MAP.put(Registries.ITEM.getKey(item), Registries.ITEM_GROUP.getKey(itemGroup));
		return Registry.register(Registries.ITEM, Cabricality.id(name), item);
	}

	private static Item registerItem(String name, Item item) {
		return registerItem(name, item, null);
	}

	@Override
	public ActionResult resourceConditionCheckTag(TagKey<Item> key) {
		Identifier id = key.id();
		if (id.getNamespace().equals("c")) {
			String name = id.getPath();
			if (name.equals("ingots/enderium"))
				return ActionResult.SUCCESS;

			if (name.equals("ingots/invar"))
				return ActionResult.SUCCESS;

			if (name.equals("ingots/nickel"))
				return ActionResult.SUCCESS;

			if (name.equals("coins/silver"))
				return ActionResult.SUCCESS;

			if (name.equals("coins/gold"))
				return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}

	@Override
	public void onTagsLoad(TagHandler<Item> handler) {
		Arrays.stream(TradingEntry.CoinTypes.values())
				.forEach(coinType -> {
					handler.register(
							C.id(coinType.getName() + "_coins"),
							Registries.ITEM.get(coinType.getId())
					);
					handler.register(
							C.id("coins", coinType.getName()),
							Registries.ITEM.get(coinType.getId())
					);
				});
		Arrays.stream(ToolMaterialIndex.values())
				.forEach(materialIndex -> handler.register(
						CabfItemTags.SAWS,
						Registries.ITEM.get(Cabricality.id(materialIndex.getName() + "_saw"))
				));
		DUSTS.forEach(variant -> handler.register(
				C.id(variant, "_dusts"),
				Registries.ITEM.get(Cabricality.id(variant + "_dust"))
		));
		CRUSHED_RAW_MATERIALS.forEach(variant -> handler.register(
				AllItemTags.CRUSHED_RAW_MATERIALS.tag,
				Registries.ITEM.get(Cabricality.id("crushed_raw_" + variant))
		));

		handler.register(C.id("enderium_ingots"), ENDERIUM_INGOT);
		handler.register(C.id("invar_ingots"), INVAR_INGOT);
		handler.register(C.id("nickel_ingots"), NICKEL_INGOT);

		handler.register(C.id("ingots", "enderium"), ENDERIUM_INGOT);
		handler.register(C.id("ingots", "invar"), INVAR_INGOT);
		handler.register(C.id("ingots", "nickel"), NICKEL_INGOT);

		handler.register(C.id("nickel_nuggets"), NICKEL_NUGGET);
		handler.register(C.id("nuggets", "nickel"), NICKEL_NUGGET);
	}
}
