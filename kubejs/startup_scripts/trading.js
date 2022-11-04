let S = (x) => CABF('silver_coin', x)
let G = (x) => CABF('gold_coin', x)

onEvent("item.registry", event => {
            global.trades = []
            global.professions = []
            global.transactions = []

            let profession = (name, c1, c2, transaction) => {
                let id = name.toLowerCase().replace("'", "").split(" ").join("_")
                global.professions.push(id)
                global.transactions[id] = [transaction, asIdentifier("profession_card_" + id)]

                event.create(asIdentifier("profession_card_" + id))
                    .color(1, c1)
                    .color(2, c2)
                    .parentModel(modpackId + ":item/profession_card")
                    .texture(modpackId + ":item/trading/profession_card_0")
                    .displayName(Text.translate(`item.cabricality.profession_card`).getString() + " §f- " + Text.translate(`profession.${modpackId}.${id}`).getString())
                    .tooltip("&b" + Text.translate(`profession.${modpackId}.${id}`).getString())
                    .unstackable()
            }

            let trade = (name, c1, c2, transactions, custom) => {
                let split = name.toLowerCase().split('x')
                let cid = ""
                let amount = ""
                split.forEach((e, idx) => {
                    if (idx == 0) amount = e;
                    else {
                        if (idx > 1) cid = cid + "x";
                        cid = cid + e.replace("'", "").replace("#", "").replace(' ', '');
                    }
                })
                let id = cid.replace(':', '_');
                if (custom == "shipments") {
                    global.trades.push(id)
                    global.transactions[id] = [transactions, asIdentifier(`trade_card_${id}`)]

                    event.create(asIdentifier(`trade_card_${id}`))
                        .color(1, c1)
                        .color(2, c2)
                        .parentModel(modpackId + ":item/trade_card")
                        .texture(modpackId + ":item/trading/trade_card_0")
                        .displayName(Text.translate(`item.cabricality.trade_card`).getString() + " §f- " + Text.translate(Item.of(cid).name.toString().split('\'')[1]).getString())
                        .tooltip(`§f${Text.translate(Item.of(cid).name.toString().split('\'')[1]).getString()}`)
                        .tooltip(`§8${cid}`)
                        .unstackable()



                } else {
                    global.trades.push(id)
                    global.transactions[id] = [transactions, asIdentifier(`trade_card_${id}`)]

                    event.create(asIdentifier(`trade_card_${id}`))
                        .color(0, c2)
                        .color(1, c1)
                        .color(2, c2)
                        .parentModel(modpackId + ":item/trade_card")
                        .texture(modpackId + ":item/trading/trade_card_0")
                        .displayName(Text.translate(`item.cabricality.trade_card`).getString() + " §f- " + Text.translate(`trading.cabricality.exchange_currencies`).getString())
                        .tooltip(Text.translate(`trading.cabricality.exchange_currencies`).getString())
                        .unstackable()

                }
            }

            profession("Farming", 0xFFCC29, 0x81B214, [
                { in: FD("carrot_crate", 1), out: S(1) },
                { in: FD("beetroot_crate", 1), out: S(1) },
                { in: FD("cabbage_crate", 1), out: S(1) },
                { in: FD("tomato_crate", 1), out: S(1) },
                { in: FD("onion_crate", 1), out: S(1) },
                { in: FD("rice_bag", 1), out: S(1) },
                { in: FD("canvas", 32), out: S(1) },
                { in: MC("sweet_berries", 8), out: S(1) },
                { in: MC("cocoa_beans", 16), out: S(1) },
                { in: MC("honey_bottle", 8), out: S(1) },
                { in: MC("honeycomb", 4), out: S(1) },
                { in: MC("dandelion", 16), out: S(1) },
                { in: MC("poppy", 16), out: S(1) },
                { in: MC("oxeye_daisy", 16), out: S(1) },
                { in: MC("bread", 8), out: S(1) },
                { in: MC("brown_mushroom", 8), out: S(1) },
                { in: MC("red_mushroom", 8), out: S(1) },
                { in: MC("kelp", 64), out: S(1) },
                { in: MC("pumpkin", 9), out: S(1) },
                { in: MC("wool", 16), out: S(1) },
                { in: MC("melon", 3), out: S(1) }
            ])

            profession("Carpentry", 0xD0AF84, 0x966C3B, [
                { in: MC("oak_log", 64), out: S(1) },
                { in: MC("spruce_log", 64), out: S(1) },
                { in: MC("jungle_log", 64), out: S(1) },
                { in: MC("dark_oak_log", 64), out: S(1) },
                { in: MC("acacia_log", 64), out: S(1) },
                { in: MC("birch_log", 64), out: S(1) },
                { in: MC("crimson_stem", 64), out: S(1) },
                { in: MC("warped_stem", 64), out: S(1) },
                { in: PMD("palm_log", 64), out: S(1) },
                { in: PMD("cherry_oak_log", 64), out: S(1) },
                { in: PMD("dark_amaranth_stem", 64), out: S(1) }
            ])

            profession("Mining", 0x1C1124, 0x88FFF7, [
                { in: CR("crushed_iron_ore", 16), out: S(12) },
                { in: CR("crushed_copper_ore", 16), out: S(14) },
                { in: CR("crushed_zinc_ore", 16), out: S(14) },
                { in: CR("crushed_gold_ore", 16), out: S(16) },
                { in: CR("crushed_nickel_ore", 16), out: S(14) },
                { in: CR("crushed_lead_ore", 16), out: S(14) },
                { in: CR("crushed_tin_ore", 16), out: S(12) },
                { in: KJ("crushed_calorite_ore", 16), out: S(16) },
                { in: KJ("crushed_ostrum_ore", 16), out: S(16) },
                { in: KJ("crushed_desh_ore", 16), out: S(16) },
                { in: KJ("crushed_cobalt_ore", 16), out: S(16) },
                { in: MC("andesite", 64), out: S(1) },
                { in: MC("granite", 64), out: S(1) },
                { in: MC("diorite", 64), out: S(1) },
                { in: MC("tuff", 64), out: S(2) },
                { in: MC("sandstone", 64), out: S(1) },
                { in: CR("limestone", 64), out: S(1) },
                { in: PMD("blunite", 64), out: S(1) },
                { in: PMD("carbonite", 64), out: S(1) },
                { in: CR("scoria", 64), out: S(1) },
                { in: CR("veridium", 16), out: S(1) },
                { in: CR("ochrum", 16), out: S(1) },
                { in: CR("crimsite", 16), out: S(1) },
                { in: CR("asurine", 16), out: S(1) },
                { in: IV("sulfur_crystal", 16), out: S(6) },
                { in: MC("lapis_lazuli", 16), out: S(6) },
                { in: MC("diamond", 1), out: S(14) },
                { in: MC("coal", 16), out: S(2) },
                { in: MC("redstone", 48), out: S(2) }
            ])

            profession("Masonry", 0x5E6F64, 0xBA7967, [
                { in: AP("basalt_tiles", 64), out: S(6) },
                { in: AP("sunmetal_block", 64), out: S(8) },
                { in: AP("osseous_bricks", 64), out: S(6) },
                { in: AP("packed_ice_pillar", 64), out: S(8) },
                { in: AP("flint_tiles", 64), out: S(4) },
                { in: AP("abyssaline", 64), out: S(12) },
                { in: AP("gilded_sandstone", 64), out: S(10) },
                { in: MC("bricks", 64), out: S(6) },
                { in: AP("olivestone_bricks", 64), out: S(4) },
                { in: MC("quartz_bricks", 64), out: S(18) },
                { in: AP("algal_bricks", 64), out: S(6) },
                { in: CR("ornate_iron_window", 64), out: S(10) },
                { in: MC("mossy_cobblestone", 64), out: S(6) },
                { in: TC("seared_bricks", 64), out: S(8) },
                { in: TC("tconstruct", "scorched_bricks", 64), out: S(12) },
                { in: C("glazed_terracotta", 64), out: S(6) }
            ])

            profession("Hunting", 0x393E46, 0xCF0000, [
                { in: MC("phantom_membrane", 1), out: S(8) },
                { in: MC("rabbit_foot", 1), out: S(8) },
                { in: MC("nether_star", 1), out: S(64) },
                { in: MC("dragon_breath", 1), out: S(1) },
                { in: MC("ghast_tear", 1), out: S(10) },
                { in: MC("dragon_egg", 1), out: S(128) }
            ])

            profession("Cooking", 0xD8B384, 0xF7DAD9, [
                { in: CR("bar_of_chocolate", 16), out: S(4) },
                { in: CR("honeyed_apple", 16), out: S(4) },
                { in: CR("builders_tea", 16), out: S(4) },
                { in: FD("hot_cocoa", 16), out: S(5) },
                { in: FD("tomato_sauce", 8), out: S(3) },
                { in: FD("apple_pie_slice", 16), out: S(3) },
                { in: FD("chocolate_pie_slice", 16), out: S(4) },
                { in: FD("sweet_berry_cheesecake_slice", 17), out: S(3) },
                { in: FD("cake_slice", 14), out: S(3) },
                { in: FD("sweet_berry_cookie", 64), out: S(2) },
                { in: FD("honey_cookie", 64), out: S(2) },
                { in: MC("cookie", 64), out: S(2) },
                { in: FD("fruit_salad", 16), out: S(7) },
                { in: FD("mixed_salad", 16), out: S(9) },
                { in: FD("nether_salad", 16), out: S(5) },
                { in: FD("barbecue_stick", 16), out: S(5) },
                { in: FD("egg_sandwich", 16), out: S(5) },
                { in: FD("chicken_sandwich", 16), out: S(9) },
                { in: FD("bacon_sandwich", 16), out: S(9) },
                { in: FD("hamburger", 16), out: S(11) },
                { in: FD("mutton_wrap", 16), out: S(10) },
                { in: FD("dumplings", 16), out: S(7) },
                { in: FD("stuffed_potato", 16), out: S(6) },
                { in: FD("cabbage_rolls", 16), out: S(5) },
                { in: FD("beef_stew", 16), out: S(8) },
                { in: FD("chicken_soup", 16), out: S(9) },
                { in: MC("rabbit_stew", 16), out: S(10) },
                { in: MC("beetroot_soup", 16), out: S(7) },
                { in: MC("pumpkin_pie", 16), out: S(6) },
                { in: FD("vegetable_soup", 16), out: S(9) },
                { in: FD("fish_stew", 16), out: S(9) },
                { in: FD("fried_rice", 16), out: S(8) },
                { in: FD("pumpkin_soup", 16), out: S(12) },
                { in: FD("baked_cod_stew", 16), out: S(9) },
                { in: FD("noodle_soup", 16), out: S(9) },
                { in: FD("pasta_with_meatballs", 16), out: S(10) },
                { in: FD("pasta_with_mutton_chop", 16), out: S(10) },
                { in: FD("roasted_mutton_chops", 16), out: S(9) },
                { in: FD("vegetable_noodles", 16), out: S(10) },
                { in: FD("steak_and_potatoes", 16), out: S(9) },
                { in: FD("ratatouille", 16), out: S(9) },
                { in: FD("squid_ink_pasta", 16), out: S(11) },
                { in: FD("roast_chicken", 16), out: S(7) },
                { in: FD("stuffed_pumpkin", 16), out: S(9) },
                { in: FD("honey_glazed_ham", 16), out: S(7) },
                { in: FD("shepherds_pie", 16), out: S(7) },
                { in: CR("sweet_roll", 16), out: S(4) }
            ])

            profession("fishing", 0x9DDFD3, 0xDBF6E9, [
                { in: MC("cod", 8), out: S(1) },
                { in: MC("salmon", 8), out: S(1) },
                { in: MC("pufferfish", 8), out: S(1) },
                { in: MC("tropical_fish", 8), out: S(1) }
            ])

            profession("smithing", 0xFFC93C, 0xFF7A00, [
                { in: MC("iron_boots", 1), out: S(2) },
                { in: MC("iron_leggings", 1), out: S(4) },
                { in: MC("iron_chestplate", 1), out: S(4) },
                { in: MC("iron_helmet", 1), out: S(2) },
                { in: MC("golden_boots", 1), out: S(4) },
                { in: MC("golden_leggings", 1), out: S(7) },
                { in: MC("golden_chestplate", 1), out: S(8) },
                { in: MC("golden_helmet", 1), out: S(5) },
                { in: MC("golden_apple", 1), out: S(10) },
                { in: MC("arrow", 32), out: S(3) },
                { in: MC("iron_sword", 1), out: S(1) },
                { in: MC("golden_sword", 1), out: S(2) }
            ])

            trade("1x cabricality:exchange_currencies", 0xEEE9E9, 0xFFD700, [
                { in: G(1), out: S(64) },
                { in: S(64), out: G(1) }
            ], "exchange")

            let DATAGEN_QUESTS = false

            let data = []
            let group_max_width = []
            let current_group_max_width = 0
            let simulate = DATAGEN_QUESTS
            let entry_cost = 10

            let row = 0
            let col = 0
            let group = 0

            let next_group = () => {
                group++
                if (simulate)
                    group_max_width.push(current_group_max_width)
                current_group_max_width = 0
                col = 0
                row++
            }

            let simple = (name, item, coin, c1, c2) => {
                    let jump1 = true;
                    let jump2 = true;
                    let split = item.split('x')
                    let id = ""
                    let amount = ""
                    split.forEach((e, idx) => {
                        if (idx == 0) amount = e;
                        else {
                            if (idx > 1) id = id + "x";
                            id = id + e.replace(' ', '').replace("#", "");
                        }
                    })
                    let card_id = asIdentifier("trade_card_" + id.toLowerCase().replace("'", "").replace("#", "").replace(":", "_").replace(" ", "_"))
                    if (!simulate)
                        trade(item, c1, c2, [
                            { in: item, out: coin >= 64 ? G(Math.floor(coin / 64)) : S(coin) }
                        ], "shipments")
                    if (!DATAGEN_QUESTS)
                        jump1 = false;
                    if (jump1) {
                        current_group_max_width = Math.min(8, current_group_max_width + 1)
                        if (simulate)
                            jump2 = false;
                        if (jump2) {
                            if (col > 7) {
                                col = 0
                                row++
                            }

                            let x = col - (group_max_width[group] - 1) / 2
                            let y = row + group / 2
                            col++

                            let template = `
		{
			title: "${amount} × {${entry_cost<0?"block.":"item."}${id.replace(':','.')}}"
			icon: "${id}"
			disable_toast: true
			x: ${x}d
			y: ${y}d
			shape: "hexagon"
			subtitle: "${coin>=64? `${Math.floor(coin/64).toString()}{market.cabricality.shipments.gold}` : `${coin}{market.cabricality.shipments.silver}`}"
			tasks: [{
				type: "item"
				item: "cabricality:silver_coin"
				icon: "cabricality:silver_coin"
				count: ${Math.abs(entry_cost)}L
			}]
			rewards: [
				{
					type: "item"
					auto: "enabled"
					item: "${card_id}"
				}
				{
					type: "custom"
					title: "{market.cabricality.shipments.repeatable}"
					icon: "indrev:module_charger"
					tags: ["reset"]
					auto: "no_toast"
				}
			]
		}`
				data.push(template)
			}
		}
	}

    while (true) {
        group = 0
        row = 0

        entry_cost = -10
        simple("Dirt", MC('dirt', 16), 2, 0x513825, 0xA87954)
        simple("Sand", MC('sand', 16), 4, 0xC2B289, 0xD8D6B9)
        simple("Gravel", MC('gravel', 16), 2, 0x686160, 0xA19393)
        simple("Clay", MC('clay', 16), 6, 0x878B95, 0x8E939D)
        simple("Ice", MC('ice', 16), 16, 0x7E99CF, 0xABB8D0)
        simple("Blackstone", MC('blackstone', 16), 12, 0x140E0F, 0x2D2831)
        simple("Grout", TC('grout', 16), 12, 0x70737F, 0xAEB0B5)
        simple("Cobblestone", MC('cobblestone', 16), 1, 0x585858, 0x646363)
        simple("Deepslate", MC('deepslate', 16), 1, 0x3D3D43, 0x646464)
        simple("Granite", MC('granite', 16), 3, 0x563A2F, 0x9A6C5B)
        simple("Diorite", MC('diorite', 16), 3, 0x7F7F7F, 0xD4D4D4)
        simple("Andesite", MC('andesite', 16), 3, 0x5F5F5F, 0x8E8E8E)
        simple("Limestone", CR('limestone', 16), 3, 0xA7A89E, 0xC0C2BA)
        simple("Tuff", MC('tuff', 16), 3, 0x5E6055, 0x797E74)
        simple("Calcite", MC('calcite', 16), 3, 0xC2C2C2, 0xDADADA)
        simple("Dripstone", MC('dripstone_block', 16), 3, 0x6B5D4F, 0x7D6B5A)
        simple("Scoria", CR('scoria', 16), 3, 0x2A130C, 0x553427)
        simple("Scorchia", CR('scorchia', 16), 3, 0x0D0706, 0x23201A)
        simple("Obsidian", MC('obsidian', 1), 8, 0x05030A, 0x36234C)

        next_group()
        entry_cost = -10
        //simple("Dead Log", BOP('dead_log', 16), 2, 0x3D362D, 0x7A756D)
        simple("Twisted Log", AP('twisted_log', 16), 8, 0x675962, 0x3F3441)
        simple("Oak Log", MC('oak_log', 16), 4, 0x735932, 0xA88756)
        simple("Birch Log", MC('birch_log', 16), 4, 0xD6D6D2, 0xC4B079)
        simple("Spruce Log", MC('spruce_log', 16), 4, 0x523E21, 0x6F522F)
        simple("Jungle Log", MC('jungle_log', 16), 4, 0x5A501D, 0x9B6E4C)
        simple("Acacia Log", MC('acacia_log', 16), 4, 0x4F4B42, 0x9E552E)
        simple("Dark Oak Log", MC('dark_oak_log', 16), 4, 0x2C1B0D, 0x422B15)
        simple("Crimson Stem", MC('crimson_stem', 16), 8, 0x442332, 0x7A3852)
        simple("Warped Stem", MC('warped_stem', 16), 8, 0x3E1E2D, 0x347776)
        

        next_group()
        entry_cost = 10
        simple("Iron Ingot", MC('iron_ingot', 8), 16, 0xA6A6A6, 0xD5D5D5)
        simple("Zinc Ingot", CR('zinc_ingot', 8), 16, 0x616A60, 0xD0D2C5)
        simple("Copper Ingot", MC('copper_ingot', 8), 16, 0xDD7E5D, 0xFCEFBA)
        simple("Nickel Ingot", CABF('nickel_ingot', 8), 32, 0x977756, 0xE4D196)
        simple("Lead Ingot", IV('lead_ingot', 8), 32, 0x232456, 0x7C95A4)
        simple("Gold Ingot", MC('gold_ingot', 8), 48, 0xD99413, 0xFAF25E)

        simple("Andesite Alloy", CR('andesite_alloy', 16), 8, 0x505050, 0x878787)
        //simple("Silica Steel", "8x moreminecarts:silica_steel", 16, 0x3E4644, 0xB8DAC8)
        simple("Brass Ingot", CR('brass_ingot', 8), 48, 0x6F3C2D, 0xFCF085)
        //simple("Invar Ingot", IV('invar_ingot', 4), 64, 0x406D6C, 0xC3CAC1)
        simple("Invar Ingot", CABF('invar_ingot', 4), 64, 0x406D6C, 0xC3CAC1)

        entry_cost = 10
        simple("Coal", MC('coal', 16), 4, 0x1C1C1E, 0x383D45)
        simple("Flint", MC('flint', 16), 4, 0x3C3B3B, 0xA6A6A6)
        //simple("Cinnabar", IV('cinnabar', 4), 16, 0xFC7781, 0xFCCED0)
        simple("Redstone Dust", MC('redstone', 16), 8, 0xA80F01, 0xFC7781)
        simple("Diamond", MC('diamond', 1), 64, 0x20C3B3, 0xD2FCF3)
        simple("Lapis Lazuli", MC('lapis_lazuli', 8), 32, 0x335DC1, 0x7395E7)
        simple("Emerald", MC('emerald', 1), 64, 0x00A82B, 0xADFACB)
        simple("Sulfur", IV('sulfur_crystal', 4), 8, 0xC7A94A, 0xEEF071)
        //simple("Apatite", IV('apatite', 4), 8, 0x27A9BB, 0x2CC7C9)
        //simple("Niter", IV('niter', 4), 8, 0x735A65, 0xB8AFAF)
        simple("Nether Quartz", MC('quartz', 8), 24, 0xB19E8F, 0xE7E2DB)
        simple("Certus Quartz", AE2('certus_quartz_crystal', 8), 24, 0x91C5FC, 0xA7CBCF)
        simple("Fluix Crystal", AE2("fluix_crystal", 8), 32, 0x8F5CCB, 0xFC7ED4)
        simple("Cured Rubber", CABF('cured_rubber', 6), 16, 0x3D363C, 0x594F55)

        next_group()
        entry_cost = -10
        simple("Scaffolding", MC('scaffolding', 16), 2, 0x7F5F41, 0xDDC683)
        simple("Wool", MC('white_wool', 1), 8, 0xBEC4C5, 0xE4E4E4)
        simple("Sponge", MC('sponge', 1), 16, 0x8F8234, 0xCDCF4A)
        simple("Cobweb", MC('cobweb', 1), 16, 0xC2CCCF, 0xFCFCFC)

        row += 4

        next_group()
        entry_cost = -10
        simple("Rice", FD('rice_bag', 1), 4, 0x9F7653, 0xCEC6BC)
        entry_cost = 10
        simple("Straw", FD('straw', 32), 8, 0x623A17, 0x966537)
        //simple("Glowshroom", BOP('glowshroom', 4), 9, 0x2C65C9, 0x83A7B7)
        //simple("Bramble", BOP('bramble', 4), 9, 0x8C3122, 0xECCDBC)
        //simple("Barley", BOP('barley', 4), 4, 0xB78B44, 0xD8BC64)
        //simple("Watergrass", BOP('watergrass', 4), 4, 0x43763D, 0x538B51)
        //simple("Mangrove Root", BOP('mangrove_root', 4), 9, 0x795740, 0xB8AB88)
        //simple("Reed", BOP('reed', 4), 5, 0x7B4E35, 0xB2855C)
        //simple("Clover Petal", BOP('huge_clover_petal', 4), 10, 0x5B8A4F, 0x6FA960)
        //simple("Spanish Moss", BOP('spanish_moss', 4), 7, 0x395B2A, 0xA2C790)
        //simple("Willow Vine", BOP('willow_vine', 4), 7, 0x265F0D, 0x317B10)
        //simple("Cattail", BOP('cattail', 4), 4, 0x186B2B, 0x845738)
        entry_cost = -10
        simple("Sugar Cane", MC('sugar_cane', 4), 3, 0x688546, 0xC5FC85)
        simple("Kelp", MC('kelp', 8), 3, 0x5B8131, 0x58A92F)
        simple("Bamboo", MC('bamboo', 8), 5, 0x4F7416, 0x88AC5F)
        entry_cost = 10
        simple("Sweet Berries", MC('sweet_berries', 8), 11, 0x27613F, 0xA30700)
        simple("Glow Berries", MC('glow_berries', 8), 17, 0xF4DF6A, 0xD27119)
        entry_cost = -10
        simple("Vines", MC('vine', 4), 7, 0x183D08, 0x317B10)
        simple("Cactus", MC('cactus', 4), 5, 0xB8BD93, 0x639631)
        entry_cost = 10
        simple("Tree Fertilizer", CR('tree_fertilizer', 1), 8, 0xCF8469, 0x71933A)

        next_group()
        entry_cost = -10
        //simple("Daub", SP('daub', 16), 5, 0xBFBAAA, 0xCBC8B6)
        simple("Clear Glass", TC('clear_glass', 16), 4, 0xA9C3CF, 0xE8E8E8)
        //simple("Factory Block", "16x chisel:factory/dots", 2, 0x211C10, 0x604B43)
        //simple("Laboratory Block", "16x chisel:laboratory/wallpanel", 4, 0x71706E, 0x9D9A98)
        simple("Copper Shingles", CR('copper_shingles', 16), 3, 0xB5654B, 0xE4BB79)
        simple("Algal Bricks", AP('algal_bricks', 32), 6, 0x292926, 0x3D4D48)
        simple("Olivestone Bricks", AP('olivestone_bricks', 32), 8, 0x3A3C2E, 0x57553E)
        //simple("Architect's Limestone", AP('limestone', 32), 8, 0x756958, 0x7F7360)
        simple("Sunmetal", AP('sunmetal_block', 32), 8, 0x603E38, 0xB48764)
        simple("Plating Block", AP('plating_block', 32), 8, 0x222225, 0x39383C)
        simple("Twisted Planks", AP('twisted_planks', 32), 8, 0x5E5259, 0x72616B)
        simple("Osseous Bricks", AP('osseous_bricks', 32), 8, 0x9D976F, 0xD3D0BF)
        simple("Seared Stone", TC('seared_stone', 16), 32, 0x393734, 0x59534F)
        simple("Scorched Stone", TC('scorched_stone', 16), 32, 0x382C25, 0x4C3F37)

        simple("Lantern", MC('lantern', 1), 1, 0x484F64, 0xF6C765)
        //simple("Copper Lantern", SP('copper_lantern', 1), 1, 0xB36535, 0xF3B154)
        //simple("Brass Lantern", SP('brass_lantern', 1), 1, 0xA47C37, 0xFAEACF)
        //simple("Crimson Lantern", SP('crimson_lantern', 1), 1, 0x9C0E2C, 0xE7EB56)

        next_group()
        entry_cost = 10
        simple("Slime Ball", MC('slime_ball', 4), 24, 0x4F7E48, 0x8AD480)
        simple("String", MC('string', 4), 5, 0x2E4446, 0xD8D8D8)
        simple("Feather", MC('feather', 4), 6, 0xD0D0D0, 0xFCFCFC)
        simple("Gunpowder", MC('gunpowder', 4), 7, 0x535353, 0x717171)
        simple("Leather", MC('leather', 4), 8, 0x873A25, 0xC45B34)
        simple("Ink Sac", MC('ink_sac', 4), 8, 0x493F49, 0x786470)
        simple("Experience", MC('experience_bottle', 1), 8, 0x689AC7, 0xFFF2FF)
        simple("Shulker Shell", MC('shulker_shell', 1), 128, 0x6B476A, 0x956895)
        simple("Spider Eye", MC('spider_eye', 4), 10, 0x64062A, 0xC25E6A)
        simple("Ender Pearl", MC('ender_pearl', 1), 48, 0x0B4C41, 0x2BCBAF)
        simple("Rotten Flesh", MC('rotten_flesh', 4), 3, 0xB24320, 0x695C18)
        simple("Blaze Rod", MC('blaze_rod', 1), 20, 0xAC3B00, 0xD5AC26)
        simple("Bone", MC('bone', 4), 8, 0xC9C4A3, 0xC9C4A3)
        simple("Prismarine Shard", MC('prismarine_shard', 4), 16, 0x2F6355, 0x8FC0AA)
        simple("Prismarine Crystals", MC('prismarine_crystals', 4), 24, 0x71A296, 0xDCE6D9)

        //entry_cost = 10
        //simple("Witch Hat", RQ('witch_hat', 1), 64, 0x424242, 0x568125)
        //simple("Zombie heart", RQ('zombie_heart', 1), 32, 0x532B38, 0x8D584A)
        //simple("Squid Beak", RQ('squid_beak', 1), 32, 0x00613B, 0x5BCDA1)
        //simple("Rib Bone", RQ('rib_bone', 1), 32, 0xDFDDCE, 0xFAF9E9)
        //simple("Catalyzing Gland", RQ('catalyzing_gland', 1), 48, 0x268E23, 0x63CA52)
        //simple("Chelicerae", RQ('chelicerae', 1), 32, 0x251721, 0x4D0C3B)
        //simple("Slime Pearl", RQ('slime_pearl', 1), 48, 0x1B9D33, 0x84F58E)
        //simple("Bat Wing", RQ('bat_wing', 1), 128, 0x464646, 0x6D6D6D)
        //simple("Withered Rib", RQ('withered_rib', 1), 128, 0x2A2E2E, 0x434949)
        //simple("Molten Core", RQ('molten_core', 1), 48, 0xED7102, 0xFAFC58)
        //simple("Eye of the Storm", RQ('eye_of_the_storm', 1), 128, 0xFCD607, 0x96FC52)
        //simple("Frozen Core", RQ('frozen_core', 1), 48, 0x008DC2, 0x7571FB)
        //simple("Nebulous Heart", RQ('nebulous_heart', 1), 64, 0x6200A0, 0xE500C3)
        //simple("Guardian Spike", RQ('guardian_spike', 1), 64, 0x7F4215, 0xE29964)
        //simple("Phantom Membrane", MC('phantom_membrane', 1), 48, 0x6E506B, 0xC1B79F)
        //simple("Blaze Cake", CR('blaze_cake', 1), 16, 0x834141, 0xFCE083)
        next_group()

        if (!DATAGEN_QUESTS)
            break
        if (!simulate)
            break
        simulate = false
    }

    if (DATAGEN_QUESTS) {
        console.log("QUEST PASTER:")
        console.log(data.join(""))
        console.log(":QUEST PASTER END")
    }
})