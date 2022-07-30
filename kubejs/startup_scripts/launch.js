let modpackId = "cabricality"

let MOD = (domain, id, x) => (x ? `${x}x ` : "") + (id.startsWith("#") ? "#" : "") + domain + ":" + id.replace("#", "")
let CR = (id, x) => MOD("create", id, x)
let MC = (id, x) => MOD("minecraft", id, x)
let KJ = (id, x) => MOD("kubejs", id, x)
let C = (id, x) => MOD("c", id, x)
let F = (id, x) => MOD("fabric", id, x)
let IV = (id, x) => MOD("indrev", id, x)
let AE2 = (id, x) => MOD("ae2", id, x)
let KB = (id, x) => MOD("kibe", id, x)
let PMD = (id, x) => MOD("promenade", id, x)
let AP = (id, x) => MOD("architects_palette", id, x)
let FD = (id, x) => MOD("farmersdelight", id, x)

onEvent("item.registry", event => {
	//	Mechanism
	let registerMechanism = (name, localName, rarity) => {
		let id = name.toLowerCase() + "_mechanism"
		let incompleteId = "incomplete_" + id

		event.create(id)
			.texture(modpackId + ":/item/mechanism/" + id)
			.displayName(localName + " Mechanism")
			.rarity(rarity ? rarity : RARITY_COMMON)

		event.create(incompleteId)
			.texture(modpackId + ":/item/mechanism/incomplete/" + incompleteId)
			.displayName("Incomplete " + localName + " Mechanism")
			.rarity(rarity ? rarity : RARITY_COMMON)
	}
	let initMechanisms = () => {
		registerMechanism("Kinetic", "Kinetic")
		registerMechanism("Sealed", "Sealed")
		registerMechanism("Infernal", "Infernal")
		registerMechanism("Sturdy", "Sturdy")
		registerMechanism("Inductive", "Inductive")
		registerMechanism("Abstruse", "Abstruse")
		registerMechanism("Calculation", "Calculation")
	}

	//	Machine Parts
	let registerMachinePart = (name, localName) => {
		let id = name.replace(" ", "_").toLowerCase()

		event.create(id)
			.texture(modpackId + ":/item/machine_part/" + id)
			.displayName(localName)
	}
	let initMachineParts = () => {
		registerMachinePart("Saw Blade", "Saw Blade")
	}

	//	Tools
	let registerSaw = (materialName, materialId, durability) => {
		let id = materialId + "_saw"

		event.create(id)
			.texture(modpackId + ":item/tool/saw/" + id)
			.displayName(materialName + " Saw")
			.maxDamage(durability)
	}
	let registerToolMaterial = (material, localName, durability) => {
		let id = material.replace(" ", "_").toLowerCase()
		registerSaw(localName, id, durability)
	}
	let initToolMaterials = () => {
		registerToolMaterial("Stone", "Stone", 131)
		registerToolMaterial("Iron", "Iron", 250)
		registerToolMaterial("Diamond", "Diamond", 1561)
		registerToolMaterial("Netherite", "Netherite", 2031)
	}

	//	Other items
	let registerTypicalItem = (name, localName) => {
		let id = name.replace(" ", "_").toLowerCase()

		event.create(id)
			.texture(modpackId + ":item/" + id)
			.displayName(localName)
	}
	let initTypicalItems = () => {
		registerTypicalItem("Stone Rod", "Stone Rod")
		registerTypicalItem("Rubber", "Rubber")
		registerTypicalItem("Cured Rubber", "Rubber")

		event.create("screwdriver")
			.texture(modpackId + ":item/screwdriver")
			.displayName("Reinforced Screwdriver")
			.maxDamage(512)

		registerTypicalItem("Invar Ingot", "Invar Ingot")
		registerTypicalItem("Nickel Ingot", "Nickel Ingot")
		registerTypicalItem("Nickel Nugget", "Nickel Ingot")
		registerTypicalItem("Enderium Ingot", "Enderium Ingot")
		registerTypicalItem("Nickel Compound", "Nickel Compound")
		registerTypicalItem("Invar Compound", "Invar Compound")
		registerTypicalItem("Silicon Compound", "Silicon Compound")

		registerTypicalItem("Ruby", "Ruby")
		registerTypicalItem("Sapphire", "Sapphire")

		registerTypicalItem("Gold Coin", "Gold Coin")
		registerTypicalItem("Silver Coin", "Silver Coin")

		registerTypicalItem("Sand Ball", "Sand Ball")
		registerTypicalItem("Coke Chunk", "Coke Chunk")
		registerTypicalItem("Rough Sand", "Rough Sand")
		registerTypicalItem("Purified Sand", "Purified Sand")

		registerTypicalItem("Emerald Dust", "Emerald Dust")
		registerTypicalItem("Diamond Dust", "Diamond Dust")

		registerTypicalItem("Basalz Shard", "Basalz Shard")
		registerTypicalItem("Basalz Powder", "Basalz Powder")
		//		registerTypicalItem("Blitz Mote")
		//		registerTypicalItem("Blitz Powder")
		registerTypicalItem("Blizz Cube", "Blizz Cube")
		registerTypicalItem("Blizz Powder", "Blizz Powder")
		//		registerTypicalItem("Smoke Mote")

		registerTypicalItem("Ice Charge", "Ice Charge")
		registerTypicalItem("Earth Charge", "Earth Charge")
		registerTypicalItem("Lightning Charge", "Lightning Charge")

		registerTypicalItem("Circuit Scrap", "Circuit Scrap")
		registerTypicalItem("Zinc Sheet", "Zinc Sheet")
		registerTypicalItem("Matter Plastics", "Matter Plastics")

		event.create("coal_coke")
			.texture(modpackId + ":item/coal_coke")
			.displayName("Coke")
			.burnTime(3200)

		event.create("incomplete_coke_chunk")
			.texture(modpackId + ":item/incomplete_coke_chunk")
			.displayName("Incomplete Coke Chunk")

		event.create("raw_logic_sheet")
			.texture(modpackId + ":item/raw_logic_sheet")
			.displayName("Raw Logic Sheet")
			.glow(true)



		event.create("earth_slimy_fern")
			.texture(modpackId + ":item/fern/earth_slimy_fern")
			.displayName("Slimy Fern")

		event.create("ender_slimy_fern")
			.texture(modpackId + ":item/fern/ender_slimy_fern")
			.displayName("Slimy Fern")

		event.create("sky_slimy_fern")
			.texture(modpackId + ":item/fern/sky_slimy_fern")
			.displayName("Slimy Fern")



		event.create("earth_slimy_fern_leaf")
			.texture(modpackId + ":item/fern/leaf/earth_slimy_fern_leaf")
			.displayName("Slimy Fern Leaf")

		event.create("ender_slimy_fern_leaf")
			.texture(modpackId + ":item/fern/leaf/ender_slimy_fern_leaf")
			.displayName("Slimy Fern Leaf")

		event.create("sky_slimy_fern_leaf")
			.texture(modpackId + ":item/fern/leaf/sky_slimy_fern_leaf")
			.displayName("Slimy Fern Leaf")



		event.create("earth_slimy_fern_paste")
			.texture(modpackId + ":item/fern/paste/earth_slimy_fern_paste")
			.displayName("Slimy Fern Paste")

		event.create("ender_slimy_fern_paste")
			.texture(modpackId + ":item/fern/paste/ender_slimy_fern_paste")
			.displayName("Slimy Fern Paste")

		event.create("sky_slimy_fern_paste")
			.texture(modpackId + ":item/fern/paste/sky_slimy_fern_paste")
			.displayName("Slimy Fern Paste")



		event.create("radiant_sheet")
			.texture(modpackId + ":item/radiant_sheet")
			.displayName("Radiant Sheet")
			.glow(true)

		event.create("radiant_coil")
			.texture(modpackId + ":item/radiant_coil")
			.displayName("Radiant Coil")
			.glow(true)



		event.create("chromatic_resonator")
			.texture(modpackId + ":item/chromatic_resonator")
			.displayName("Chromatic Resonator")
			.maxDamage(512)

		event.create("dye_entangled_singularity")
			.texture(modpackId + ":item/dye_entangled_singularity")
			.unstackable()
			.displayName("Dyed Entangled Singularity")

		event.create("flash_drive")
			.texture(modpackId + ":item/boot_medium")
			.displayName("Flash Drive")
			.maxDamage(512)

		event.create("charged_calculator")
			.texture(modpackId + ":item/charged_calculator")
			.displayName("Charged Calculator")
			.maxDamage(64)

		let processors = ["Calculation", "Logic", "Engineering"]
		let processorsLocalName = ["Calculation", "Logic", "Engineering"]
		processors.forEach(name => {
			let id = name.toLowerCase()
			let localName = processorsLocalName[processors.indexOf(name)]
			event.create("incomplete_" + id + "_processor")
				.texture(modpackId + ":item/processor/incomplete_" + id + "_processor")
				.displayName("Incomplete " + localName + " Processor")
		})
	}

	//	Final init
	let initItems = () => {
		initMechanisms()
		initMachineParts()
		initToolMaterials()
		initTypicalItems()
	}
	initItems()
	let number = (name, localName) => {
		let id = name.toLowerCase()
		event.create(id)
			.texture(modpackId + ":item/number/" + id)
			.displayName(localName)
			.glow(true)
	}
	number("Zero", "0")
	number("One", "1")
	number("Two", "2")
	number("Three", "3")
	number("Four", "4")
	number("Five", "5")
	number("Six", "6")
	number("Seven", "7")
	number("Eight", "8")
	number("Nine", "9")
	number("Plus", "+")
	number("Minus", "-")
	number("Multiply", "×")
	number("Divide", "÷")
	number("Missingno", "NaN")

	event.create("number_array")
		.texture(modpackId + ":item/number/number_array")
		.displayName("Number Array")
		.glow(true)
})

onEvent("block.registry", event => {
	//	Machine
	let registerMachine = (name, localName, layer) => {
		let id = name.toLowerCase() + "_machine"
		event.create(id)
			.model(modpackId + ":block/machine/" + id)
			.material("lantern")
			.hardness(3.0)
			.displayName(localName + " Machine")
			.notSolid()
			.renderType(layer)
			.tagBlock("create:wrench_pickup")
			.tagBlock("minecraft:mineable/pickaxe")
	}
	registerMachine("Extractor", "Extractor", "solid")
	registerMachine("Andesite", "Andesite", "solid")
	registerMachine("Brass", "Brass", "translucent")
	registerMachine("Copper", "Copper", "cutout")
	registerMachine("Zinc", "Zinc", "cutout")
	registerMachine("Enderium", "Enderium", "cutout")
	registerMachine("Obsidian", "Obsidian", "translucent")

	let registerCasing = (name, localName) => {
		let id = name.toLowerCase() + "_casing"
		event.create(id)
			.model(modpackId + ":block/casing/" + id)
			.material("metal")
			.hardness(3.0)
			.displayName(localName + " Casing")
			.tagBlock("create:wrench_pickup")
			.tagBlock("minecraft:mineable/pickaxe")
	}
	registerCasing("Invar", "Invar")
	registerCasing("Fluix", "Fluix")
	registerCasing("Zinc", "Zinc")
	registerCasing("Enderium", "Enderium")

	event.create("computation_matrix")
		.model(modpackId + ":block/computation_matrix")
		.material("metal")
		.hardness(3.0)
		.displayName("Computation Matrix")
		.tagBlock("minecraft:mineable/pickaxe")

	for (i = 0; i < 15; i++) {
		event.create(`failed_alchemy_${i}`)
			.material("glass")
			.color(0, 0x394867)
			.color(1, 0x14274E)
			.hardness(0.1)
			.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
			.model(modpackId + ":block/mundane_substrate")
			.displayName(`Mundane Alchemic Blend ${i + 1}`)
			.renderType("cutout")
			.item(e => e.color(0, 0x394867).color(1, 0x14274E))
	}
	global.substrates = []
	global.substrate_mapping = {}
	var current_category = []
	var category_index = 0
	var substrate_index = 0

	let category = () => {
		global.substrates.push(current_category)
		current_category = []
		category_index++
		substrate_index = 0
	}

	let substrate_base = (c1, c2, id, name, localName, model, ingredient, outputItem) => {
		global.substrate_mapping[id] = {
			category: category_index,
			index: substrate_index,
			name: name
		}
		current_category.push({
			id: "kubejs" + `:substrate_${id}`,
			ingredient: ingredient,
			outputItem: outputItem
		})
		event.create(`substrate_${id}`)
			.material("glass")
			.color(0, c1)
			.color(1, c2)
			.hardness(0.1)
			.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
			.model(modpackId + ":block/" + model)
			.displayName(localName)
			.renderType("cutout")
			.item(e => e.rarity(model == "catalyst" ? RARITY_UNCOMMON : RARITY_COMMON).color(0, c1).color(1, c2))
		substrate_index++
	}

	let reagent = (c1, c2, id, name, localName, ingredient, outputItem) => substrate_base(c1, c2, id, name, `${localName} Reagent`, "substrate", ingredient, outputItem)
	let catalyst = (c1, c2, id, name, localName, ingredient) => substrate_base(c1, c2, id, name, `${localName} Catalyst`, "catalyst", ingredient)

	reagent(0x5F5F5F, 0x8E8E8E, "andesite", "Andesite", "§7Andesite§r", "minecraft:andesite")
	reagent(0x7F7F7F, 0xD4D4D4, "diorite", "Diorite", "§7Diorite§r", "minecraft:diorite")
	reagent(0x563A2F, 0x9A6C5B, "granite", "Granite", "§7Granite§r", "minecraft:granite")
	reagent(0x585858, 0x646363, "cobblestone", "Stone", "§7Stone§r", "minecraft:cobblestone")
	reagent(0x32333D, 0x5C5C5C, "basalt", "Basalt", "§7Basalt§r", "minecraft:basalt")
	reagent(0x6B5D4F, 0x7D6B5A, "limestone", "Limestone", "§7Limestone§r", "create:limestone")
	category()
	reagent(0xD30000, 0xB80F0A, "red", "Crimson", "§4Crimson§r", "minecraft:red_dye")
	reagent(0xFC6600, 0xb1560f, "orange", "Orange", "§6Orange§r", "minecraft:orange_dye")		//	biomesoplenty:burning_blossom
	reagent(0xFFF200, 0xdba520, "yellow", "Goldenrod", "§eGoldenrod§r", "minecraft:yellow_dye")	//	biomesoplenty:goldenrod
	reagent(0x9dc183, 0x708238, "green", "Olive", "§2Olive§r", "minecraft:green_dye")	 //	 biomesoplenty:watergrass
	reagent(0x57a0d2, 0x0080fe, "blue", "Azure", "§3Azure§r", "minecraft:light_blue_dye")	//	biomesoplenty:blue_hydrangea
	reagent(0xb200ed, 0xff66cc, "magenta", "Fuchsia", "§dFuchsia§r", "minecraft:magenta_dye")
	category()
	reagent(0xAC3B00, 0xD5AC26, "blaze", "Blazing", "§6Blazing§r", "minecraft:blaze_powder")
	reagent(0x4F7E48, 0x8AD480, "slime", "Slime", "§aSlime§r", "minecraft:slime_ball")
	reagent(0x5B151A, 0xBC3E49, "nether", "Nether", "§4Nether§r", "minecraft:nether_wart")
	reagent(0x05030A, 0x36234C, "obsidian", "Obsidian", "§8Obsidian§r", "create:powdered_obsidian")
	reagent(0x535353, 0x717171, "gunpowder", "Gunpowder", "§8Gunpowder§r", "minecraft:gunpowder")
	reagent(0x529680, 0xA2CFC0, "prismarine", "Aquatic", "§3Aquatic§r", "minecraft:prismarine_shard")
	category()
	reagent(0xC7A94A, 0xEEF071, "sulfur", "Sulfuric", "§6Sulfuric§r", "indrev:sulfur_dust")
	reagent(0x91C5FC, 0xA7CBCF, "certus", "Certus Quartz", "§bCertus Quartz§r", "ae2:certus_quartz_dust")
	category()
	reagent(0x616A60, 0xD0D2C5, "zinc", "Zinc", "§3Zinc§r", "createplus:zinc_dust")
	reagent(0xDD7E5D, 0xFCEFBA, "copper", "Copper", "§6Copper§r", "indrev:copper_dust")
	reagent(0xA6A6A6, 0xD5D5D5, "iron", "Iron", "§7Iron§r", "indrev:iron_dust")
	reagent(0x232456, 0x7C95A4, "lead", "Lead", "§8Lead§r", "indrev:lead_dust")
	reagent(0xD99413, 0xFAF25E, "gold", "Gold", "§eGold§r", "indrev:gold_dust")
	category()
	//	reagent(0xFC7781, 0xFCCED0, "cinnabar", "Cinnabar", "§c朱砂", "thermal:cinnabar")
	reagent(0x335DC1, 0x7395E7, "lapis", "Lapis Lazuli", "§1Lapis Lazuli§r", "minecraft:lapis_lazuli")
	reagent(0x00A82B, 0xADFACB, "emerald", "Emerald", "§2Emerald§r", "kubejs:emerald_dust")
	reagent(0x20C3B3, 0xD2FCF3, "diamond", "Diamond", "§bDiamond§r", "kubejs:diamond_dust")
	reagent(0x9D0A33, 0xFB7B71, "ruby", "Ruby", "§cRuby§r", "kubejs:ruby")
	reagent(0x246BE9, 0x76C6FC, "sapphire", "Sapphire", "§9Sapphire§r", "kubejs:sapphire")
	category()
	catalyst(0x506D84, 0x889EAF, "igneous", "Igneous", "§dIgneous")
	catalyst(0xB5CDA3, 0xC9E4C5, "herbal", "Herbal", "§dHerbal")
	catalyst(0x9F5F80, 0xFF8474, "volatile", "Volatile", "§dVolatile")
	catalyst(0xFFB037, 0xFFE268, "crystal", "Crystalline", "§dCrystalline")
	catalyst(0x232457, 0x7D97A6, "metal", "Metallurgic", "§dMetallurgic")
	catalyst(0x3EDBF0, 0xC0FEFC, "gem", "Gemstone", "§dGemstone")
	category()

	event.create("substrate_chaos")
		.material("glass")
		.color(0, 0xb200ed)
		.color(1, 0xff66cc)
		.hardness(0.1)
		.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
		.model(modpackId + ":block/chaos_catalyst")
		.displayName("§5Chaos Catalyst")
		.renderType("cutout")
		.item(e => e.rarity(RARITY_RARE).color(0, 0xb200ed).color(1, 0xff66cc))

	event.create("substrate_silicon")
		.material("glass")
		.color(0, 0x474449)
		.color(1, 0x967DA0)
		.hardness(0.1)
		.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
		.model(modpackId + ":block/substrate")
		.displayName("§dSilicon Reagent")
		.renderType("cutout")
		.item(e => e.rarity(RARITY_EPIC).color(0, 0x474449).color(1, 0x967DA0))


	event.create("substrate_silver")
		.material("glass")
		.color(0, 0x9FADB4)
		.color(1, 0xBECCD2)
		.hardness(0.1)
		.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
		.model(modpackId + ":block/substrate")
		.displayName("§7Silver §rReagent")
		.renderType("cutout")
		.item(e => e.color(0, 0x9FADB4).color(1, 0xBECCD2))

	event.create("accellerator_glowstone")
		.material("glass")
		.color(0, 0xFFBC5E)
		.hardness(0.1)
		.box(.125, 0, .125, .875, 10.0 / 16.0, .875, false)
		.model(modpackId + ":block/accellerator")
		.displayName("§6Glowstone §rAccelerator")
		.renderType("cutout")
		.item(e => e.color(0, 0xFFBC5E))

	event.create(`accellerator_redstone`)
		.material("glass")
		.color(0, 0xAA0F01)
		.hardness(0.1)
		.box(.125, 0, .125, .875, 10.0 / 16.0, .875, false)
		.model(modpackId + ":block/accellerator")
		.displayName("§cRedstone §rAccelerator")
		.renderType("cutout")
		.item(e => e.color(0, 0xAA0F01))


})

onEvent("fluid.registry", event => {
	event.create("resin")
		.displayName("Resin")
		.thinTexture(0xaf7519)

	event.create("redstone")
		.displayName("Redstone")
		.thinTexture(0x850b0e)
		.noBlock()

	event.create("sky_stone")
		.displayName("Sky Stone")
		.thinTexture(0x404344)
		.noBlock()

	event.create("liquid_soul")
		.displayName("Liquid Soul")
		.thinTexture(0x604b3f)
		.noBlock()

	event.create("slime")
		.displayName("§aSilme")
		.thinTexture(0x75db89)
		.noBlock()

	event.create("waste")
		.displayName("Waste")
		.thinTexture(0x123d36)
		.noBlock()

	event.create("powered_water")
		.displayName("Powered Water")
		.thinTexture(0x76d0f9)

	event.create("coke")
		.displayName("Coke")
		.thinTexture(0x323232).noBlock()



	event.create("molten_zinc")
		.displayName("Molten Zinc")
		.thickTexture(0xb0b29f)

	event.create("molten_tungsten")
		.displayName("Molten Tungsten")
		.thickTexture(0x57594c)

	event.create("molten_brass")
		.displayName("Molten Brass")
		.thickTexture(0xe0c36f)

	event.create("molten_diamond")
		.displayName("Molten Diamond")
		.thickTexture(0x4ee5ca)

	event.create("molten_enderium")
		.displayName("Molten Enderium").
		thinTexture(0x185f74)

	event.create("molten_glass")
		.displayName("Molten Glass")
		.thinTexture(0xcfe6e5)



	event.create("fine_sand")
		.displayName("Fine Sand").noBlock()
		.thickTexture(0xded6a4)

	let colors = [0xCBE827, 0xAEE827, 0x68E827, 0x27E86E, 0x27E8B1, 0x27DEE8, 0x27B5E8, 0x2798E8, 0x2778E8, 0x2748E8]
	event.create("raw_logic")
		.displayName("Liquified Logic (Unprocessed)")
		.thinTexture(0xE7FFCB)
		.noBlock()
	/*	for (i = 0; i < 10; i++) {
			event.create("number_" + i)
				.displayName("玻色-爱因斯坦凝聚态逻辑（${i}）")
				.thinTexture(colors[i]).noBlock()
		}	*/
	event.create("matrix")
		.displayName("Liquified Computation Matrix")
		.thinTexture(colors[0])
		.noBlock()
})

onEvent("item.modification", event => {
	let colors = ["red", "yellow", "green", "blue", "magenta", "black"]
	colors.forEach(element => {
		event.modify("ae:" + element + "_paint_ball", item => {
			item.maxStackSize = 1
		})
	});
})