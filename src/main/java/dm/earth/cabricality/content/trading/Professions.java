package dm.earth.cabricality.content.trading;

import dm.earth.cabricality.content.trading.core.Profession;
import dm.earth.cabricality.content.trading.core.TradingEntry;
import dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import dm.earth.cabricality.ModEntry;

public enum Professions {
	EXCHANGE(Profession.of("exchange", TradingEntry.of(TradingEntry.CoinTypes.SILVER.getId(), 16, TradingEntry.CoinTypes.GOLD, 1, 0x9fadb4),
			TradingEntry.of(TradingEntry.CoinTypes.GOLD.getId(), 1, TradingEntry.CoinTypes.SILVER, 16, 0xfdf470))),

	FARMING(Profession.of("farming", 0x7baf4a,
			// Vanilla
			TradingEntry.of(ModEntry.MC.id("carrot"), 9, TradingEntry.CoinTypes.SILVER, 1, 0xfd8e28), TradingEntry.of(ModEntry.MC.id("apple"), 9, TradingEntry.CoinTypes.SILVER, 1, 0xfc2234),
			TradingEntry.of(ModEntry.MC.id("beetroot"), 9, TradingEntry.CoinTypes.SILVER, 1, 0xa2292f),
			TradingEntry.of(ModEntry.MC.id("potato"), 9, TradingEntry.CoinTypes.SILVER, 1, 0xc79643),
			TradingEntry.of(ModEntry.MC.id("sweet_berries"), 12, TradingEntry.CoinTypes.SILVER, 1, 0xa30c0e),
			TradingEntry.of(ModEntry.MC.id("cocoa_beans"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x6f4428),
			TradingEntry.of(ModEntry.MC.id("honey_bottle"), 8, TradingEntry.CoinTypes.SILVER, 1, 0xfd912d),
			TradingEntry.of(ModEntry.MC.id("honeycomb"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xf9be3d),
			TradingEntry.of(ModEntry.MC.id("wheat"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x8c7641), TradingEntry.of(ModEntry.MC.id("kelp"), 64, TradingEntry.CoinTypes.SILVER, 1, 0x5ca939),
			TradingEntry.of(ModEntry.MC.id("melon_slice"), 64, TradingEntry.CoinTypes.SILVER, 1, 0xbd322a),
			TradingEntry.of(ModEntry.MC.id("white_wool"), 64, TradingEntry.CoinTypes.SILVER, 1, 0xf8f9f9),
			TradingEntry.of(ModEntry.MC.id("cactus"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x659739),
			// Farmer's Delight
			TradingEntry.of(ModEntry.FD.id("rice"), 9, TradingEntry.CoinTypes.SILVER, 1, 0xe6dfd7), TradingEntry.of(ModEntry.FD.id("onion"), 9, TradingEntry.CoinTypes.SILVER, 1, 0xab7437),
			TradingEntry.of(ModEntry.FD.id("tomato"), 9, TradingEntry.CoinTypes.SILVER, 1, 0xbc3427),
			TradingEntry.of(ModEntry.FD.id("cabbage"), 9, TradingEntry.CoinTypes.SILVER, 1, 0x7baf4a),
			TradingEntry.of(ModEntry.FD.id("canvas"), 9, TradingEntry.CoinTypes.SILVER, 1, 0xb69775),
			TradingEntry.of(ModEntry.FD.id("pumpkin_slice"), 32, TradingEntry.CoinTypes.SILVER, 1, 0xde882e),
			// Promenade
			TradingEntry.of(ModEntry.PM.id("blueberries"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x98afd8))),

	CARPENTRY(Profession.of("carpentry", 0x735a38, TradingEntry.of(ModEntry.MC.id("oak_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x735a38),
			TradingEntry.of(ModEntry.MC.id("spruce_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x3a2715),
			TradingEntry.of(ModEntry.MC.id("jungle_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x544c1e),
			TradingEntry.of(ModEntry.MC.id("dark_oak_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x332716),
			TradingEntry.of(ModEntry.MC.id("acacia_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x696259),
			TradingEntry.of(ModEntry.MC.id("birch_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0xf0eeeb),
			TradingEntry.of(ModEntry.MC.id("crimson_stem"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x871115),
			TradingEntry.of(ModEntry.MC.id("warped_stem"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x1b615b),
			TradingEntry.of(ModEntry.PM.id("palm_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x4d433a),
			TradingEntry.of(ModEntry.PM.id("cherry_oak_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x401c15),
			TradingEntry.of(ModEntry.PM.id("dark_amaranth_stem"), 24, TradingEntry.CoinTypes.SILVER, 1, 0x4e4053),
			TradingEntry.of(ModEntry.MLM.id("runewood_log"), 24, TradingEntry.CoinTypes.SILVER, 1, 0x583434),
			TradingEntry.of(ModEntry.MLM.id("exposed_runewood_log"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x683a30),
			TradingEntry.of(ModEntry.MLM.id("soulwood_log"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x34232e),
			// terrestria
			TradingEntry.of(ModEntry.TRE.id("redwood_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x482a22),
			TradingEntry.of(ModEntry.TRE.id("hemlock_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x28281c),
			TradingEntry.of(ModEntry.TRE.id("rubber_log"), 24, TradingEntry.CoinTypes.SILVER, 1, 0x807d7d),
			TradingEntry.of(ModEntry.TRE.id("cypress_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x463f3c),
			TradingEntry.of(ModEntry.TRE.id("willow_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x252216),
			TradingEntry.of(ModEntry.TRE.id("japanese_maple_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x99605d),
			TradingEntry.of(ModEntry.TRE.id("rainbow_eucalyptus_log"), 24, TradingEntry.CoinTypes.SILVER, 1, 0x2e5086),
			TradingEntry.of(ModEntry.TRE.id("sakura_log"), 24, TradingEntry.CoinTypes.SILVER, 1, 0x341d12),
			TradingEntry.of(ModEntry.TRE.id("yucca_palm_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x615941),
			TradingEntry.of(ModEntry.TRE.id("small_oak_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x4f3d25),
			TradingEntry.of(ModEntry.TRE.id("redwood_quarter_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x482a22),
			TradingEntry.of(ModEntry.TRE.id("hemlock_quarter_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x28281c),
			TradingEntry.of(ModEntry.TRE.id("cypress_quarter_log"), 32, TradingEntry.CoinTypes.SILVER, 1, 0x463f3c),
			TradingEntry.of(ModEntry.TRE.id("rainbow_eucalyptus_quarter_log"), 24, TradingEntry.CoinTypes.SILVER, 1, 0x2e5086))),

	MINING(Profession.of("mining", 0x826357, TradingEntry.of(ModEntry.CR.id("crushed_raw_iron"), 3, TradingEntry.CoinTypes.SILVER, 1, 0xe8c8b2),
			TradingEntry.of(ModEntry.CR.id("crushed_raw_copper"), 3, TradingEntry.CoinTypes.SILVER, 1, 0xe77a57),
			TradingEntry.of(ModEntry.CR.id("crushed_raw_zinc"), 3, TradingEntry.CoinTypes.SILVER, 1, 0xbae8c2),
			TradingEntry.of(ModEntry.CR.id("crushed_raw_gold"), 3, TradingEntry.CoinTypes.SILVER, 2, 0xf9e845),
			TradingEntry.of(ModEntry.CR.id("crushed_raw_nickel"), 3, TradingEntry.CoinTypes.SILVER, 1, 0xe0dcab),
			TradingEntry.of(ModEntry.CR.id("crushed_raw_lead"), 3, TradingEntry.CoinTypes.SILVER, 2, 0x535466),
			TradingEntry.of(ModEntry.CR.id("crushed_raw_tin"), 3, TradingEntry.CoinTypes.SILVER, 1, 0xdcdcdc),
			TradingEntry.of(ModEntry.CABF.id("crushed_raw_calorite"), 1, TradingEntry.CoinTypes.SILVER, 5, 0xb22c45),
			TradingEntry.of(ModEntry.CABF.id("crushed_raw_ostrum"), 1, TradingEntry.CoinTypes.SILVER, 4, 0x644a59),
			TradingEntry.of(ModEntry.CABF.id("crushed_raw_desh"), 1, TradingEntry.CoinTypes.SILVER, 2, 0xdfa562),
			TradingEntry.of(ModEntry.CABF.id("crushed_raw_cobalt"), 2, TradingEntry.CoinTypes.SILVER, 3, 0x2a79da),
			TradingEntry.of(ModEntry.MLM.id("crushed_soulstone"), 1, TradingEntry.CoinTypes.SILVER, 1, 0x734c77),
			TradingEntry.of(ModEntry.MC.id("andesite"), 64, TradingEntry.CoinTypes.SILVER, 1, 0x868887),
			TradingEntry.of(ModEntry.MC.id("granite"), 64, TradingEntry.CoinTypes.SILVER, 1, 0x9e6b5a),
			TradingEntry.of(ModEntry.MC.id("diorite"), 64, TradingEntry.CoinTypes.SILVER, 1, 0xe6e2e6),
			TradingEntry.of(ModEntry.MC.id("tuff"), 48, TradingEntry.CoinTypes.SILVER, 1, 0x85837b),
			TradingEntry.of(ModEntry.MC.id("calcite"), 48, TradingEntry.CoinTypes.SILVER, 1, 0xedece6),
			TradingEntry.of(ModEntry.MC.id("sandstone"), 64, TradingEntry.CoinTypes.SILVER, 1, 0xdfd3a9),
			TradingEntry.of(ModEntry.CR.id("limestone"), 64, TradingEntry.CoinTypes.SILVER, 1, 0xbbb6a9),
			TradingEntry.of(ModEntry.PM.id("blunite"), 64, TradingEntry.CoinTypes.SILVER, 1, 0x5f6874),
			TradingEntry.of(ModEntry.PM.id("carbonite"), 64, TradingEntry.CoinTypes.SILVER, 1, 0x514e52),
			TradingEntry.of(ModEntry.CR.id("scoria"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x493a34),
			TradingEntry.of(ModEntry.CR.id("veridium"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x205f4f),
			TradingEntry.of(ModEntry.CR.id("ochrum"), 16, TradingEntry.CoinTypes.SILVER, 1, 0xb29561),
			TradingEntry.of(ModEntry.CR.id("crimsite"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x7f2f3d),
			TradingEntry.of(ModEntry.CR.id("asurine"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x3f4b68),
			TradingEntry.of(ModEntry.IR.id("sulfur_crystal"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xbbab53),
			TradingEntry.of(ModEntry.MC.id("lapis_lazuli"), 6, TradingEntry.CoinTypes.SILVER, 1, 0x3761c0),
			TradingEntry.of(ModEntry.MC.id("diamond"), 1, TradingEntry.CoinTypes.GOLD, 1, 0x54ecd9), TradingEntry.of(ModEntry.MC.id("coal"), 16, TradingEntry.CoinTypes.SILVER, 1, 0x252525),
			TradingEntry.of(ModEntry.MC.id("redstone"), 24, TradingEntry.CoinTypes.SILVER, 1, 0xfc0d1b))),

	MASONRY(Profession.of("masonry", 0xaf6250, TradingEntry.of(ModEntry.AP.id("basalt_tiles"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x747474),
			TradingEntry.of(ModEntry.AP.id("sunmetal_block"), 8, TradingEntry.CoinTypes.SILVER, 1, 0x613c3d),
			TradingEntry.of(ModEntry.AP.id("osseous_bricks"), 12, TradingEntry.CoinTypes.SILVER, 1, 0xe8e5d3),
			TradingEntry.of(ModEntry.AP.id("packed_ice_pillar"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x7ea7f1),
			TradingEntry.of(ModEntry.AP.id("flint_tiles"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x302d30),
			TradingEntry.of(ModEntry.AP.id("abyssaline_bricks"), 8, TradingEntry.CoinTypes.SILVER, 1, 0x534265),
			TradingEntry.of(ModEntry.AP.id("gilded_sandstone"), 8, TradingEntry.CoinTypes.SILVER, 1, 0xf8bc39),
			TradingEntry.of(ModEntry.AP.id("olivestone_bricks"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x51523e),
			TradingEntry.of(ModEntry.AP.id("algal_bricks"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x38423b),
			TradingEntry.of(ModEntry.AP.id("myonite_bricks"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x867967),
			// Create
			TradingEntry.of(ModEntry.CR.id("ornate_iron_window"), 8, TradingEntry.CoinTypes.SILVER, 1, 0x7f786f),
			// Tconstruct
			TradingEntry.of(ModEntry.TC.id("seared_bricks"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x3f3c39),
			TradingEntry.of(ModEntry.TC.id("scorched_bricks"), 8, TradingEntry.CoinTypes.SILVER, 1, 0x2d231d),
			TradingEntry.of(ModEntry.TC.id("mud_bricks"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x6e4e34),
			// Vanilla
			TradingEntry.of(ModEntry.MC.id("bricks"), 12, TradingEntry.CoinTypes.SILVER, 1, 0xaf6250),
			TradingEntry.of(ModEntry.MC.id("nether_bricks"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x37181e),
			TradingEntry.of(ModEntry.MC.id("quartz_bricks"), 8, TradingEntry.CoinTypes.SILVER, 1, 0xeeeae6),
			TradingEntry.of(ModEntry.MC.id("mossy_cobblestone"), 12, TradingEntry.CoinTypes.SILVER, 1, 0x738454))),

	HUNTING(Profession.of("hunting", 0x4f3c3e,
			TradingEntry.of(ModEntry.MC.id("phantom_membrane"), 1, TradingEntry.CoinTypes.SILVER, 8, 0x8e7f7a),
			TradingEntry.of(ModEntry.MC.id("rabbit_foot"), 1, TradingEntry.CoinTypes.SILVER, 8, 0xd7b184),
			TradingEntry.of(ModEntry.MC.id("nether_star"), 1, TradingEntry.CoinTypes.GOLD, 6, 0xfdfeac),
			TradingEntry.of(ModEntry.MC.id("dragon_breath"), 1, TradingEntry.CoinTypes.SILVER, 1, 0xaa307a),
			TradingEntry.of(ModEntry.MC.id("ghast_tear"), 1, TradingEntry.CoinTypes.SILVER, 4, 0xd0f1f1),
			TradingEntry.of(ModEntry.MC.id("dragon_egg"), 1, TradingEntry.CoinTypes.GOLD, 16, 0x2c0332))),

	FISHING(Profession.of("fishing", 0x4772e6, TradingEntry.of(ModEntry.MC.id("cod"), 8, TradingEntry.CoinTypes.SILVER, 1, 0xc5a174),
			TradingEntry.of(ModEntry.MC.id("salmon"), 8, TradingEntry.CoinTypes.SILVER, 1, 0xa93636),
			TradingEntry.of(ModEntry.MC.id("pufferfish"), 8, TradingEntry.CoinTypes.SILVER, 1, 0xf9a62c),
			TradingEntry.of(ModEntry.MC.id("tropical_fish"), 8, TradingEntry.CoinTypes.SILVER, 1, 0xf26f2f))),

	COOKING(Profession.of("cooking", 0xbd634d,
			TradingEntry.of(ModEntry.CR.id("bar_of_chocolate"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xb56447),
			TradingEntry.of(ModEntry.CR.id("honeyed_apple"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xb8410e),
			TradingEntry.of(ModEntry.CR.id("builders_tea"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xcd7559),
			TradingEntry.of(ModEntry.CR.id("sweet_roll"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xe5d7b9),
			TradingEntry.of(ModEntry.MC.id("bread"), 3, TradingEntry.CoinTypes.SILVER, 1, 0xba8727), TradingEntry.of(ModEntry.MC.id("cake"), 1, TradingEntry.CoinTypes.SILVER, 1, 0xfcf5df),
			TradingEntry.of(ModEntry.MC.id("cookie"), 8, TradingEntry.CoinTypes.SILVER, 1, 0xe5964f),
			TradingEntry.of(ModEntry.MC.id("pumpkin_pie"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xd5894f),
			// Farmer's Delight
			TradingEntry.of(ModEntry.FD.id("apple_pie"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x8a1c30),
			TradingEntry.of(ModEntry.FD.id("sweet_berry_cheesecake"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xd04176),
			TradingEntry.of(ModEntry.FD.id("chocolate_pie"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x5f352e),
			TradingEntry.of(ModEntry.FD.id("roast_chicken_block"), 2, TradingEntry.CoinTypes.SILVER, 1, 0x5f352e),
			TradingEntry.of(ModEntry.FD.id("stuffed_pumpkin_block"), 2, TradingEntry.CoinTypes.SILVER, 1, 0xd4811b),
			TradingEntry.of(ModEntry.FD.id("honey_glazed_ham_block"), 2, TradingEntry.CoinTypes.SILVER, 1, 0xdf6868),
			TradingEntry.of(ModEntry.FD.id("shepherds_pie_block"), 2, TradingEntry.CoinTypes.SILVER, 1, 0xeacd81),
			TradingEntry.of(ModEntry.FD.id("sweet_berry_cookie"), 6, TradingEntry.CoinTypes.SILVER, 1, 0x9a0700),
			TradingEntry.of(ModEntry.FD.id("honey_cookie"), 6, TradingEntry.CoinTypes.SILVER, 1, 0xd9a514),
			TradingEntry.of(ModEntry.FD.id("hot_cocoa"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x855037),
			TradingEntry.of(ModEntry.FD.id("apple_cider"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xb98142),
			TradingEntry.of(ModEntry.FD.id("melon_juice"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xa33026),
			TradingEntry.of(ModEntry.FD.id("fruit_salad"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x255b3c),
			TradingEntry.of(ModEntry.FD.id("mixed_salad"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x38693a),
			TradingEntry.of(ModEntry.FD.id("nether_salad"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x0f8b77),
			TradingEntry.of(ModEntry.FD.id("barbecue_stick"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x3311d),
			TradingEntry.of(ModEntry.FD.id("egg_sandwich"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xd9a514),
			TradingEntry.of(ModEntry.FD.id("chicken_sandwich"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xa0572e),
			TradingEntry.of(ModEntry.FD.id("beef_stew"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xee8408),
			TradingEntry.of(ModEntry.FD.id("hamburger"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xaf8024),
			TradingEntry.of(ModEntry.FD.id("bacon_sandwich"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xc49f57),
			TradingEntry.of(ModEntry.FD.id("mutton_wrap"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xe5cc9d),
			TradingEntry.of(ModEntry.FD.id("dumplings"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xcfae7d),
			TradingEntry.of(ModEntry.FD.id("stuffed_potato"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xa37b3f),
			TradingEntry.of(ModEntry.FD.id("cabbage_rolls"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x9fcc6c),
			TradingEntry.of(ModEntry.FD.id("chicken_soup"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xc4c7ac),
			TradingEntry.of(ModEntry.FD.id("vegetable_soup"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x817023),
			TradingEntry.of(ModEntry.FD.id("fish_stew"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xa03500),
			TradingEntry.of(ModEntry.FD.id("fried_rice"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xe1c43e),
			TradingEntry.of(ModEntry.FD.id("pumpkin_soup"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xd4811b),
			TradingEntry.of(ModEntry.FD.id("baked_cod_stew"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xdcbf8c),
			TradingEntry.of(ModEntry.FD.id("noodle_soup"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xd8b75d),
			TradingEntry.of(ModEntry.FD.id("bacon_and_eggs"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xd17f7f),
			TradingEntry.of(ModEntry.FD.id("pasta_with_meatballs"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x744331),
			TradingEntry.of(ModEntry.FD.id("pasta_with_mutton_chop"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x7f4a3c),
			TradingEntry.of(ModEntry.FD.id("roasted_mutton_chops"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x7f4a3c),
			TradingEntry.of(ModEntry.FD.id("vegetable_noodles"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x9fcc6c),
			TradingEntry.of(ModEntry.FD.id("steak_and_potatoes"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x87523b),
			TradingEntry.of(ModEntry.FD.id("ratatouille"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xc14837),
			TradingEntry.of(ModEntry.FD.id("squid_ink_pasta"), 4, TradingEntry.CoinTypes.SILVER, 1, 0x332f5a),
			TradingEntry.of(ModEntry.FD.id("grilled_salmon"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xae4b29),
			TradingEntry.of(ModEntry.FD.id("roast_chicken"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xc4845b),
			TradingEntry.of(ModEntry.FD.id("stuffed_pumpkin"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xe8b05d),
			TradingEntry.of(ModEntry.FD.id("honey_glazed_ham"), 4, TradingEntry.CoinTypes.SILVER, 1, 0xba6522))),

	SMITHING(Profession.of("smithing", 0xb9b9b9, TradingEntry.of(ModEntry.MC.id("arrow"), 12, TradingEntry.CoinTypes.SILVER, 1, 0xc9c9c9),
			// Iron
			TradingEntry.of(ModEntry.MC.id("iron_sword"), 1, TradingEntry.CoinTypes.SILVER, 1, 0xeeeeee),
			TradingEntry.of(ModEntry.MC.id("iron_axe"), 1, TradingEntry.CoinTypes.SILVER, 1, 0xeeeeee),
			TradingEntry.of(ModEntry.MC.id("iron_helmet"), 1, TradingEntry.CoinTypes.SILVER, 2, 0xeeeeee),
			TradingEntry.of(ModEntry.MC.id("iron_chestplate"), 1, TradingEntry.CoinTypes.SILVER, 4, 0xeeeeee),
			TradingEntry.of(ModEntry.MC.id("iron_leggings"), 1, TradingEntry.CoinTypes.SILVER, 3, 0xeeeeee),
			TradingEntry.of(ModEntry.MC.id("iron_boots"), 1, TradingEntry.CoinTypes.SILVER, 2, 0xeeeeee),
			// Golden
			TradingEntry.of(ModEntry.MC.id("golden_sword"), 1, TradingEntry.CoinTypes.SILVER, 2, 0xece559),
			TradingEntry.of(ModEntry.MC.id("golden_axe"), 1, TradingEntry.CoinTypes.SILVER, 2, 0xece559),
			TradingEntry.of(ModEntry.MC.id("golden_helmet"), 1, TradingEntry.CoinTypes.SILVER, 4, 0xece559),
			TradingEntry.of(ModEntry.MC.id("golden_chestplate"), 1, TradingEntry.CoinTypes.SILVER, 8, 0xece559),
			TradingEntry.of(ModEntry.MC.id("golden_leggings"), 1, TradingEntry.CoinTypes.SILVER, 4, 0xece559),
			TradingEntry.of(ModEntry.MC.id("golden_boots"), 1, TradingEntry.CoinTypes.SILVER, 4, 0xece559),
			// Netherite
			TradingEntry.of(ModEntry.MC.id("netherite_sword"), 1, TradingEntry.CoinTypes.GOLD, 1, 0x45263c),
			TradingEntry.of(ModEntry.MC.id("netherite_axe"), 1, TradingEntry.CoinTypes.GOLD, 1, 0x45263c),
			TradingEntry.of(ModEntry.MC.id("netherite_helmet"), 1, TradingEntry.CoinTypes.GOLD, 2, 0x45263c),
			TradingEntry.of(ModEntry.MC.id("netherite_chestplate"), 1, TradingEntry.CoinTypes.GOLD, 4, 0x45263c),
			TradingEntry.of(ModEntry.MC.id("netherite_leggings"), 1, TradingEntry.CoinTypes.GOLD, 3, 0x45263c),
			TradingEntry.of(ModEntry.MC.id("netherite_boots"), 1, TradingEntry.CoinTypes.GOLD, 2, 0x45263c)));

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
