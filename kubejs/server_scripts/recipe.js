//	0.4 or 0.5, choose your way! (don't type 0.4.1 or it will bugged)
let createVersion = 0.5

let MOD = (domain, id, x) => (x ? `${x}x ` : "") + (id.startsWith("#") ? "#" : "") + domain + ":" + id.replace("#", "")
let CR = (id, x) => MOD("create", id, x)
let MC = (id, x) => MOD("minecraft", id, x)
let KJ = (id, x) => MOD("kubejs", id, x)
let C = (id, x) => MOD("c", id, x)
let F = (id, x) => MOD("fabric", id, x)
let IV = (id, x) => MOD("indrev", id, x)
let AE2 = (id, x) => MOD("ae2", id, x)
let KB = (id, x) => MOD("kibe", id, x)
let CX = (id, x) => MOD("coxinhautilities", id, x)
let PMD = (id, x) => MOD("promenade", id, x)
let AR = (id, x) => MOD("agape_space", id, x)
let AP = (id, x) => MOD("architects_palette", id, x)
let FD = (id, x) => MOD("farmersdelight", id, x)
let ED = (id, x) => MOD("extended_drawers", id, x)
let BC = (id, x) => MOD("bitsandchisels", id, x)
let TC = (id, x) => MOD("tconstruct", id, x)
let CC = (id, x) => MOD("computercraft", id, x)

let wood_types = [
	MC("oak"), MC("spruce"), MC("birch"), MC("jungle"), MC("acacia"), MC("dark_oak"), MC("crimson"), MC("warped"),
	PMD("dark_amaranth"), PMD("palm"), PMD("cherry_oak"),
	AP("twisted"),
	TC("bloodshroom"), TC("skyroot"), TC("greenheart")
]

let donutCraft = (event, output, center, ring) => {
	event.shaped(output, [
		'SSS',
		'SCS',
		'SSS'
	], {
		C: center,
		S: ring
	})
}

function ifiniDeploying(output, input, tool) {
	return {
		"type": "create:deploying",
		"ingredients": [
			Ingredient.of(input).toJson(),
			Ingredient.of(tool).toJson()
		],
		"results": [
			Item.of(output).toResultJson()
		],
		"keepHeldItem": true
	}
}

onEvent("item.tags", event => {
	//		Get the #forge:cobblestone tag collection and add Diamond Ore to it
	//		event.get("forge:cobblestone").add("minecraft:diamond_ore")

	//		Get the #forge:cobblestone tag collection and remove Mossy Cobblestone from it
	//		event.get("forge:cobblestone").remove("minecraft:mossy_cobblestone")
})
//	recipes
function tweaks(event) {
	event.remove({ mod: "agape_space" })
	event.remove({ type: IV("sawmill") })
	event.remove({ type: IV("compress") })
	event.remove({ type: AE2("inscriber") })
	event.shaped(KJ("circuit_scrap", 2), [" A ", "ABA", " A "], { A: KJ("invar_ingot"), B: C("#circuit_press") })
	event.stonecutting(AE2("silicon_press"), KJ("circuit_scrap"))
	event.stonecutting(AE2("engineering_processor_press"), KJ("circuit_scrap"))
	event.stonecutting(AE2("calculation_processor_press"), KJ("circuit_scrap"))
	event.stonecutting(AE2("logic_processor_press"), KJ("circuit_scrap"))

	event.remove({ id: FD("flint_knife") })
	event.remove({ id: FD("iron_knife") })
	event.remove({ id: FD("golden_knife") })
	event.remove({ id: FD("diamond_knife") })
	event.shaped(FD("flint_knife"), ["S ", " M"], { M: MC("flint"), S: C("#rods/wooden") })
	event.shaped(FD("iron_knife"), ["S ", " M"], { M: MC("iron_ingot"), S: C("#rods/wooden") })
	event.shaped(FD("golden_knife"), ["S ", " M"], { M: MC("gold_ingot"), S: C("#rods/wooden") })
	event.shaped(FD("diamond_knife"), ["S ", " M"], { M: MC("diamond"), S: C("#rods/wooden") })

	event.remove({ output: IV("fluid_pipe_mk1") })
	event.remove({ output: IV("fluid_pipe_mk2") })
	event.remove({ output: IV("fluid_pipe_mk3") })
	event.remove({ output: IV("fluid_pipe_mk4") })
	event.remove({ output: IV("item_pipe_mk1") })
	event.remove({ output: IV("cable_mk1") })
	event.remove({ output: KB("placer") })
	event.remove({ output: KB("breaker") })
	event.remove({ output: ED("shadow_drawer") })
	event.remove({ output: IV("wrench") })
	event.remove({ output: KB("regular_conveyor_belt") })
	event.remove({ output: KB("fast_conveyor_belt") })
	event.remove({ output: KB("express_conveyor_belt") })

	event.remove({ output: IV("hammer") })
	event.remove({ input: IV("hammer") })

	event.remove({ id: TC("smeltery/casting/metal/gold/coin_sand_cast") })
	event.remove({ id: TC("smeltery/casting/metal/gold/coin_gold_cast") })
	event.remove({ id: TC("smeltery/casting/metal/silver/coin_sand_cast") })
	event.remove({ id: TC("smeltery/casting/metal/silver/coin_gold_cast") })

	event.replaceInput("#c:gold_plates", "create:golden_sheet")
	event.replaceInput("#c:iron_plates", "create:iron_sheet")
	event.replaceInput("#c:copper_plates", "create:copper_sheet")
	event.replaceInput("#c:plates/gold", "create:golden_sheet")
	event.replaceInput("#c:plates/iron", "create:iron_sheet")
	event.replaceInput("#c:plates/copper", "create:copper_sheet")
	event.replaceInput("indrev:nikolite_dust", "minecraft:redstone")

	event.blasting(IV("steel_ingot"), MC("iron_ingot")).cookingTime(400)

	event.remove({ id: CR("splashing/gravel") })
	event.recipes.createSplashing([
		Item.of(MC("iron_nugget", 3)).withChance(0.25),
		Item.of(MC("flint")).withChance(0.25)
	], "minecraft:gravel")

	event.remove({ id: CR("splashing/red_sand") })
	event.recipes.createSplashing([
		Item.of(MC("gold_nugget", 2)).withChance(0.125),
		Item.of(MC("dead_bush")).withChance(0.05)
	], "minecraft:red_sand")

	event.shaped("8x indrev:cable_mk1", [
		"PMP"
	], {
		P: KJ("invar_ingot"),
		M: MC("redstone")
	})
	event.shaped(IV("item_pipe_mk1", 8), [
		"PMP"
	], {
		P: CR("brass_sheet"),
		M: CR("brass_ingot")
	})
	let upgrade = (input, output, outputTier) => {
		event.remove({ output: output })
		if (outputTier) {
			event.smithing(Item.of(output, 1), input, "indrev:tier_upgrade_mk" + outputTier)
			event.recipes.createMechanicalCrafting(Item.of(output, 1), "AB", { A: input, B: "indrev:tier_upgrade_mk" + outputTier })
		}
	}
	upgrade(IV("solar_generator_mk1"), IV("solar_generator_mk3"), 2)
	//TL as number
	let upgradeAll = (input, min, max, actualDelete) => {
		for (i = min; i <= actualDelete; i++) {
			let inputThing = input.replace("TL", i.toString())
			if (i < max) {
				let outputThing = input.replace("TL", (i + 1).toString())
				upgrade(inputThing, outputThing, i + 1)
			} else {
				if (i > max) upgrade(inputThing, inputThing)
			}
		}
	}
	upgradeAll(KB("basalt_generator_mkTL"), 1, 4, 5)
	upgradeAll(KB("cobblestone_generator_mkTL"), 1, 4, 5)
	upgradeAll(IV("cable_mkTL"), 1, 4, 4)
	upgradeAll(IV("item_pipe_mkTL"), 1, 4, 4)
	upgradeAll(IV("solid_infuser_mkTL"), 1, 4, 4)
	upgradeAll(IV("fluid_infuser_mkTL"), 1, 4, 4)
	upgradeAll(IV("fisher_mkTL"), 2, 4, 4)
	upgradeAll(IV("rancher_mkTL"), 1, 0, 4)
	upgradeAll(IV("slaughter_mkTL"), 1, 0, 4)
	upgradeAll(IV("farmer_mkTL"), 1, 0, 4)
	upgradeAll(IV("chopper_mkTL"), 1, 0, 4)
	upgradeAll(IV("compressor_mkTL"), 1, 0, 4)
	upgradeAll(IV("sawmill_mkTL"), 1, 4, 4)
	upgradeAll(IV("pulverizer_mkTL"), 1, 4, 4)
	upgradeAll(IV("electric_furnace_mkTL"), 1, 4, 4)
	upgradeAll(IV("lazuli_flux_container_mkTL"), 1, 4, 4)

	event.remove({ output: MC("hopper") })
	event.shaped(MC("hopper"), [
		"T T",
		"S S",
		" S "
	], {
		T: IV("tin_ingot"),
		S: IV("steel_plate")
	})
	event.remove({ output: KB("fluid_hopper") })
	event.shaped(KB("fluid_hopper"), [
		"T T",
		"C C",
		" C "
	], {
		T: IV("lead_ingot"),
		C: CR("copper_sheet")
	})
	event.remove({ output: IV("planks") })
	event.recipes.createPressing(IV("planks"), MC("#wooden_slabs"))
	event.remove({ output: ED("single_drawer") })
	event.shaped(ED("single_drawer"), [
		"P",
		"B",
		"P"
	], {
		B: MC("barrel"),
		P: IV("planks")
	})
	event.remove({ output: ED("connector") })
	event.shaped(ED("connector"), [
		"P",
		"W",
		"P"
	], {
		W: MC("#planks"),
		P: IV("planks")
	})
	event.remove({ output: ED("double_drawer") })
	event.shaped(ED("double_drawer", 2), [
		"PP"
	], {
		P: ED("single_drawer")
	})
	event.remove({ output: ED("quad_drawer") })
	event.shaped(ED("quad_drawer", 4), [
		"PP",
		"PP"
	], {
		P: ED("single_drawer")
	})
	event.remove({ output: ED("quad_drawer") })
	event.shaped(ED("quad_drawer", 2), [
		"P",
		"P"
	], {
		P: ED("double_drawer")
	})
	event.remove({ output: ED("t4_upgrade") })
	event.remove({ output: ED("upgrade_frame") })
	event.remove({ output: CR("propeller") })
	event.stonecutting(ED("upgrade_frame"), IV("planks"))
	upgrade(ED("upgrade_frame"), ED("t1_upgrade"), 2)
	upgrade(ED("t1_upgrade"), ED("t2_upgrade"), 3)
	upgrade(ED("t2_upgrade"), ED("t3_upgrade"), 4)
	event.remove({ output: ED("lock") })
	event.shaped(ED("lock", 1), [
		"B",
		"G"
	], {
		B: CR("brass_nugget"),
		G: CR("golden_sheet")
	})
	event.replaceInput({ output: AP("nether_brass_blend") }, MC("copper_ingot"), IV("copper_dust"))

	event.remove({ output: BC("smart_chisel") })
	event.shaped(BC("smart_chisel", 1), [
		"BS"
	], {
		S: MC("stick"),
		B: CR("polished_rose_quartz")
	})

	event.remove({ output: IV("solid_infuser_factory_mk4") })
	event.remove({ output: IV("compressor_factory_mk4") })
	event.remove({ output: IV("pulverizer_factory_mk4") })
	event.remove({ output: IV("electric_furnace_factory_mk4") })

	event.remove({ output: CR("item_vault") })
	event.shaped(CR("item_vault"), [
		"L",
		"B",
		"L"
	], {
		L: IV("lead_plate"),
		B: MC("barrel")
	})
	event.remove({ output: MC("shulker_box") })
	event.shaped(MC("shulker_box"), [
		"L",
		"B",
		"L"
	], {
		L: MC("shulker_shell"),
		B: MC("barrel")
	})
	event.remove({ output: IV("cabinet") })
	event.shaped(IV("cabinet"), [
		"L",
		"B",
		"L"
	], {
		L: CR("iron_sheet"),
		B: MC("barrel")
	})
	event.remove({ output: IV("fan") })
	event.shaped(IV("fan"), [
		" T ",
		"TIT",
		" T "
	], {
		T: IV("tin_plate"),
		I: MC("iron_ingot")
	})
	event.remove({ output: AP("plating_block") })
	event.stonecutting(AP("plating_block", 2), IV("steel_plate"))

	event.remove({ output: TC('obsidian_pane') })
	event.shaped(TC('obsidian_pane', 8), [
		'SSS',
		'SSS'
	], {
		S: MC('obsidian')
	})

	event.replaceInput(AP('withered_bone'), TC('necrotic_bone'))


	event.remove({ id: TC('compat/create/andesite_alloy_zinc') })
	event.remove({ id: TC('compat/create/andesite_alloy_iron') })

	event.replaceInput({ output: TC("seared_duct") }, TC("cobalt_ingot"), CR("brass_ingot"))
	event.replaceInput({ output: TC("scorched_duct") }, TC("cobalt_ingot"), CR("brass_ingot"))

	event.replaceInput({ output: TC("scorched_drain") }, TC("obsidian_pane"), CR("sturdy_sheet"))
	event.replaceInput({ output: TC("scorched_chute") }, TC("obsidian_pane"), CR("sturdy_sheet"))

	event.remove({ output: "naturescompass:naturescompass" })
	event.remove({ output: "explorerscompass:explorerscompass" })
	donutCraft(event, "naturescompass:naturescompass", MC("compass"), MC("#leaves"))

	event.remove({ output: CX("portable_tank_mk1") })
	event.shaped(CX("portable_tank_mk1"), [
		"T",
		"P",
		"T"
	], {
		T: IV("tin_plate"),
		P: KB("tank")
	})
	event.remove({ output: CX("cardboard_box") })
	event.shaped(CX("cardboard_box"), [
		"T",
		"P",
		"T"
	], {
		T: IV("planks"),
		P: MC("paper")
	})
	event.remove({ output: CX("wooden_hopper") })
	event.shaped(CX("wooden_hopper"), [
		"W W",
		"P P",
		" P "
	], {
		W: MC("#planks"),
		P: IV("planks")
	})
	upgradeAll(CX("portable_tank_mkTL"), 1, 4, 5)
	event.remove({ output: CX("grannys_sink") })
	event.recipes.createMechanicalCrafting(CX("grannys_sink"), [
		"SSSSS",
		"SSSSS",
		"SSWSS",
		"SSSSS",
		"SSSSS"
	], {
		W: MC("water_bucket"),
		S: KJ("computation_matrix")
	})
	event.remove({ output: CX("copper_ladder") })
	event.replaceOutput({}, CR("copper_ladder"), CX("copper_ladder"))
}

function alloying(event) {
	event.remove({ id: TC('smeltery/alloys/molten_bronze') })
	event.remove({ id: TC('smeltery/alloys/molten_brass') })
	event.remove({ id: TC('smeltery/alloys/molten_invar') })
	event.remove({ id: TC('smeltery/alloys/molten_electrum') })
	event.remove({ id: TC('smeltery/alloys/molten_rose_gold') })
	event.remove({ id: TC('smeltery/alloys/molten_enderium') })

	event.remove({ type: MC("crafting_shapeless"), output: "createplus:brass_dust" })

	event.remove({ id: TC("smeltery/casting/seared/smeltery_controller") })
	event.remove({ id: TC("smeltery/melting/copper/smeltery_controller") })
	donutCraft(event, TC('smeltery_controller'), TC('seared_bricks'), KJ('sealed_mechanism'))

	event.remove({ id: TC('smeltery/casting/scorched/foundry_controller') })
	donutCraft(event, TC('foundry_controller'), TC('scorched_bricks'), KJ('infernal_mechanism'))
	event.remove({ id: TC('smeltery/melting/soul/sand') })

	event.custom({
		"type": "tconstruct:melting",
		"ingredient": {
			"item": "minecraft:redstone"
		},
		"result": {
			"fluid": "kubejs:redstone",
			"amount": 9000
		},
		"temperature": 250,
		"time": 15
	})
	event.custom({
		"type": "tconstruct:melting",
		"ingredient": {
			"item": "minecraft:redstone_block"
		},
		"result": {
			"fluid": "kubejs:redstone",
			"amount": 81000
		},
		"temperature": 250,
		"time": 135
	})
}

function trickierWindmills(event) {
	event.remove({ output: "create:sail_frame" })
	event.remove({ output: "create:white_sail" })
	event.shapeless("create:sail_frame", ["create:white_sail"])
	event.shaped("2x create:white_sail", [
		"SSS",
		"NAN",
		"SSS"
	], {
		A: "#minecraft:wool",
		N: "minecraft:iron_nugget",
		S: "minecraft:stick"
	})
}

function initAndesiteMachine(event) {
	event.replaceInput({ id: CR("crafting/kinetics/brass_hand") }, "#c:plates/brass", CR("golden_sheet"))
	wood_types.forEach(wood => {
		if (wood.startsWith("tconstruct")) {
			event.recipes.createCutting("2x " + wood + "_planks_slab", wood + "_planks").processingTime(150)
		} else {
			event.recipes.createCutting("2x " + wood + "_slab", wood + "_planks").processingTime(150)
		}
	})
	let transitional = "kubejs:incomplete_kinetic_mechanism"
	event.recipes.createSequencedAssembly([
		"kubejs:kinetic_mechanism",
	], "#minecraft:wooden_slabs", [
		event.recipes.createDeploying(transitional, [transitional, CR("andesite_alloy")]),
		event.recipes.createDeploying(transitional, [transitional, CR("andesite_alloy")]),
		event.recipes.createDeploying(transitional, [transitional, "#fabric:saws"])
	]).transitionalItem(transitional)
		.loops(1)
		.id("kubejs:kinetic_mechanism")
	event.shapeless(KJ("kinetic_mechanism"), [CR("cogwheel"), CR("andesite_alloy"), "#minecraft:logs"]).id("kubejs:kinetic_mechanism_manual_only")//.damageIngredient(1)
	event.shaped(KJ("andesite_machine"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: CR("andesite_casing"),
		S: KJ("kinetic_mechanism")
	})

	let andesite_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), "kubejs:andesite_machine", other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: "kubejs:andesite_machine", B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), "kubejs:andesite_machine")
	}

	event.remove({ output: IV("iron_drill_head") })
	event.shaped(IV("iron_drill_head"), [
		"NN ",
		"NLP",
		" PL"
	], {
		N: MC("iron_nugget"),
		P: CR("iron_sheet"),
		L: IV("lead_ingot")
	})
	event.shaped(KJ("saw_blade"), [
		"NPN",
		"PLP",
		"NPN"
	], {
		N: MC("iron_nugget"),
		P: CR("iron_sheet"),
		L: IV("lead_ingot")
	})

	andesite_machine("create:portable_storage_interface", 2)
	andesite_machine("create:encased_fan", 1, IV("fan"))
	andesite_machine("create:mechanical_press", 1, MC("iron_block"))
	andesite_machine("create:mechanical_mixer", 1, CR("whisk"))
	andesite_machine("create:mechanical_drill", 1, IV("iron_drill_head"))
	andesite_machine("create:mechanical_saw", 1, KJ("saw_blade"))
	andesite_machine("create:deployer", 1, CR("brass_hand"))
	andesite_machine("indrev:coal_generator_mk1", 1, IV("heat_coil"))
	andesite_machine("kubejs:extractor_machine", 1, MC("bucket"))
	andesite_machine("create:mechanical_harvester", 2)
	andesite_machine("create:mechanical_plough", 2)
	andesite_machine("create:andesite_funnel", 4)
	andesite_machine("create:andesite_tunnel", 4)
	andesite_machine(AE2("charger"), 1, AE2("fluix_crystal"))
}

function algalAndesite(event) {
	event.remove({ id: CR("crafting/materials/andesite_alloy") })
	event.remove({ id: CR("crafting/materials/andesite_alloy_from_zinc") })
	event.remove({ id: CR("mixing/andesite_alloy") })
	event.remove({ id: CR("mixing/andesite_alloy_from_zinc") })

	event.remove({ output: AP("algal_brick") })
	event.smelting(AP("algal_brick"), AP("algal_blend")).xp(0).cookingTime(120)
	event.remove({ id: AP("algal_blend_shapeless") })

	event.shaped(Item.of(AP("algal_blend"), 4), [
		"SS",
		"AA"
	], {
		A: "minecraft:clay_ball",
		S: ["minecraft:kelp", "minecraft:seagrass"]
	})
	event.shaped(Item.of(AP("algal_blend"), 4), [
		"AA",
		"SS"
	], {
		A: "minecraft:clay_ball",
		S: ["minecraft:kelp", "minecraft:seagrass"]
	})
	event.shaped(Item.of(CR("andesite_alloy"), 2), [
		"SS",
		"AA"
	], {
		A: ["minecraft:andesite", CR("andesite_cobblestone")],
		S: AP("algal_brick")
	})
	event.shaped(Item.of(CR("andesite_alloy"), 2), [
		"AA",
		"SS"
	], {
		A: ["minecraft:andesite", CR("andesite_cobblestone")],
		S: AP("algal_brick")
	})

	event.recipes.createMixing(Item.of(AP("algal_blend"), 2), ["minecraft:clay_ball", ["minecraft:kelp", "minecraft:seagrass"]])
	event.recipes.createMixing(Item.of(CR("andesite_alloy"), 2), [AP("algal_brick"), ["minecraft:andesite", CR("andesite_cobblestone")]])
}

function oreProcessing(event) {
	let iron = { name: "iron" }
	let gold = { name: "gold" }
	let nickel = { name: "nickel" }

	let materialBP = new Map([
		[iron, "nickel"],
		[gold, "zinc"],
		[nickel, "copper"]
	])

	let process = (ingot, nugget, raw, crushed, dust, ores, id) => {
		let fluid = "tconstruct:molten_" + id

		event.remove({ output: ingot, input: ores })
		event.remove({ output: ingot, input: raw })
		event.remove({ output: ingot, input: crushed })

		event.remove({ output: ingot, input: dust })
		event.remove({ output: nugget, input: crushed, type: "create:splashing" })

		event.smelting(nugget, dust).cookingTime(40)
		event.blasting(nugget, dust).cookingTime(20)
		event.smelting(nugget, crushed)
		event.blasting(nugget, crushed)

		event.recipes.createMilling([Item.of(dust, 3)], crushed)
		event.recipes.createCrushing([Item.of(dust, 3), Item.of(dust, 3).withChance(0.5)], crushed)
		event.recipes.createSplashing([Item.of(nugget, 2)], dust)

		event.custom({
			"type": "alloy_forgery:forging",
			"inputs": [
				{ "item": dust },
				{ "item": dust },
				{ "item": dust }
			],
			"output": {
				"id": ingot,
				"count": 1
			},
			"min_forge_tier": 1,
			"fuel_per_tick": 3
		})
		event.remove({ id: TC("smeltery/melting/metal/" + id + "/dust") })

		if (materialBP.get(id) != null) {
			let byproduct = "tconstruct:molten_" + materialBP.get(id)
			event.custom({
				"type": "tconstruct:melting",
				"ingredient": {
					"item": dust
				},
				"result": {
					"fluid": fluid,
					"amount": 3000
				},
				"temperature": 500,
				"time": 30,
				"byproducts": [
					{
						"fluid": byproduct,
						"amount": 810
					}
				]
			})
		} else {
			event.custom({
				"type": "tconstruct:melting",
				"ingredient": {
					"item": dust
				},
				"result": {
					"fluid": fluid,
					"amount": 3000
				},
				"temperature": 500,
				"time": 30
			})
		}
	}
	let metalsVanilla = ["iron", "gold"]
	metalsVanilla.forEach(e => {
		let ingot = "minecraft:" + e + "_ingot"
		let nugget = "minecraft:" + e + "_nugget"
		let raw = "minecraft:raw_" + e
		let crushed = "create:crushed_" + e + "_ore"
		let dust = "indrev:" + e + "_dust"
		let ores = "#minecraft:" + e + "_ores"
		process(ingot, nugget, raw, crushed, dust, ores, e)

		event.remove({ id: "alloy_forgery:" + e + "_from_ores" })
		event.remove({ id: "alloy_forgery:" + e + "_from_raw_ores" })

	})
	let metalsVanillaCreate = ["copper"]
	metalsVanillaCreate.forEach(e => {
		let ingot = "minecraft:" + e + "_ingot"
		let nugget = "create:" + e + "_nugget"
		let raw = "minecraft:raw_" + e
		let crushed = "create:crushed_" + e + "_ore"
		let dust = "indrev:" + e + "_dust"
		let ores = "#minecraft:" + e + "_ores"
		process(ingot, nugget, raw, crushed, dust, ores, e,)
		event.remove({ id: "alloy_forgery:" + e + "_from_ores" })
		event.remove({ id: "alloy_forgery:" + e + "_from_raw_ores" })
	})
	let metalsCreate = ["zinc"]
	metalsCreate.forEach(e => {
		let ingot = "create:" + e + "_ingot"
		let nugget = "create:" + e + "_nugget"
		let raw = "create:raw_" + e
		let crushed = "create:crushed_" + e + "_ore"
		let dust = "createplus:" + e + "_dust"
		let ores = "#c:ores/" + e
		process(ingot, nugget, raw, crushed, dust, ores, e)
	})
	let metalsIndrev = ["tin", "lead"]
	metalsIndrev.forEach(e => {
		let ingot = "indrev:" + e + "_ingot"
		let nugget = "indrev:" + e + "_nugget"
		let raw = "indrev:raw_" + e
		let crushed = "create:crushed_" + e + "_ore"
		let dust = "indrev:" + e + "_dust"
		let ores = "#c:" + e + "_ores"
		process(ingot, nugget, raw, crushed, dust, ores, e)
	})
	let metalsTic = ["cobalt"]
	metalsTic.forEach(e => {
		let ingot = "tconstruct:" + e + "_ingot"
		let nugget = "tconstruct:" + e + "_nugget"
		let raw = "tconstruct:raw_" + e
		let crushed = "kubejs:crushed_" + e + "_ore"
		let dust = "kubejs:" + e + "_dust"
		let ores = "#c:ores/" + e
		process(ingot, nugget, raw, crushed, dust, ores, e)
	})
}

function initCopperMachine(event) {
	event.remove({ id: CR("crafting/kinetics/belt_connector") })
	event.shaped(CR("belt_connector", 3), [
		"SSS",
		"SSS"
	], {
		S: KJ("cured_rubber")
	})
	event.smelting("kubejs:cured_rubber", "kubejs:rubber")
	event.shaped(KJ("sealed_mechanism"), [
		"SCS"
	], {
		C: KJ("kinetic_mechanism"),
		S: KJ("cured_rubber")
	})
	event.shaped(KJ("copper_machine"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: CR("copper_casing"),
		S: KJ("sealed_mechanism")
	})

	let copper_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), "kubejs:copper_machine", other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: "kubejs:copper_machine", B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), "kubejs:copper_machine")
	}
	copper_machine("create:copper_backtank", 1, MC("copper_block"))
	copper_machine("create:portable_fluid_interface", 2)
	copper_machine("create:spout", 1, KB("fluid_hopper"))
	copper_machine("indrev:tier_upgrade_mk2", 1, MC("redstone"))
	copper_machine("create:hose_pulley", 1)
	copper_machine("create:item_drain", 1, MC("iron_bars"))
	copper_machine("indrev:heat_generator_mk4", 1, IV("heat_coil"))
	copper_machine("indrev:fisher_mk2", 1, MC("bucket"))
	if (createVersion >= 0.5) copper_machine("create:steam_engine", 2, MC("piston"))
	copper_machine("create:smart_fluid_pipe", 2)
	copper_machine(CX("fluid_trash_can"), 1, KB("trash_can"))
}

function initBrassMachine(event) {
	event.remove({ id: CR("crafting/materials/electron_tube") })
	event.remove({ id: CR("crafting/materials/rose_quartz") })
	let redstone = MC("redstone")
	event.shapeless("create:rose_quartz", [[MC("quartz"), AE2("certus_quartz_crystal"), AE2("charged_certus_quartz_crystal")], redstone, redstone, redstone, redstone])
	event.recipes.createMilling([AE2("certus_quartz_dust")], "#ae2:all_certus_quartz").processingTime(200)
	event.recipes.createMilling([AE2("fluix_dust")], "#ae2:all_fluix").processingTime(200)
	event.recipes.createMechanicalCrafting(Item.of(AE2("certus_crystal_seed"), 2), ["A"], { A: AE2("#all_certus_quartz") })
	event.recipes.createMechanicalCrafting(Item.of(AE2("fluix_crystal_seed"), 2), ["A"], { A: AE2("#all_fluix") })

	event.remove({ id: CR("sequenced_assembly/precision_mechanism") })
	event.shapeless(KJ("screwdriver"), [IV("screwdriver"), MC("iron_ingot"), MC("blue_dye")])

	/*	let transition = CR("incomplete_precision_mechanism")
		event.recipes.createSequencedAssembly([
			CR("precision_mechanism"),
		], KJ("kinetic_mechanism"), [
			event.recipes.createDeploying(transition, [transition, CR("electron_tube")]),
			event.recipes.createDeploying(transition, [transition, CR("electron_tube")]),
			event.recipes.createDeploying(transition, [transition, "kubejs:screwdriver"])
		]).transitionalItem(transition)
			.loops(1)
			.id("kubejs:precision_mechanism")	*/

	event.shaped(KJ("brass_machine"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: CR("brass_casing"),
		S: CR("precision_mechanism")
	})
	let brass_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), "kubejs:brass_machine", other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: "kubejs:brass_machine", B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), "kubejs:brass_machine")
	}
	brass_machine("create:mechanical_crafter", 3, MC("crafting_table"))
	brass_machine("create:sequenced_gearshift", 2)
	brass_machine("create:furnace_engine", 1)
	brass_machine("create:rotation_speed_controller", 1)
	brass_machine("create:mechanical_arm", 1)
	brass_machine("create:stockpile_switch", 2)
	brass_machine("create:content_observer", 2)
	brass_machine("indrev:solid_infuser_mk1", 1, MC("dropper"))
	brass_machine("indrev:biomass_generator_mk3", 1, IV("heat_coil"))
	brass_machine("create:brass_funnel", 4)
	brass_machine("create:brass_tunnel", 4)

	event.recipes.createMilling([AE2("sky_dust"), AE2("sky_stone_block")], AE2("sky_stone_block")).processingTime(1000)
}

function initZincMachine(event) {
	event.shaped(KJ("zinc_machine"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: KJ("zinc_casing"),
		S: KJ("infernal_mechanism")
	})
	let zinc_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), "kubejs:zinc_machine", other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: "kubejs:zinc_machine", B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), "kubejs:zinc_machine")
	}
	zinc_machine(KB("cobblestone_generator_mk1"), 1, MC("piston"))
	zinc_machine(KB("basalt_generator_mk1"), 1, MC("blue_ice"))
	zinc_machine(KB("trash_can"), 1, MC("lava_bucket"))
	zinc_machine(KB("vacuum_hopper"), 1, CR("nozzle"))
	zinc_machine(KB("big_torch"), 1, MC("torch"))
	zinc_machine(IV("solar_generator_mk1"), 1, MC("glass"))
	zinc_machine("indrev:tier_upgrade_mk3", 1, MC("redstone"))
	zinc_machine(ED("controller"), 1, ED("connector"))
}

function initObsidianMachine(event) {
	event.shaped(KJ("obsidian_machine"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: CR("railway_casing"),
		S: KJ("sturdy_mechanism")
	})

	let transitional = "kubejs:incomplete_sturdy_mechanism"
	event.recipes.createSequencedAssembly([
		"kubejs:sturdy_mechanism",
	], "create:precision_mechanism", [
		event.recipes.createDeploying(transitional, [transitional, CR("sturdy_sheet")]),
		event.recipes.createDeploying(transitional, [transitional, CR("sturdy_sheet")]),
		event.recipes.createPressing(transitional, transitional)
	]).transitionalItem(transitional)
		.loops(1)
		.id("kubejs:sturdy_mechanism")

	let obsidian_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), "kubejs:obsidian_machine", other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: "kubejs:obsidian_machine", B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), "kubejs:obsidian_machine")
	}
	obsidian_machine(CR("track_station"), 1, MC("compass"))
	obsidian_machine(CR("track_signal"), 1, CR("electron_tube"))
	obsidian_machine(CR("track_observer"), 1, MC("observer"))
	obsidian_machine(CR("controls"), 1, MC("lever"))
}

function initInvarMachine(event) {
	let chop = (type, output) => {
		event.custom({
			"type": "farmersdelight:cutting",
			"ingredients": [{ "item": "tconstruct:" + type + "_slime_fern" }],
			"tool": { "tag": "fabric:tools/knives" },
			"result": [Item.of(KJ(type + "_slimy_fern_leaf"), 2).toResultJson()]
		})
		event.custom({
			"type": "create:haunting",
			"ingredients": [{ "item": KJ(type + "_slimy_fern_leaf") }],
			"results": [{ "item": TC(type + "_slime_fern") }]
		})
		event.custom(ifiniDeploying(KJ(type + "_slimy_fern_leaf", 2), TC(type + "_slime_fern"), "#fabric:tools/knives"))
		event.recipes.createMilling([KJ(type + "_slimy_fern_paste")], KJ(type + "_slimy_fern_leaf"))
		event.campfireCooking(output, KJ(type + "_slimy_fern_paste")).cookingTime(300)
	}
	chop("earth", MC("gunpowder"))
	chop("sky", MC("bone_meal"))
	chop("ender", AE2("ender_dust"))

	event.campfireCooking(MC("torch"), MC("stick")).cookingTime(20)

	//	event.shapeless(KJ("nickel_compound"), [KJ("nickel_ingot"), IV("iron_dust"), IV("iron_dust"), IV("iron_dust"), IV("iron_dust")])

	event.blasting(KJ("invar_compound"), KJ("nickel_compound"))
	let s = KJ("invar_compound")
	event.recipes.createSequencedAssembly([
		KJ("invar_ingot"),
	], KJ("invar_compound"), [
		event.recipes.createPressing(s, s)
	]).transitionalItem(s)
		.loops(16)
		.id("kubejs:invar_ingot")

	event.remove({ id: CR("mechanical_crafting/crushing_wheel") })
	event.recipes.createMechanicalCrafting(Item.of(CR("crushing_wheel"), 2), [
		" AAA ",
		"AABAA",
		"ABBBA",
		"AABAA",
		" AAA "
	], {
		A: C("#cobblestone"),
		B: MC("stick")
	})

	event.recipes.createCrushing([Item.of(AE2("singularity")).withChance(1)], CR("crushing_wheel")).processingTime(250)

	let dyes = [MC("orange_dye"), MC("magenta_dye"), MC("light_blue_dye"), MC("yellow_dye"), MC("lime_dye"), MC("pink_dye"), MC("cyan_dye"), MC("purple_dye"), MC("blue_dye"), MC("brown_dye"), MC("green_dye"), MC("red_dye")]
	event.recipes.createCompacting("1x " + KJ("dye_entangled_singularity"), [dyes, Item.of(AE2("quantum_entangled_singularity"), 1)])
	event.recipes.createConversion([AE2("quantum_entangled_singularity")], AE2("singularity"))
	event.recipes.createCrushing([
		Item.of(AE2("red_paint_ball"), 1).withChance(.90),
		Item.of(AE2("yellow_paint_ball"), 1).withChance(.80),
		Item.of(AE2("green_paint_ball"), 1).withChance(.70),
		Item.of(AE2("blue_paint_ball"), 1).withChance(.60),
		Item.of(AE2("magenta_paint_ball"), 1).withChance(.50)],
		KJ("dye_entangled_singularity")).processingTime(50)

	event.recipes.createMechanicalCrafting(CR("chromatic_compound"), [
		"AA",
		"AA"
	], {
		A: AE2("magenta_paint_ball")
	})

	event.recipes.createPressing(KJ("radiant_sheet"), CR("refined_radiance"))
	event.recipes.createMechanicalCrafting(KJ("radiant_coil"), ["A"], { A: KJ("radiant_sheet") })

	event.shaped(KJ("chromatic_resonator"), [
		" R ",
		"R S",
		"LS "
	], {
		R: KJ("ruby"),
		L: IV("lead_ingot"),
		S: KJ("sapphire")
	})

	/*	let t = KJ("incomplete_inductive_mechanism")
		event.recipes.createSequencedAssembly([
			KJ("inductive_mechanism"),
		], CR("precision_mechanism"), [
			event.recipes.createDeploying(t, [t, KJ("radiant_coil")]),
			event.recipes.createDeploying(t, [t, KJ("radiant_coil")]),
			event.recipes.createDeploying(t, [t, KJ("chromatic_resonator")])
		]).transitionalItem(t)
			.loops(1)
			.id("kubejs:inductive_mechanism")	*/

	event.remove({ output: IV("machine_block") })
	event.shaped(IV("machine_block"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: KJ("invar_casing"),
		S: KJ("inductive_mechanism")
	})
	let invar_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), IV("machine_block"), other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: IV("machine_block"), B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), IV("machine_block"))
	}

	invar_machine(IV("electric_furnace_mk1"), 1, MC("furnace"))
	invar_machine(IV("smelter_mk4"), 1, MC("blast_furnace"))
	invar_machine(IV("pulverizer_mk1"), 1, MC("flint"))
	invar_machine(IV("sawmill_mk1"), 1, KJ("saw_blade"))
	invar_machine(IV("recycler_mk2"), 1, MC("composter"))
	invar_machine(IV("condenser_mk4"), 1, MC("packed_ice"))
	invar_machine(IV("fluid_infuser_mk1"), 1, CR("whisk"))
	invar_machine(IV("modular_workbench_mk4"), 1, MC("crafting_table"))
	invar_machine(IV("lazuli_flux_container_mk1"), 1, MC("redstone_block"))
	invar_machine(IV("laser_emitter_mk4"), 1, MC("lightning_rod"))
	event.remove({ output: IV("compressor_mk1") })
	event.remove({ output: IV("chopper_mk1") })
	event.remove({ output: IV("farmer_mk1") })
	event.remove({ output: IV("slaughter_mk1") })
	event.remove({ output: IV("rancher_mk1") })
	event.remove({ output: IV("pump_mk1") })
	event.remove({ output: IV("mining_rig_mk4") })
	event.remove({ output: IV("data_card_writer_mk4") })
	event.remove({ output: IV("drain_mk1") })
	invar_machine(CX("energy_trash_can"), 1, KB("trash_can"))
}

function initEnderMachine(event) {
	event.shaped(KJ("enderium_machine"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: KJ("enderium_casing"),
		S: KJ("abstruse_mechanism")
	})

	event.custom({
		"type": "tconstruct:alloy",
		"inputs": [
			{ "name": "tconstruct:molten_silver", "amount": 9000 },
			{ "name": "tconstruct:ender_slime", "amount": 20250 },
			{ "name": "tconstruct:molten_ender", "amount": 20250 }
		],
		"result": {
			"fluid": "tconstruct:molten_enderium",
			"amount": 9000
		},
		"temperature": 850
	})

	event.custom({
		"type": "tconstruct:melting",
		"ingredient": {
			"item": "promenade:dark_amaranth_fungus"
		},
		"result": {
			"fluid": "tconstruct:ender_slime",
			"amount": 27000
		},
		"temperature": 100,
		"time": 10
	})

	let ender_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), "kubejs:enderium_machine", other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: "kubejs:enderium_machine", B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), "kubejs:enderium_machine")
	}

	ender_machine("kibe:entangled_chest", 1, MC("chest"))
	ender_machine("kibe:entangled_tank", 1, CR("fluid_tank"))
	ender_machine(IV("tier_upgrade_mk4"), 1, MC("redstone"))
	ender_machine(AE2("quantum_ring"), 1, AE2("energy_cell"))
	ender_machine(AE2("quantum_link"), 1, AE2("fluix_pearl"))
}

function alchemy(event) {
	let blizz = KJ("blizz_powder")
	let basalz = KJ("basalz_powder")
	event.recipes.createSplashing([Item.of(KJ("sand_ball")).withChance(0.125)], "minecraft:sandstone")
	event.recipes.createCrushing([Item.of(blizz, 1), Item.of(blizz, 1).withChance(.5)], KJ("blizz_cube"))
	event.recipes.createCrushing([Item.of(basalz, 1), Item.of(basalz, 1).withChance(.5)], KJ("basalz_shard"))
	event.recipes.createCompacting(KJ("ice_charge"), [blizz, blizz, blizz, blizz, blizz, blizz, blizz, blizz])
	event.recipes.createCompacting(KJ("earth_charge"), [basalz, basalz, basalz, basalz, basalz, basalz, basalz, basalz])
	//	event.recipes.createCompacting(KJ("lightning_charge"), [basalz, basalz, basalz, basalz, basalz, basalz, basalz, basalz])

	event.remove({ id: CR("crushing/obsidian") })
	//	event.remove({ output: "ae2:silicon", type: "minecraft:smelting" })
	//	event.remove({ output: "ae2:silicon", type: "minecraft:blasting" })
	event.remove({ id: AE2("blasting/silicon_from_certus_quartz_dust") })
	event.remove({ id: AE2("smelting/silicon_from_certus_quartz_dust") })

	let alchemy_mix = (output, catalyst, r1, r2, amount) => {
		event.recipes.createMixing([Item.of(KJ("substrate_" + output, amount ? amount : 1)), KJ("substrate_" + catalyst)], [KJ("substrate_" + catalyst), KJ("substrate_" + r1, 2), KJ("substrate_" + r2)]).heated()
	}
	let alchemy_smelt = (output, catalyst, r1, r2, amount) => {
		let arrayIn = []
		let c = 1
		if (amount) { c = amount }
		arrayIn.push(KJ("substrate_" + output, c))
		arrayIn.push(KJ("substrate_" + catalyst))
		event.recipes.createCompacting(arrayIn, [KJ("substrate_" + r1, 2), KJ("substrate_" + catalyst), KJ("substrate_" + r2)]).heated()
	}

	alchemy_mix("red", "herbal", "diorite", "andesite")
	alchemy_mix("orange", "herbal", "granite", "diorite")
	alchemy_mix("yellow", "herbal", "cobblestone", "granite")
	alchemy_mix("green", "herbal", "basalt", "cobblestone")
	alchemy_mix("blue", "herbal", "limestone", "basalt")
	alchemy_mix("magenta", "herbal", "andesite", "limestone")

	alchemy_smelt("blaze", "volatile", "orange", "andesite")
	alchemy_smelt("gunpowder", "volatile", "yellow", "diorite")
	alchemy_smelt("slime", "volatile", "green", "granite")
	alchemy_smelt("prismarine", "volatile", "blue", "cobblestone")
	alchemy_smelt("obsidian", "volatile", "magenta", "basalt")

	alchemy_mix("gunpowder", "crystal", "gunpowder", "orange")
	alchemy_mix("sulfur", "crystal", "slime", "yellow")
	alchemy_mix("certus", "crystal", "obsidian", "blue")

	alchemy_smelt("lead", "metal", "blaze", "obsidian")
	alchemy_smelt("copper", "metal", "nether", "certus")
	alchemy_smelt("gold", "metal", "lead", "blaze")
	alchemy_smelt("zinc", "metal", "emerald", "slime")
	alchemy_smelt("iron", "metal", "nether", "prismarine")

	alchemy_mix("emerald", "gem", "lead", "certus")
	alchemy_mix("diamond", "gem", "gold", "sulfur")
	alchemy_mix("lapis", "gem", "copper", "nether")
	alchemy_mix("sapphire", "gem", "zinc", "gunpowder")
	alchemy_mix("ruby", "gem", "copper", "sulfur")

	alchemy_smelt("andesite", "igneous", "sapphire", "iron", 20)
	alchemy_smelt("diorite", "igneous", "lapis", "lead", 20)
	alchemy_smelt("granite", "igneous", "diamond", "copper", 20)
	alchemy_smelt("cobblestone", "igneous", "ruby", "gold", 20)
	alchemy_smelt("basalt", "igneous", "emerald", "gold", 20)
	alchemy_smelt("limestone", "igneous", "lapis", "zinc", 20)

	let mundane = (id, outputs) => {
		let jsonOut = []
		if (outputs[0] > 0)
			jsonOut.push({
				"item": "minecraft:bone_meal",
				"count": outputs[0],
				"chance": 0.65
			})
		if (outputs[1] > 0)
			jsonOut.push({
				"item": MC("redstone"),
				"count": outputs[1]
			})
		if (outputs[2] > 0)
			jsonOut.push({
				"item": MC("glowstone_dust"),
				"count": outputs[2]
			})
		event.custom({
			"type": "indrev:sawmill",
			"ingredients": {
				"item": KJ(`failed_alchemy_${id}`)
			},
			"output": jsonOut,
			"processTime": 64
		})
	}

	let i = 0;

	mundane(i++, [4, 0, 0])
	mundane(i++, [3, 1, 0])
	mundane(i++, [3, 0, 1])
	mundane(i++, [2, 2, 0])
	mundane(i++, [2, 0, 2])

	mundane(i++, [2, 1, 1])
	mundane(i++, [1, 3, 0])
	mundane(i++, [1, 0, 3])
	mundane(i++, [1, 2, 1])
	mundane(i++, [1, 1, 2])

	mundane(i++, [0, 4, 0])
	mundane(i++, [0, 0, 4])
	mundane(i++, [0, 3, 1])
	mundane(i++, [0, 1, 3])
	mundane(i++, [0, 2, 2])

	let recompact = (id, id2) => {
		event.recipes.createCompacting(id2, [id])
	}

	event.recipes.createCrushing(CR("powdered_obsidian"), MC("obsidian"))

	recompact(CR("powdered_obsidian"), MC("obsidian"))
	recompact(KJ("diamond_dust"), MC("diamond"))
	recompact(KJ("emerald_dust"), MC("emerald"))

	global.substrates.forEach(a => {
		a.forEach(e => {
			if (!e.ingredient)
				return
			event.custom({
				"type": "indrev:fluid_infuse",
				"ingredients": Ingredient.of(e.ingredient).toJson(),
				"fluidInput": {
					"fluid": "tconstruct:molten_glass",
					"amount": 8100
				},
				"output": { "item": e.id },
				"processTime": 375
			})
			event.custom({
				"type": "tconstruct:casting_basin",
				"cast": Ingredient.of(e.ingredient).toJson(),
				"cast_consumed": true,
				"fluid": {
					"name": "tconstruct:molten_glass",
					"amount": 8100
				},
				"result": e.id,
				"cooling_time": 150
			})
			event.custom({
				"type": "indrev:sawmill",
				"ingredients": { "item": e.id },
				"output": [{ "item": e.outputItem ? e.outputItem : typeof e.ingredient == "string" ? e.ingredient : e.ingredient[0], "chance": 0.75 }],
				"processTime": 64
			})
		})
	})

	event.custom({
		"type": "indrev:sawmill",
		"ingredients": { "item": "kubejs:substrate_silicon" },
		"output": [{ "item": "ae2:silicon", "count": 1 }],
		"processTime": 64
	})

	event.custom({
		"type": "indrev:sawmill",
		"ingredients": { "item": "kubejs:substrate_silver" },
		"output": [{ "item": "indrev:silver_dust", "count": 1 }],
		"processTime": 64
	})

	event.custom({
		"type": "indrev:fluid_infuse",
		"ingredients": [
			{ "item": "minecraft:redstone" },
		],
		"fluidInput": { "fluid": "tconstruct:molten_glass", "amount": 8100 },
		"output": { "item": "kubejs:accellerator_redstone", "count": 1 },
		"processTime": 400
	})

	event.custom({
		"type": "indrev:fluid_infuse",
		"ingredients": [
			{ "item": "indrev:silver_dust" }
		],
		"fluidInput": { "fluid": "tconstruct:molten_glass", "amount": 8100 },
		"output": { "item": "kubejs:substrate_silver", "count": 1 },
		"processTime": 400
	})

	event.custom({
		"type": "indrev:fluid_infuse",
		"ingredients": [
			{ "item": "minecraft:glowstone_dust" }
		],
		"fluidInput": { "fluid": "tconstruct:molten_glass", "amount": 8100 },
		"output": { "item": "kubejs:accellerator_glowstone", "count": 1 },
		"processTime": 400
	})
}

function initFluixMachine(event) {
	event.custom(ifiniDeploying(AE2("printed_silicon"), AE2("silicon"), AE2("silicon_press")))

	event.shaped(KJ("flash_drive"), [
		"SCA"
	], {
		A: TC("cobalt_ingot"),
		C: AE2("logic_processor"),
		S: MC("iron_ingot")
	})

	/*
	let t = KJ("incomplete_calculation_mechanism")
		event.recipes.createSequencedAssembly([
			KJ("calculation_mechanism"),
		], KJ("inductive_mechanism"), [
			event.recipes.createDeploying(t, [t, AE2("printed_silicon")]),
			event.recipes.createDeploying(t, [t, AE2("printed_silicon")]),
			event.recipes.createDeploying(t, [t, KJ("flash_drive")])
		]).transitionalItem(t)
			.loops(1)
			.id("kubejs:calculation_mechanism")
	*/

	event.remove({ output: AE2("controller") })
	event.shaped(AE2("controller"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: KJ("fluix_casing"),
		S: KJ("calculation_mechanism")
	})

	let fluix_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), AE2("controller"), other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: AE2("controller"), B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), AE2("controller"))
	}
	fluix_machine(AE2("condenser"), 1, AE2("fluix_pearl"))
	fluix_machine(AE2("drive"), 1, AE2("engineering_processor"))
	fluix_machine(AE2("formation_core"), 4, AE2("logic_processor"))
	fluix_machine(AE2("annihilation_core"), 4, AE2("calculation_processor"))
	fluix_machine(AE2("chest"), 1, MC("chest"))

	event.replaceInput({ id: AE2("network/cells/item_storage_components_cell_1k_part") }, C("#redstone_dusts"), KJ("calculation_mechanism"))
	event.replaceInput({ id: AE2("network/cells/item_storage_components_cell_1k_part") }, AE2("logic_processor"), MC("redstone"))
	event.replaceInput({ id: AE2("network/cells/spatial_components") }, C("#glowstone_dusts"), KJ("calculation_mechanism"))
	event.replaceInput({ id: AE2("network/cells/spatial_components") }, AE2("engineering_processor"), MC("glowstone_dust"))
	event.replaceInput({ id: AE2("network/crafting/patterns_blank") }, C("#glowstone_dusts"), KJ("calculation_mechanism"))
}

function initMath(event) {
	let types = ["three", "eight", "plus", "minus", "multiply", "divide"]
	types.forEach(e => {
		event.stonecutting(KJ(e + "_cast"), IV("bronze_plate"))
		event.custom({
			"type": "tconstruct:casting_table",
			"cast": {
				"item": KJ(e + '_cast')
			},
			"fluid": {
				"name": "kubejs:raw_logic",
				"amount": 81
			},
			"result": Item.of(KJ(e)).toResultJson(),
			"cooling_time": 10
		})
	})

	let nums = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
	let ops = [(a, b) => a + b, (a, b) => a - b, (a, b) => a * b, (a, b) => b == 0 ? "error" : a / b]
	let opNames = ["plus", "minus", "multiply", "divide"]
	for (var a = 0; a < 10; a++) {
		for (var b = 0; b < 10; b++) {
			for (var op = 0; op < ops.length; op++) {

				let result = ops[op](a, b)
				var output;

				if (result == "error")
					output = KJ("missingno")
				else if (result < 0)
					continue
				else if (result > 9)
					continue
				else if (result % 1 != 0)
					continue
				else
					output = KJ(nums[result])

				event.custom({
					"type": "create:mechanical_crafting",
					"pattern": [
						"AOB"
					],
					"key": {
						"A": {
							"item": KJ(nums[a])
						},
						"O": {
							"item": KJ(opNames[op])
						},
						"B": {
							"item": KJ(nums[b])
						}
					},
					"result": {
						"item": output
					},
					"acceptMirrored": false
				})
			}
		}
	}

	let meltOrCrucible = (id, out, outAmount) => {
		event.custom({
			"type": "tconstruct:melting",
			"ingredient": { "item": id },
			"result": {
				"fluid": out,
				"amount": outAmount
			},
			"temperature": 200,
			"time": 20
		})
	}

	let alloyAmount = 810
	let outAmount = 4050
	event.custom({
		"type": "tconstruct:alloy",
		"inputs": [
			{ "name": "kubejs:number_0", "amount": alloyAmount },
			{ "name": "kubejs:number_1", "amount": alloyAmount },
			{ "name": "kubejs:number_2", "amount": alloyAmount },
			{ "name": "kubejs:number_3", "amount": alloyAmount },
			{ "name": "kubejs:number_4", "amount": alloyAmount },
			{ "name": "kubejs:number_5", "amount": alloyAmount },
			{ "name": "kubejs:number_6", "amount": alloyAmount },
			{ "name": "kubejs:number_7", "amount": alloyAmount },
			{ "name": "kubejs:number_8", "amount": alloyAmount },
			{ "name": "kubejs:number_9", "amount": alloyAmount }
		],
		"result": {
			"fluid": "kubejs:matrix",
			"amount": outAmount
		},
		"temperature": 200
	})

	meltOrCrucible(KJ("calculation_mechanism"), KJ("raw_logic"), 2430)
	meltOrCrucible(KJ("zero"), KJ("number_0"), 81)
	meltOrCrucible(KJ("one"), KJ("number_1"), 81)
	meltOrCrucible(KJ("two"), KJ("number_2"), 81)
	meltOrCrucible(KJ("three"), KJ("number_3"), 81)
	meltOrCrucible(KJ("four"), KJ("number_4"), 81)
	meltOrCrucible(KJ("five"), KJ("number_5"), 81)
	meltOrCrucible(KJ("six"), KJ("number_6"), 81)
	meltOrCrucible(KJ("seven"), KJ("number_7"), 81)
	meltOrCrucible(KJ("eight"), KJ("number_8"), 81)
	meltOrCrucible(KJ("nine"), KJ("number_9"), 81)

	event.custom({
		"type": "tconstruct:casting_basin",
		"fluid": {
			"name": "kubejs:matrix",
			"amount": 81000
		},
		"result": Item.of(KJ("computation_matrix")).toResultJson(),
		"cooling_time": 20
	})
}
function trading(event) {
	let trade = (card_id, ingredient, output) => {
		event.custom({
			type: "indrev:infuse",
			ingredients: [
				{ "item": ingredient },
				{ "item": card_id }
			],
			output: [
				Item.of(output).toResultJson()
			],
			energy: 1000
		})
	}
	global.trades.forEach(element => {
		if (global.transactions[element])
			global.transactions[element].forEach(transaction => {
				trade(KJ("trade_card_" + element), transaction.in, transaction.out)
			})
	});

	global.professions.forEach(element => {
		if (global.transactions[element])
			global.transactions[element].forEach(transaction => {
				trade(KJ("profession_card_" + element), transaction.in, transaction.out)
			})
	});
}

function spaceCraft(event) {
	event.recipes.createCompacting(KJ("matter_plastics"), [AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball")]).superheated()
	let gear = AR("netherite_gear")
	let plastic = KJ("matter_plastics")
	let machine = AE2("controller")
	let matrix = KJ("computation_matrix")
	event.remove({ output: AR("starship_control") })
	event.remove({ output: AR("starship_powersource") })
	event.recipes.createMechanicalCrafting(AR("starship_control"), [
		"AAAAA",
		"ASSSA",
		"GS SG",
		"ASSSA",
		"AAMAA"
	], {
		A: plastic,
		M: machine,
		G: gear,
		S: matrix
	})
	let smithAndMechCraft = (r, i1, i2) => {
		event.remove({ output: r })
		event.smithing(r, i1, i2)
		event.recipes.createMechanicalCrafting(r, "AB", { A: i1, B: i2 })
	}
	//	smithAndMechCraft("advancedrocketry:seat", CR("#seats"), plastic)
	smithAndMechCraft(AR("starship_powersource"), CR("fluid_tank"), plastic)
	smithAndMechCraft(AR("starship_engine"), CR("blaze_burner"), plastic)
	smithAndMechCraft(AR("rocket_ship_box_cargo"), MC("scaffolding"), plastic)
	smithAndMechCraft(AR("starship_conduit"), AP("pipe"), plastic)
}

function initComputer(event) {
	event.remove({ output: CC("computer_normal") })
	event.custom({
		"type": "tconstruct:casting_basin",
		"cast": {
			"item": "ae2:controller"
		},
		"cast_consumed": true,
		"fluid": {
			"name": "tconstruct:molten_electrum",
			"amount": 27000
		},
		"result": "computercraft:computer_normal",
		"cooling_time": 20
	})
}

onEvent("recipes", event => {
	tweaks(event)
	oreProcessing(event)
	initAndesiteMachine(event)
	initCopperMachine(event)
	initBrassMachine(event)
	initZincMachine(event)
	initObsidianMachine(event)
	initInvarMachine(event)
	algalAndesite(event)
	initEnderMachine(event)
	alchemy(event)
	initFluixMachine(event)
	initComputer(event)
	initMath(event)
	spaceCraft(event)
	alloying(event)
})

onEvent("item.tags", event => {
	event.get("create:upright_on_belt")
		.add(AE2("red_paint_ball"))
		.add(AE2("yellow_paint_ball"))
		.add(AE2("green_paint_ball"))
		.add(AE2("blue_paint_ball"))
		.add(AE2("magenta_paint_ball"))
		.add(AE2("black_paint_ball"))
})