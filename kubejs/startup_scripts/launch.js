
let modpackId = "cabricality"

let MOD = (domain, id, x) => (x ? `${x}x ` : "") + (id.startsWith('#') ? '#' : "") + domain + ":" + id.replace('#', '')
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
	//Mechanism
	let registerMechanism = (name, rarity) => {
		let id = name.toLowerCase() + "_mechanism"
		let incompleteId = "incomplete_" + id
		event.create(id).texture(modpackId + ":/item/mechanism/" + id).displayName(name + " Mechanism").rarity(rarity ? rarity : RARITY_COMMON)
		event.create(incompleteId).texture(modpackId + ":/item/mechanism/incomplete/" + incompleteId).displayName("Incomplete " + name + " Mechanism").displayName("Incomplete " + name + " Mechanism")
	}
	let initMechanisms = () => {
		registerMechanism("Kinetic")
		registerMechanism("Sealed")
		registerMechanism("Infernal")
		registerMechanism("Inductive")
		registerMechanism("Abstruse")
		registerMechanism("Calculation")
	}

	//Machine Parts
	let registerMachinePart = (name) => {
		let id = name.replace(" ", "_").toLowerCase()
		event.create(id).texture(modpackId + ":/item/machine_part/" + id).displayName(name)
	}
	let initMachineParts = () => {
		registerMachinePart("Saw Blade")
	}

	//Tools
	let registerSaw = (materialName, materialId, durability) => {
		let id = materialId + "_saw"
		event.create(id).texture(modpackId + ":item/tool/saw/" + id).displayName(materialName + " Saw").maxDamage(durability)
	}
	let registerToolMaterial = (material, durability) => {
		let id = material.replace(" ", "_").toLowerCase()
		registerSaw(material, id, durability)
	}
	let initToolMaterials = () => {
		registerToolMaterial("Stone", 131)
		registerToolMaterial("Iron", 250)
		registerToolMaterial("Diamond", 1561)
		registerToolMaterial("Netherite", 2031)
	}

	//other items
	let registerTypicalItem = (name) => {
		let id = name.replace(" ", "_").toLowerCase()
		event.create(id).texture(modpackId + ":item/" + id).displayName(name)
	}
	let initTypicalItems = () => {
		registerTypicalItem("Stone Rod")
		registerTypicalItem("Rubber")
		registerTypicalItem("Cured Rubber")
		event.create("screwdriver").texture(modpackId + ":item/screwdriver").displayName("Reinforced Screwdriver").maxDamage(64)

		registerTypicalItem("Invar Ingot")
		registerTypicalItem("Nickel Ingot")
		registerTypicalItem("Nickel Nugget")
		registerTypicalItem("Enderium Ingot")
		registerTypicalItem("Nickel Compound")
		registerTypicalItem("Invar Compound")
		registerTypicalItem("Silicon Compound")

		registerTypicalItem("Ruby")
		registerTypicalItem("Sapphire")

		registerTypicalItem("Gold Coin")
		registerTypicalItem("Silver Coin")

		registerTypicalItem("Sand Ball")
		registerTypicalItem("Coke Chunk")
		registerTypicalItem("Rough Sand")
		registerTypicalItem("Purified Sand")

		registerTypicalItem("Emerald Dust")
		registerTypicalItem("Diamond Dust")

		registerTypicalItem("Basalz Shard")
		registerTypicalItem("Basalz Powder")
		//		registerTypicalItem("Blitz Mote")
		//		registerTypicalItem("Blitz Powder")
		registerTypicalItem("Blizz Cube")
		registerTypicalItem("Blizz Powder")
		//		registerTypicalItem("Smoke Mote")

		registerTypicalItem("Ice Charge")
		registerTypicalItem("Earth Charge")
		registerTypicalItem("Lightning Charge")

		registerTypicalItem("Circuit Scrap")
		registerTypicalItem("Zinc Sheet")
		registerTypicalItem("Matter Plastics")

		event.create("coal_coke").texture(modpackId + ":item/coal_coke").displayName("Coke").burnTime(3200)
		event.create("incomplete_coke_chunk").texture(modpackId + ":item/incomplete_coke_chunk").displayName("Incomplete Coke Chunk")
		event.create("raw_logic_sheet").texture(modpackId + ":item/raw_logic_sheet").displayName("Raw Logic Sheet").glow(true)

		event.create("earth_slimy_fern").texture(modpackId + ":item/fern/earth_slimy_fern").displayName("Slimy Fern")
		event.create("ender_slimy_fern").texture(modpackId + ":item/fern/ender_slimy_fern").displayName("Slimy Fern")
		event.create("sky_slimy_fern").texture(modpackId + ":item/fern/sky_slimy_fern").displayName("Slimy Fern")

		event.create("earth_slimy_fern_leaf").texture(modpackId + ":item/fern/leaf/earth_slimy_fern_leaf").displayName("Slimy Fern Leaf")
		event.create("ender_slimy_fern_leaf").texture(modpackId + ":item/fern/leaf/ender_slimy_fern_leaf").displayName("Slimy Fern Leaf")
		event.create("sky_slimy_fern_leaf").texture(modpackId + ":item/fern/leaf/sky_slimy_fern_leaf").displayName("Slimy Fern Leaf")

		event.create("earth_slimy_fern_paste").texture(modpackId + ":item/fern/paste/earth_slimy_fern_paste").displayName("Slimy Fern Paste")
		event.create("ender_slimy_fern_paste").texture(modpackId + ":item/fern/paste/ender_slimy_fern_paste").displayName("Slimy Fern Paste")
		event.create("sky_slimy_fern_paste").texture(modpackId + ":item/fern/paste/sky_slimy_fern_paste").displayName("Slimy Fern Paste")

		event.create("radiant_sheet").texture(modpackId + ":item/radiant_sheet").displayName("Radiant Sheet").glow(true)
		event.create("radiant_coil").texture(modpackId + ":item/radiant_coil").displayName("Radiant Coil").glow(true)

		event.create('chromatic_resonator').texture(modpackId + ":item/chromatic_resonator").displayName('Chromatic Resonator').maxDamage(512)
		event.create('dye_entangled_singularity').texture(modpackId + ":item/dye_entangled_singularity").unstackable().displayName('Chromatic Singularity')
		event.create('flash_drive').texture(modpackId + ":item/boot_medium").displayName('Flash Drive').maxDamage(512)
		event.create('charged_calculator').texture(modpackId + ":item/charged_calculator").displayName('Calculator').maxDamage(64)

		let processors = ["Calculation", "Logic", "Engineering"]
		processors.forEach(name => {
			let e = name.toLowerCase()
			event.create('incomplete_' + e + '_processor').texture(modpackId + ':item/processor/incomplete_' + e + '_processor').displayName('Incomplete ' + name + ' Processor')
		})
	}

	//final init
	let initItems = () => {
		initMechanisms()
		initMachineParts()
		initToolMaterials()
		initTypicalItems()
	}
	initItems()
	let number = (name) => {
		let id = name.toLowerCase()
		event.create(id).texture(modpackId + ":item/number/" + id).glow(true).displayName(name)
	}
	number('Zero')
	number('One')
	number('Two')
	number('Three')
	number('Four')
	number('Five')
	number('Six')
	number('Seven')
	number('Eight')
	number('Nine')
	number('Plus')
	number('Minus')
	number('Multiply')
	number('Divide')
	number('Missingno')
	event.create('number_array').texture(modpackId + ":item/number/number_array").glow(true).displayName('Number Array')
	
})

onEvent("block.registry", event => {
	//Machine
	let registerMachine = (name, layer) => {
		let id = name.toLowerCase() + "_machine"
		event.create(id)
			.model(modpackId + ":block/machine/" + id)
			.material("lantern")
			.hardness(3.0)
			.displayName(name + " Machine")
			.notSolid()
			.renderType(layer)
			.tagBlock("create:wrench_pickup")
			.tagBlock("minecraft:mineable/pickaxe")
	}
	registerMachine("Extractor", "solid")
	registerMachine("Andesite", "solid")
	registerMachine("Brass", "translucent")
	registerMachine("Copper", "cutout")
	registerMachine("Zinc", "cutout")
	registerMachine("Enderium", "cutout")
	//	registerMachine("Invar", "cutout")

	let registerCasing = (name) => {
		let id = name.toLowerCase() + "_casing"
		event.create(id)
			.model(modpackId + ":block/casing/" + id)
			.material("metal")
			.hardness(3.0)
			.displayName(name + " Casing")
			.tagBlock("create:wrench_pickup")
			.tagBlock("minecraft:mineable/pickaxe")
	}
	registerCasing("Invar")
	registerCasing("Fluix")
	registerCasing("Zinc")
	registerCasing("Enderium")

	event.create('computation_matrix')
		.model(modpackId + ":block/computation_matrix")
		.material("metal")
		.hardness(3.0)
		.displayName("Computation Matrix")
		.tagBlock("minecraft:mineable/pickaxe")

	for (i = 0; i < 15; i++) {
		event.create(`failed_alchemy_${i}`)
			.material('glass')
			.color(0, 0x394867)
			.color(1, 0x14274E)
			.hardness(0.1)
			.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
			.model(modpackId + ":block/mundane_substrate")
			.displayName(`Mundane Alchemic Blend`)
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

	let substrate_base = (c1, c2, id, name, model, ingredient, outputItem) => {
		global.substrate_mapping[id] = {
			category: category_index,
			index: substrate_index,
			name: name.replace(" Reagent", "").replace(" Catalyst", "")
		}
		current_category.push({
			id: "kubejs" + `:substrate_${id}`,
			ingredient: ingredient,
			outputItem: outputItem
		})
		event.create(`substrate_${id}`)
			.material('glass')
			.color(0, c1)
			.color(1, c2)
			.hardness(0.1)
			.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
			.model(modpackId + ":block/" + model)
			.displayName(name)
			.renderType("cutout")
			.item(e => e.rarity(model == "catalyst" ? RARITY_UNCOMMON : RARITY_COMMON).color(0, c1).color(1, c2))
		substrate_index++
	}

	let reagent = (c1, c2, id, prefix, ingredient, outputItem) => substrate_base(c1, c2, id, `${prefix} Reagent`, "substrate", ingredient, outputItem)
	let catalyst = (c1, c2, id, prefix, ingredient) => substrate_base(c1, c2, id, `${prefix} Catalyst`, "catalyst", ingredient)

	reagent(0x5F5F5F, 0x8E8E8E, "andesite", "Andesite", "minecraft:andesite")
	reagent(0x7F7F7F, 0xD4D4D4, "diorite", "Diorite", "minecraft:diorite")
	reagent(0x563A2F, 0x9A6C5B, "granite", "Granite", "minecraft:granite")
	reagent(0x585858, 0x646363, "cobblestone", "Stone", "minecraft:cobblestone")
	reagent(0x32333D, 0x5C5C5C, "basalt", "Basalt", "minecraft:basalt")
	reagent(0x6B5D4F, 0x7D6B5A, "limestone", "Limestone", "create:limestone")
	category()
	reagent(0xD30000, 0xB80F0A, "red", "Crimson", ["minecraft:rose_bush", "minecraft:poppy", "minecraft:red_tulip"], "minecraft:red_dye")
	reagent(0xFC6600, 0xb1560f, "orange", "Orange", ["minecraft:orange_tulip", "minecraft:pumpkin"], "minecraft:orange_dye")  //biomesoplenty:burning_blossom
	reagent(0xFFF200, 0xdba520, "yellow", "Goldenrod", ["minecraft:sunflower", "minecraft:dandelion"], "minecraft:yellow_dye") //biomesoplenty:goldenrod
	reagent(0x9dc183, 0x708238, "green", "Olive", ["minecraft:fern", "minecraft:cactus"], "minecraft:green_dye") //biomesoplenty:watergrass
	reagent(0x57a0d2, 0x0080fe, "blue", "Azure", ["minecraft:cornflower", "minecraft:blue_orchid"], "minecraft:light_blue_dye") //biomesoplenty:blue_hydrangea
	reagent(0xb200ed, 0xff66cc, "magenta", "Fuchsia", ["minecraft:lilac", "minecraft:allium", "minecraft:pink_tulip"], "minecraft:magenta_dye")
	category()
	reagent(0xAC3B00, 0xD5AC26, "blaze", "Blazing", "minecraft:blaze_powder")
	reagent(0x4F7E48, 0x8AD480, "slime", "Slime", "minecraft:slime_ball")
	reagent(0x5B151A, 0xBC3E49, "nether", "Nether", "minecraft:nether_wart")
	reagent(0x05030A, 0x36234C, "obsidian", "Obsidian", "create:powdered_obsidian")
	reagent(0x535353, 0x717171, "gunpowder", "Gunpowder", "minecraft:gunpowder")
	reagent(0x529680, 0xA2CFC0, "prismarine", "Aquatic", "minecraft:prismarine_shard")
	category()
	reagent(0xC7A94A, 0xEEF071, "sulfur", "Sulfuric", "indrev:sulfur_dust")
	reagent(0x91C5FC, 0xA7CBCF, "certus", "Certus Quartz", "ae2:certus_quartz_dust")
	category()
	reagent(0x616A60, 0xD0D2C5, "zinc", "Zinc", "createplus:zinc_dust")
	reagent(0xDD7E5D, 0xFCEFBA, "copper", "Copper", "indrev:copper_dust")
	reagent(0xA6A6A6, 0xD5D5D5, "iron", "Iron", "indrev:iron_dust")
	reagent(0x232456, 0x7C95A4, "lead", "Lead", "indrev:lead_dust")
	reagent(0xD99413, 0xFAF25E, "gold", "Gold", "indrev:gold_dust")
	category()
	//	reagent(0xFC7781, 0xFCCED0, "cinnabar", "Cinnabar", "thermal:cinnabar")
	reagent(0x335DC1, 0x7395E7, "lapis", "Lapis Lazuli", "minecraft:lapis_lazuli")
	reagent(0x00A82B, 0xADFACB, "emerald", "Emerald", "kubejs:emerald_dust")
	reagent(0x20C3B3, 0xD2FCF3, "diamond", "Diamond", "kubejs:diamond_dust")
	reagent(0x9D0A33, 0xFB7B71, "ruby", "Ruby", "kubejs:ruby")
	reagent(0x246BE9, 0x76C6FC, "sapphire", "Sapphire", "kubejs:sapphire")
	category()
	catalyst(0x506D84, 0x889EAF, "igneous", "Igneous")
	catalyst(0xB5CDA3, 0xC9E4C5, "herbal", "Herbal")
	catalyst(0x9F5F80, 0xFF8474, "volatile", "Volatile")
	catalyst(0xFFB037, 0xFFE268, "crystal", "Crystalline")
	catalyst(0x232457, 0x7D97A6, "metal", "Metallurgic")
	catalyst(0x3EDBF0, 0xC0FEFC, "gem", "Gemstone")
	category()

	event.create(`substrate_chaos`)
		.material('glass')
		.color(0, 0xb200ed)
		.color(1, 0xff66cc)
		.hardness(0.1)
		.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
		.model(modpackId + ":block/chaos_catalyst")
		.displayName("Chaos Catalyst")
		.renderType("cutout")
		.item(e => e.rarity(RARITY_RARE).color(0, 0xb200ed).color(1, 0xff66cc))

	event.create(`substrate_silicon`)
		.material('glass')
		.color(0, 0x474449)
		.color(1, 0x967DA0)
		.hardness(0.1)
		.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
		.model(modpackId + ":block/substrate")
		.displayName("Silicon Reagent")
		.renderType("cutout")
		.item(e => e.rarity(RARITY_EPIC).color(0, 0x474449).color(1, 0x967DA0))


	event.create(`substrate_silver`)
		.material('glass')
		.color(0, 0x9FADB4)
		.color(1, 0xBECCD2)
		.hardness(0.1)
		.box(.25, 0, .25, .75, 14.0 / 16.0, .75, false)
		.model(modpackId + ":block/substrate")
		.displayName("Silver Reagent")
		.renderType("cutout")
		.item(e => e.color(0, 0x9FADB4).color(1, 0xBECCD2))

	event.create(`accellerator_glowstone`)
		.material('glass')
		.color(0, 0xFFBC5E)
		.hardness(0.1)
		.box(.125, 0, .125, .875, 10.0 / 16.0, .875, false)
		.model(modpackId + ":block/accellerator")
		.displayName("Glowstone Accelerator")
		.renderType("cutout")
		.item(e => e.color(0, 0xFFBC5E))

	event.create(`accellerator_redstone`)
		.material('glass')
		.color(0, 0xAA0F01)
		.hardness(0.1)
		.box(.125, 0, .125, .875, 10.0 / 16.0, .875, false)
		.model(modpackId + ":block/accellerator")
		.displayName("Redstone Accelerator")
		.renderType("cutout")
		.item(e => e.color(0, 0xAA0F01))


})

onEvent('fluid.registry', event => {
	event.create('resin').thinTexture(0xaf7519)
	event.create('redstone').thinTexture(0x850b0e).noBlock()
	event.create('sky_stone').thinTexture(0x404344).noBlock()
	event.create('liquid_soul').thinTexture(0x604b3f).noBlock()
	event.create('slime').thinTexture(0x75db89).noBlock()
	event.create('waste').thinTexture(0x123d36).noBlock()
	event.create('powered_water').thinTexture(0x76d0f9)
	event.create('coke').thinTexture(0x323232).noBlock()

	event.create('molten_zinc').thickTexture(0xb0b29f)
	event.create('molten_tungsten').thickTexture(0x57594c)
	event.create('molten_brass').thickTexture(0xe0c36f)
	event.create('molten_diamond').thickTexture(0x4ee5ca)
	event.create('molten_enderium').thinTexture(0x185f74)
	event.create('molten_glass').thinTexture(0xcfe6e5)

	event.create('fine_sand').noBlock().thickTexture(0xded6a4)

	let colors = [0xCBE827, 0xAEE827, 0x68E827, 0x27E86E, 0x27E8B1, 0x27DEE8, 0x27B5E8, 0x2798E8, 0x2778E8, 0x2748E8]
	event.create('raw_logic').displayName(`Liquified Logic (Unprocessed)`).thinTexture(0xE7FFCB).noBlock()
	//for (i = 0; i < 10; i++) event.create('number_' + i).displayName(`Liquified Logic (${i})`).thinTexture(colors[i]).noBlock()
	event.create('matrix').displayName(`Liquified Computation Matrix`).thinTexture(colors[0]).noBlock()
})

onEvent('item.modification', event => {
	let colors = ["red", "yellow", "green", "blue", "magenta", "black"]
	colors.forEach(element => {
		event.modify('ae:' + element + '_paint_ball', item => {
			item.maxStackSize = 1
		})
	});
})
