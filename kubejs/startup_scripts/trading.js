let modpackId = "cabricality"
function tradeList() {
//	sync needed
//	profession, modId, item, =itemCount, =silverCoinCount
	let tl = [
//		Farming
		["farming", "item", "farmersdelight", "carrot_crate", 1, 1],
		["farming", "item", "farmersdelight", "beetroot_crate", 1, 1],
		["farming", "item", "farmersdelight", "cabbage_crate", 1, 1],
		["farming", "item", "farmersdelight", "tomato_crate", 1, 1],
		["farming", "item", "farmersdelight", "onion_crate", 1, 1],
		["farming", "item", "farmersdelight", "rice_bag", 1, 1],
		["farming", "item", "farmersdelight", "canvas", 32, 1],
		["farming", "item", "minecraft", "sweet_berries", 8, 1],
		["farming", "item", "minecraft", "cocoa_beans", 16, 1],
		["farming", "item", "minecraft", "honey_bottle", 8, 1],
		["farming", "item", "minecraft", "honeycomb", 4, 1],
		["farming", "item", "minecraft", "dandelion", 16, 1],
		["farming", "item", "minecraft", "poppy", 16, 1],
		["farming", "item", "minecraft", "oxeye_daisy", 16, 1],
		["farming", "item", "minecraft", "bread", 8, 1],
		["farming", "item", "minecraft", "brown_mushroom", 8, 1],
		["farming", "item", "minecraft", "red_mushroom", 8, 1],
		["farming", "item", "minecraft", "kelp", 64, 1],
		["farming", "item", "minecraft", "pumpkin", 9, 1],
		["farming", "tag", "minecraft", "wool", 16, 1],
		["farming", "item", "minecraft", "melon", 3, 1],
//		Carpentry
		["carpentry", "item", "minecraft", "oak_log", 64, 1],
		["carpentry", "item", "minecraft", "spruce_log", 64, 1],
		["carpentry", "item", "minecraft", "jungle_log", 64, 1],
		["carpentry", "item", "minecraft", "dark_oak_log", 64, 1],
		["carpentry", "item", "minecraft", "acacia_log", 64, 1],
		["carpentry", "item", "minecraft", "birch_log", 64, 1],
		["carpentry", "item", "minecraft", "crimson_stem", 64, 1],
		["carpentry", "item", "minecraft", "warped_stem", 64, 1],
		["carpentry", "item", "promenade", "palm_log", 64, 1],
		["carpentry", "item", "promenade", "cherry_oak_log", 64, 1],
		["carpentry", "item", "promenade", "dark_amaranth_stem", 64, 1],
//		Mining
		["mining", "item", "create", "crushed_iron_ore", 16, 8],
		["mining", "item", "create", "crushed_copper_ore", 16, 8],
		["mining", "item", "create", "crushed_zinc_ore", 16, 8],
		["mining", "item", "create", "crushed_gold_ore", 16, 10],
		["mining", "item", "create", "crushed_nickel_ore", 16, 12],
		["mining", "item", "create", "crushed_lead_ore", 16, 12],
		["mining", "item", "minecraft", "andesite", 64, 1],
		["mining", "item", "minecraft", "granite", 64, 1],
		["mining", "item", "minecraft", "diorite", 64, 1],
		["mining", "item", "minecraft", "tuff", 64, 2],
		["mining", "item", "minecraft", "sandstone", 64, 1],
		["mining", "item", "create", "limestone", 64, 1],
		["mining", "item", "indrev", "sulfur_crystal", 16, 6],
		["mining", "item", "minecraft", "lapis_lazuli", 16, 6],
		["mining", "item", "minecraft", "diamond", 1, 14],
		["mining", "item", "minecraft", "coal", 16, 2],
//      Masonry
		["masonry", "item", "architects_palette", "basalt_tiles", 64, 6],
		["masonry", "item", "architects_palette", "sunmetal_block", 64, 8],
		["masonry", "item", "architects_palette", "osseous_bricks", 64, 6],
		["masonry", "item", "architects_palette", "packed_ice_pillar", 64, 8],
		["masonry", "item", "architects_palette", "flint_tiles", 64, 4],
		["masonry", "item", "architects_palette", "abyssaline", 64, 12],
		["masonry", "item", "architects_palette", "gilded_sandstone", 64, 10],
		["masonry", "item", "minecraft", "bricks", 64, 6],
		["masonry", "item", "architects_palette", "olivestone_bricks", 64, 4],
		["masonry", "item", "minecraft", "quartz_bricks", 64, 18],
		["masonry", "item", "architects_palette", "algal_bricks", 64, 6],
		["masonry", "item", "create", "ornate_iron_window", 64, 10],
		["masonry", "item", "minecraft", "mossy_cobblestone", 64, 6],
		["masonry", "tag", "c", "glazed_terracotta", 64, 6],
//		Hunting
		["hunting", "item", "minecraft", "phantom_membrane", 1, 8],
		["hunting", "item", "minecraft", "rabbit_foot", 1, 8],
		["hunting", "item", "minecraft", "nether_star", 1, 64],
		["hunting", "item", "minecraft", "dragon_breath", 1, 1],
		["hunting", "item", "minecraft", "ghast_tear", 1, 10],
		["hunting", "item", "minecraft", "dragon_egg", 1, 128],
//		Cooking
		["cooking", "item", "create", "bar_of_chocolate", 16, 4],
		["cooking", "item", "create", "honeyed_apple", 16, 4],
		["cooking", "item", "create", "builders_tea", 16, 4],
		["cooking", "item", "farmersdelight", "hot_cocoa", 16, 5],
		["cooking", "item", "farmersdelight", "tomato_sauce", 8, 3],
		["cooking", "item", "farmersdelight", "apple_pie_slice", 16, 3],
		["cooking", "item", "farmersdelight", "chocolate_pie_slice", 16, 4],
		["cooking", "item", "farmersdelight", "sweet_berry_cheesecake_slice", 17, 3],
		["cooking", "item", "farmersdelight", "cake_slice", 14, 3],
		["cooking", "item", "farmersdelight", "sweet_berry_cookie", 64, 2],
		["cooking", "item", "farmersdelight", "honey_cookie", 64, 2],
		["cooking", "item", "minecraft", "cookie", 64, 2],
		["cooking", "item", "farmersdelight", "fruit_salad", 16, 7],
		["cooking", "item", "farmersdelight", "mixed_salad", 16, 9],
		["cooking", "item", "farmersdelight", "nether_salad", 16, 5],
		["cooking", "item", "farmersdelight", "barbecue_stick", 16, 5],
		["cooking", "item", "farmersdelight", "egg_sandwich", 16, 5],
		["cooking", "item", "farmersdelight", "chicken_sandwich", 16, 9],
		["cooking", "item", "farmersdelight", "bacon_sandwich", 16, 9],
		["cooking", "item", "farmersdelight", "hamburger", 16, 11],
		["cooking", "item", "farmersdelight", "mutton_wrap", 16, 10],
		["cooking", "item", "farmersdelight", "dumplings", 16, 7],
		["cooking", "item", "farmersdelight", "stuffed_potato", 16, 6],
		["cooking", "item", "farmersdelight", "cabbage_rolls", 16, 5],
		["cooking", "item", "farmersdelight", "beef_stew", 16, 8],
		["cooking", "item", "farmersdelight", "chicken_soup", 16, 9],
		["cooking", "item", "minecraft", "rabbit_stew", 16, 10],
		["cooking", "item", "minecraft", "beetroot_soup", 16, 7],
		["cooking", "item", "minecraft", "pumpkin_pie", 16, 6],
		["cooking", "item", "farmersdelight", "vegetable_soup", 16, 9],
		["cooking", "item", "farmersdelight", "fish_stew", 16, 9],
		["cooking", "item", "farmersdelight", "fried_rice", 16, 8],
		["cooking", "item", "farmersdelight", "pumpkin_soup", 16, 12],
		["cooking", "item", "farmersdelight", "baked_cod_stew", 16, 9],
		["cooking", "item", "farmersdelight", "noodle_soup", 16, 9],
		["cooking", "item", "farmersdelight", "pasta_with_meatballs", 16, 10],
		["cooking", "item", "farmersdelight", "pasta_with_mutton_chop", 16, 10],
		["cooking", "item", "farmersdelight", "roasted_mutton_chops", 16, 9],
		["cooking", "item", "farmersdelight", "vegetable_noodles", 16, 10],
		["cooking", "item", "farmersdelight", "steak_and_potatoes", 16, 9],
		["cooking", "item", "farmersdelight", "ratatouille", 16, 9],
		["cooking", "item", "farmersdelight", "squid_ink_pasta", 16, 11],
		["cooking", "item", "farmersdelight", "roast_chicken", 16, 7],
		["cooking", "item", "farmersdelight", "stuffed_pumpkin", 16, 9],
		["cooking", "item", "farmersdelight", "honey_glazed_ham", 16, 7],
		["cooking", "item", "farmersdelight", "shepherds_pie", 16, 7],
		["cooking", "item", "create", "sweet_roll", 16, 4],
//		Fishing
		["fishing", "item", "minecraft", "cod", 8, 1],
		["fishing", "item", "minecraft", "salmon", 8, 1],
		["fishing", "item", "minecraft", "pufferfish", 8, 1],
		["fishing", "item", "minecraft", "tropical_fish", 8, 1],
//		Smithing
		["smithing", "item", "minecraft", "iron_boots", 1, 2],
		["smithing", "item", "minecraft", "iron_leggings", 1, 4],
		["smithing", "item", "minecraft", "iron_chestplate", 1, 4],
		["smithing", "item", "minecraft", "iron_helmet", 1, 2],
		["smithing", "item", "minecraft", "golden_boots", 1, 4],
		["smithing", "item", "minecraft", "golden_leggings", 1, 7],
		["smithing", "item", "minecraft", "golden_chestplate", 1, 8],
		["smithing", "item", "minecraft", "golden_helmet", 1, 5],
		["smithing", "item", "minecraft", "golden_apple", 1, 10],
		["smithing", "item", "minecraft", "arrow", 32, 3],
		["smithing", "item", "minecraft", "iron_sword", 1, 1],
		["smithing", "item", "minecraft", "golden_sword", 1, 2],
	]
	return tl
}
onEvent("item.registry", event => {
	let professions = []
	tradeList().forEach(trade => {
		let isTag = ""
		if (trade[1] == "tag") isTag = "#"
		event.create("trade_card_" + trade[3])
			.texture(modpackId + ":/item/trading/trade_card")
			.displayName("§6交易铭牌")
			.tooltip(`§7${isTag}${trade[2]}:${trade[3]}`)
		if (professions.includes(trade[0]) != true) {
			professions.push(trade[0])
		}
	})
	professions.forEach(profession => {
		event.create("profession_card_" + profession)
			.texture(modpackId + ":/item/trading/profession_card")
			.displayName("§d职业铭牌")
			.tooltip(`${modpackId}:${profession}`)
	})
})