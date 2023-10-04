package dm.earth.cabricality.content.trading;

import static dm.earth.cabricality.Mod.Entry.AP;
import static dm.earth.cabricality.Mod.Entry.CABF;
import static dm.earth.cabricality.Mod.Entry.CREATE;
import static dm.earth.cabricality.Mod.Entry.FARMERS_DELIGHT;
import static dm.earth.cabricality.Mod.Entry.INDREV;
import static dm.earth.cabricality.Mod.Entry.MC;
import static dm.earth.cabricality.Mod.Entry.MLM;
import static dm.earth.cabricality.Mod.Entry.PROMENADE;
import static dm.earth.cabricality.Mod.Entry.TC;
import static dm.earth.cabricality.Mod.Entry.TERRESTRIA;
import static dm.earth.cabricality.content.trading.core.TradingEntry.of;
import static dm.earth.cabricality.content.trading.core.TradingEntry.CoinTypes.GOLD;
import static dm.earth.cabricality.content.trading.core.TradingEntry.CoinTypes.SILVER;
import dm.earth.cabricality.content.trading.core.Profession;
import dm.earth.cabricality.content.trading.core.TradingEntry;
import dm.earth.cabricality.content.trading.core.TradingEntryRegistry;

public enum Professions {
	EXCHANGE(Profession.of("exchange", of(SILVER.getId(), 16, GOLD, 1, 0x9fadb4),
			of(GOLD.getId(), 1, SILVER, 16, 0xfdf470))),

	FARMING(Profession.of("farming", 0x7baf4a,
			// Vanilla
			of(MC.id("carrot"), 9, SILVER, 1, 0xfd8e28), of(MC.id("apple"), 9, SILVER, 1, 0xfc2234),
			of(MC.id("beetroot"), 9, SILVER, 1, 0xa2292f),
			of(MC.id("potato"), 9, SILVER, 1, 0xc79643),
			of(MC.id("sweet_berries"), 12, SILVER, 1, 0xa30c0e),
			of(MC.id("cocoa_beans"), 16, SILVER, 1, 0x6f4428),
			of(MC.id("honey_bottle"), 8, SILVER, 1, 0xfd912d),
			of(MC.id("honeycomb"), 4, SILVER, 1, 0xf9be3d),
			of(MC.id("wheat"), 16, SILVER, 1, 0x8c7641), of(MC.id("kelp"), 64, SILVER, 1, 0x5ca939),
			of(MC.id("melon_slice"), 64, SILVER, 1, 0xbd322a),
			of(MC.id("white_wool"), 64, SILVER, 1, 0xf8f9f9),
			of(MC.id("cactus"), 16, SILVER, 1, 0x659739),
			// Farmer's Delight
			of(FARMERS_DELIGHT.id("rice"), 9, SILVER, 1, 0xe6dfd7), of(FARMERS_DELIGHT.id("onion"), 9, SILVER, 1, 0xab7437),
			of(FARMERS_DELIGHT.id("tomato"), 9, SILVER, 1, 0xbc3427),
			of(FARMERS_DELIGHT.id("cabbage"), 9, SILVER, 1, 0x7baf4a),
			of(FARMERS_DELIGHT.id("canvas"), 9, SILVER, 1, 0xb69775),
			of(FARMERS_DELIGHT.id("pumpkin_slice"), 32, SILVER, 1, 0xde882e),
			// Promenade
			of(PROMENADE.id("blueberries"), 12, SILVER, 1, 0x98afd8))),

	CARPENTRY(Profession.of("carpentry", 0x735a38, of(MC.id("oak_log"), 32, SILVER, 1, 0x735a38),
			of(MC.id("spruce_log"), 32, SILVER, 1, 0x3a2715),
			of(MC.id("jungle_log"), 32, SILVER, 1, 0x544c1e),
			of(MC.id("dark_oak_log"), 32, SILVER, 1, 0x332716),
			of(MC.id("acacia_log"), 32, SILVER, 1, 0x696259),
			of(MC.id("birch_log"), 32, SILVER, 1, 0xf0eeeb),
			of(MC.id("crimson_stem"), 32, SILVER, 1, 0x871115),
			of(MC.id("warped_stem"), 32, SILVER, 1, 0x1b615b),
			of(PROMENADE.id("palm_log"), 32, SILVER, 1, 0x4d433a),
			of(PROMENADE.id("cherry_oak_log"), 32, SILVER, 1, 0x401c15),
			of(PROMENADE.id("dark_amaranth_stem"), 24, SILVER, 1, 0x4e4053),
			of(MLM.id("runewood_log"), 24, SILVER, 1, 0x583434),
			of(MLM.id("exposed_runewood_log"), 16, SILVER, 1, 0x683a30),
			of(MLM.id("soulwood_log"), 16, SILVER, 1, 0x34232e),
			// terrestria
			of(TERRESTRIA.id("redwood_log"), 32, SILVER, 1, 0x482a22),
			of(TERRESTRIA.id("hemlock_log"), 32, SILVER, 1, 0x28281c),
			of(TERRESTRIA.id("rubber_log"), 24, SILVER, 1, 0x807d7d),
			of(TERRESTRIA.id("cypress_log"), 32, SILVER, 1, 0x463f3c),
			of(TERRESTRIA.id("willow_log"), 32, SILVER, 1, 0x252216),
			of(TERRESTRIA.id("japanese_maple_log"), 32, SILVER, 1, 0x99605d),
			of(TERRESTRIA.id("rainbow_eucalyptus_log"), 24, SILVER, 1, 0x2e5086),
			of(TERRESTRIA.id("sakura_log"), 24, SILVER, 1, 0x341d12),
			of(TERRESTRIA.id("yucca_palm_log"), 32, SILVER, 1, 0x615941),
			of(TERRESTRIA.id("small_oak_log"), 32, SILVER, 1, 0x4f3d25),
			of(TERRESTRIA.id("redwood_quarter_log"), 32, SILVER, 1, 0x482a22),
			of(TERRESTRIA.id("hemlock_quarter_log"), 32, SILVER, 1, 0x28281c),
			of(TERRESTRIA.id("cypress_quarter_log"), 32, SILVER, 1, 0x463f3c),
			of(TERRESTRIA.id("rainbow_eucalyptus_quarter_log"), 24, SILVER, 1, 0x2e5086))),

	MINING(Profession.of("mining", 0x826357, of(CREATE.id("crushed_raw_iron"), 3, SILVER, 1, 0xe8c8b2),
			of(CREATE.id("crushed_raw_copper"), 3, SILVER, 1, 0xe77a57),
			of(CREATE.id("crushed_raw_zinc"), 3, SILVER, 1, 0xbae8c2),
			of(CREATE.id("crushed_raw_gold"), 3, SILVER, 2, 0xf9e845),
			of(CREATE.id("crushed_raw_nickel"), 3, SILVER, 1, 0xe0dcab),
			of(CREATE.id("crushed_raw_lead"), 3, SILVER, 2, 0x535466),
			of(CREATE.id("crushed_raw_tin"), 3, SILVER, 1, 0xdcdcdc),
			of(CABF.id("crushed_raw_calorite"), 1, SILVER, 5, 0xb22c45),
			of(CABF.id("crushed_raw_ostrum"), 1, SILVER, 4, 0x644a59),
			of(CABF.id("crushed_raw_desh"), 1, SILVER, 2, 0xdfa562),
			of(CABF.id("crushed_raw_cobalt"), 2, SILVER, 3, 0x2a79da),
			of(MLM.id("crushed_soulstone"), 1, SILVER, 1, 0x734c77),
			of(MC.id("andesite"), 64, SILVER, 1, 0x868887),
			of(MC.id("granite"), 64, SILVER, 1, 0x9e6b5a),
			of(MC.id("diorite"), 64, SILVER, 1, 0xe6e2e6),
			of(MC.id("tuff"), 48, SILVER, 1, 0x85837b),
			of(MC.id("calcite"), 48, SILVER, 1, 0xedece6),
			of(MC.id("sandstone"), 64, SILVER, 1, 0xdfd3a9),
			of(CREATE.id("limestone"), 64, SILVER, 1, 0xbbb6a9),
			of(PROMENADE.id("blunite"), 64, SILVER, 1, 0x5f6874),
			of(PROMENADE.id("carbonite"), 64, SILVER, 1, 0x514e52),
			of(CREATE.id("scoria"), 16, SILVER, 1, 0x493a34),
			of(CREATE.id("veridium"), 16, SILVER, 1, 0x205f4f),
			of(CREATE.id("ochrum"), 16, SILVER, 1, 0xb29561),
			of(CREATE.id("crimsite"), 16, SILVER, 1, 0x7f2f3d),
			of(CREATE.id("asurine"), 16, SILVER, 1, 0x3f4b68),
			of(INDREV.id("sulfur_crystal"), 4, SILVER, 1, 0xbbab53),
			of(MC.id("lapis_lazuli"), 6, SILVER, 1, 0x3761c0),
			of(MC.id("diamond"), 1, GOLD, 1, 0x54ecd9), of(MC.id("coal"), 16, SILVER, 1, 0x252525),
			of(MC.id("redstone"), 24, SILVER, 1, 0xfc0d1b))),

	MASONRY(Profession.of("masonry", 0xaf6250, of(AP.id("basalt_tiles"), 12, SILVER, 1, 0x747474),
			of(AP.id("sunmetal_block"), 8, SILVER, 1, 0x613c3d),
			of(AP.id("osseous_bricks"), 12, SILVER, 1, 0xe8e5d3),
			of(AP.id("packed_ice_pillar"), 12, SILVER, 1, 0x7ea7f1),
			of(AP.id("flint_tiles"), 12, SILVER, 1, 0x302d30),
			of(AP.id("abyssaline_bricks"), 8, SILVER, 1, 0x534265),
			of(AP.id("gilded_sandstone"), 8, SILVER, 1, 0xf8bc39),
			of(AP.id("olivestone_bricks"), 12, SILVER, 1, 0x51523e),
			of(AP.id("algal_bricks"), 12, SILVER, 1, 0x38423b),
			of(AP.id("myonite_bricks"), 12, SILVER, 1, 0x867967),
			// Create
			of(CREATE.id("ornate_iron_window"), 8, SILVER, 1, 0x7f786f),
			// Tconstruct
			of(TC.id("seared_bricks"), 12, SILVER, 1, 0x3f3c39),
			of(TC.id("scorched_bricks"), 8, SILVER, 1, 0x2d231d),
			of(TC.id("mud_bricks"), 4, SILVER, 1, 0x6e4e34),
			// Vanilla
			of(MC.id("bricks"), 12, SILVER, 1, 0xaf6250),
			of(MC.id("nether_bricks"), 12, SILVER, 1, 0x37181e),
			of(MC.id("quartz_bricks"), 8, SILVER, 1, 0xeeeae6),
			of(MC.id("mossy_cobblestone"), 12, SILVER, 1, 0x738454))),

	HUNTING(Profession.of("hunting", 0x4f3c3e,
			of(MC.id("phantom_membrane"), 1, SILVER, 8, 0x8e7f7a),
			of(MC.id("rabbit_foot"), 1, SILVER, 8, 0xd7b184),
			of(MC.id("nether_star"), 1, GOLD, 6, 0xfdfeac),
			of(MC.id("dragon_breath"), 1, SILVER, 1, 0xaa307a),
			of(MC.id("ghast_tear"), 1, SILVER, 4, 0xd0f1f1),
			of(MC.id("dragon_egg"), 1, GOLD, 16, 0x2c0332))),

	FISHING(Profession.of("fishing", 0x4772e6, of(MC.id("cod"), 8, SILVER, 1, 0xc5a174),
			of(MC.id("salmon"), 8, SILVER, 1, 0xa93636),
			of(MC.id("pufferfish"), 8, SILVER, 1, 0xf9a62c),
			of(MC.id("tropical_fish"), 8, SILVER, 1, 0xf26f2f))),

	COOKING(Profession.of("cooking", 0xbd634d,
			of(CREATE.id("bar_of_chocolate"), 4, SILVER, 1, 0xb56447),
			of(CREATE.id("honeyed_apple"), 4, SILVER, 1, 0xb8410e),
			of(CREATE.id("builders_tea"), 4, SILVER, 1, 0xcd7559),
			of(CREATE.id("sweet_roll"), 4, SILVER, 1, 0xe5d7b9),
			of(MC.id("bread"), 3, SILVER, 1, 0xba8727), of(MC.id("cake"), 1, SILVER, 1, 0xfcf5df),
			of(MC.id("cookie"), 8, SILVER, 1, 0xe5964f),
			of(MC.id("pumpkin_pie"), 4, SILVER, 1, 0xd5894f),
			// Farmer's Delight
			of(FARMERS_DELIGHT.id("apple_pie"), 4, SILVER, 1, 0x8a1c30),
			of(FARMERS_DELIGHT.id("sweet_berry_cheesecake"), 4, SILVER, 1, 0xd04176),
			of(FARMERS_DELIGHT.id("chocolate_pie"), 4, SILVER, 1, 0x5f352e),
			of(FARMERS_DELIGHT.id("roast_chicken_block"), 2, SILVER, 1, 0x5f352e),
			of(FARMERS_DELIGHT.id("stuffed_pumpkin_block"), 2, SILVER, 1, 0xd4811b),
			of(FARMERS_DELIGHT.id("honey_glazed_ham_block"), 2, SILVER, 1, 0xdf6868),
			of(FARMERS_DELIGHT.id("shepherds_pie_block"), 2, SILVER, 1, 0xeacd81),
			of(FARMERS_DELIGHT.id("sweet_berry_cookie"), 6, SILVER, 1, 0x9a0700),
			of(FARMERS_DELIGHT.id("honey_cookie"), 6, SILVER, 1, 0xd9a514),
			of(FARMERS_DELIGHT.id("hot_cocoa"), 4, SILVER, 1, 0x855037),
			of(FARMERS_DELIGHT.id("apple_cider"), 4, SILVER, 1, 0xb98142),
			of(FARMERS_DELIGHT.id("melon_juice"), 4, SILVER, 1, 0xa33026),
			of(FARMERS_DELIGHT.id("fruit_salad"), 4, SILVER, 1, 0x255b3c),
			of(FARMERS_DELIGHT.id("mixed_salad"), 4, SILVER, 1, 0x38693a),
			of(FARMERS_DELIGHT.id("nether_salad"), 4, SILVER, 1, 0x0f8b77),
			of(FARMERS_DELIGHT.id("barbecue_stick"), 4, SILVER, 1, 0x3311d),
			of(FARMERS_DELIGHT.id("egg_sandwich"), 4, SILVER, 1, 0xd9a514),
			of(FARMERS_DELIGHT.id("chicken_sandwich"), 4, SILVER, 1, 0xa0572e),
			of(FARMERS_DELIGHT.id("beef_stew"), 4, SILVER, 1, 0xee8408),
			of(FARMERS_DELIGHT.id("hamburger"), 4, SILVER, 1, 0xaf8024),
			of(FARMERS_DELIGHT.id("bacon_sandwich"), 4, SILVER, 1, 0xc49f57),
			of(FARMERS_DELIGHT.id("mutton_wrap"), 4, SILVER, 1, 0xe5cc9d),
			of(FARMERS_DELIGHT.id("dumplings"), 4, SILVER, 1, 0xcfae7d),
			of(FARMERS_DELIGHT.id("stuffed_potato"), 4, SILVER, 1, 0xa37b3f),
			of(FARMERS_DELIGHT.id("cabbage_rolls"), 4, SILVER, 1, 0x9fcc6c),
			of(FARMERS_DELIGHT.id("chicken_soup"), 4, SILVER, 1, 0xc4c7ac),
			of(FARMERS_DELIGHT.id("vegetable_soup"), 4, SILVER, 1, 0x817023),
			of(FARMERS_DELIGHT.id("fish_stew"), 4, SILVER, 1, 0xa03500),
			of(FARMERS_DELIGHT.id("fried_rice"), 4, SILVER, 1, 0xe1c43e),
			of(FARMERS_DELIGHT.id("pumpkin_soup"), 4, SILVER, 1, 0xd4811b),
			of(FARMERS_DELIGHT.id("baked_cod_stew"), 4, SILVER, 1, 0xdcbf8c),
			of(FARMERS_DELIGHT.id("noodle_soup"), 4, SILVER, 1, 0xd8b75d),
			of(FARMERS_DELIGHT.id("bacon_and_eggs"), 4, SILVER, 1, 0xd17f7f),
			of(FARMERS_DELIGHT.id("pasta_with_meatballs"), 4, SILVER, 1, 0x744331),
			of(FARMERS_DELIGHT.id("pasta_with_mutton_chop"), 4, SILVER, 1, 0x7f4a3c),
			of(FARMERS_DELIGHT.id("roasted_mutton_chops"), 4, SILVER, 1, 0x7f4a3c),
			of(FARMERS_DELIGHT.id("vegetable_noodles"), 4, SILVER, 1, 0x9fcc6c),
			of(FARMERS_DELIGHT.id("steak_and_potatoes"), 4, SILVER, 1, 0x87523b),
			of(FARMERS_DELIGHT.id("ratatouille"), 4, SILVER, 1, 0xc14837),
			of(FARMERS_DELIGHT.id("squid_ink_pasta"), 4, SILVER, 1, 0x332f5a),
			of(FARMERS_DELIGHT.id("grilled_salmon"), 4, SILVER, 1, 0xae4b29),
			of(FARMERS_DELIGHT.id("roast_chicken"), 4, SILVER, 1, 0xc4845b),
			of(FARMERS_DELIGHT.id("stuffed_pumpkin"), 4, SILVER, 1, 0xe8b05d),
			of(FARMERS_DELIGHT.id("honey_glazed_ham"), 4, SILVER, 1, 0xba6522))),

	SMITHING(Profession.of("smithing", 0xb9b9b9, of(MC.id("arrow"), 12, SILVER, 1, 0xc9c9c9),
			// Iron
			of(MC.id("iron_sword"), 1, SILVER, 1, 0xeeeeee),
			of(MC.id("iron_axe"), 1, SILVER, 1, 0xeeeeee),
			of(MC.id("iron_helmet"), 1, SILVER, 2, 0xeeeeee),
			of(MC.id("iron_chestplate"), 1, SILVER, 4, 0xeeeeee),
			of(MC.id("iron_leggings"), 1, SILVER, 3, 0xeeeeee),
			of(MC.id("iron_boots"), 1, SILVER, 2, 0xeeeeee),
			// Golden
			of(MC.id("golden_sword"), 1, SILVER, 2, 0xece559),
			of(MC.id("golden_axe"), 1, SILVER, 2, 0xece559),
			of(MC.id("golden_helmet"), 1, SILVER, 4, 0xece559),
			of(MC.id("golden_chestplate"), 1, SILVER, 8, 0xece559),
			of(MC.id("golden_leggings"), 1, SILVER, 4, 0xece559),
			of(MC.id("golden_boots"), 1, SILVER, 4, 0xece559),
			// Netherite
			of(MC.id("netherite_sword"), 1, GOLD, 1, 0x45263c),
			of(MC.id("netherite_axe"), 1, GOLD, 1, 0x45263c),
			of(MC.id("netherite_helmet"), 1, GOLD, 2, 0x45263c),
			of(MC.id("netherite_chestplate"), 1, GOLD, 4, 0x45263c),
			of(MC.id("netherite_leggings"), 1, GOLD, 3, 0x45263c),
			of(MC.id("netherite_boots"), 1, GOLD, 2, 0x45263c)));

	private final Profession profession;

	Professions(Profession profession) {
		this.profession = profession;
		for (TradingEntry entry : profession.entries())
			TradingEntryRegistry.register(entry);
	}

	public Profession get() {
		return profession;
	}

	public static void load() {
		// Load the enum
	}
}
