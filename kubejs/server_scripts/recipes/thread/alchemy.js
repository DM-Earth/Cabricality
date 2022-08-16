onEvent("recipes", event => {
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
})