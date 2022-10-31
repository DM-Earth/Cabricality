onEvent("item.registry", event => {
	//	Mechanism
	let registerMechanism = (name, localName, rarity) => {
		let id = name.toLowerCase() + "_mechanism"
		let incompleteId = "incomplete_" + id

		event.create(asIdentifier(id))
			.texture(asIdentifier("/item/mechanism/" + id))
			.displayName(localName + " Mechanism")
			.rarity(rarity ? rarity : RARITY_COMMON)

		event.create(asIdentifier(incompleteId))
			.texture(asIdentifier("/item/mechanism/incomplete/" + incompleteId))
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

		event.create(asIdentifier(id))
			.texture(asIdentifier("/item/machine_part/" + id))
			.displayName(localName)
	}
	let initMachineParts = () => {
		registerMachinePart("Saw Blade", "Saw Blade")
	}

	//	Tools
	let registerSaw = (materialName, materialId, durability) => {
		let id = materialId + "_saw"

		event.create(asIdentifier(id))
			.texture(asIdentifier("item/tool/saw/" + id))
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
		event.create(asIdentifier(id))
			.texture(asIdentifier("item/" + id))
			.displayName(localName)
	}
	let initTypicalItems = () => {
		registerTypicalItem("Stone Rod", "Stone Rod")
		registerTypicalItem("Rubber", "Rubber")
		registerTypicalItem("Cured Rubber", "Rubber")

		event.create(asIdentifier("screwdriver"))
			.texture(asIdentifier("item/screwdriver"))
			.displayName("Reinforced Screwdriver")
			.maxDamage(512)

		registerTypicalItem("Invar Ingot", "Invar Ingot")
		registerTypicalItem("Nickel Ingot", "Nickel Ingot")
		registerTypicalItem("Nickel Nugget", "Nickel Nugget")
		registerTypicalItem("Enderium Ingot", "Enderium Ingot")
		registerTypicalItem("Nickel Compound", "Nickel Compound")
		registerTypicalItem("Invar Compound", "Invar Compound")
		registerTypicalItem("Silicon Compound", "Silicon Compound")

		registerTypicalItem("Ruby", "Ruby")
		registerTypicalItem("Sapphire", "Sapphire")

		registerTypicalItem("Gold Coin", "§6Gold Coin")
		registerTypicalItem("Silver Coin", "§7Silver Coin")

		registerTypicalItem("Sand Ball", "Sand Ball")
		registerTypicalItem("Coke Chunk", "Coke Chunk")
		registerTypicalItem("Rough Sand", "Rough Sand")
		registerTypicalItem("Purified Sand", "Purified Sand")

		registerTypicalItem("Nickel Dust", "Nickel Dust")
		registerTypicalItem("Cobalt Dust", "Cobalt Dust")
		registerTypicalItem("Desh Dust", "Desh Dust")
		registerTypicalItem("Ostrum Dust", "Ostrum Dust")
		registerTypicalItem("Calorite Dust", "Calorite Dust")
		registerTypicalItem("Emerald Dust", "Emerald Dust")
		registerTypicalItem("Diamond Dust", "Diamond Dust")

		registerTypicalItem("Basalz Shard", "Basalz Shard")
		registerTypicalItem("Basalz Powder", "Basalz Powder")
		registerTypicalItem("Blizz Cube", "Blizz Cube")
		registerTypicalItem("Blizz Powder", "Blizz Powder")

		registerTypicalItem("Ice Charge", "Ice Charge")
		registerTypicalItem("Earth Charge", "Earth Charge")
		registerTypicalItem("Lightning Charge", "Lightning Charge")

		registerTypicalItem("Circuit Scrap", "Circuit Scrap")
		registerTypicalItem("Zinc Sheet", "Zinc Sheet")
		registerTypicalItem("Matter Plastics", "Matter Plastics")

		event.create(asIdentifier("coal_coke"))
			.texture(asIdentifier("item/coal_coke"))
			.displayName("Coke")
			.burnTime(3200)

		event.create(asIdentifier("incomplete_coke_chunk"))
			.texture(asIdentifier("item/incomplete_coke_chunk"))
			.displayName("Incomplete Coke Chunk")

		event.create(asIdentifier("radiant_sheet"))
			.texture(asIdentifier("item/radiant_sheet"))
			.displayName("Radiant Sheet")
			.glow(true)

		event.create(asIdentifier("radiant_coil"))
			.texture(asIdentifier("item/radiant_coil"))
			.displayName("Radiant Coil")
			.glow(true)

		event.create(asIdentifier("chromatic_resonator"))
			.texture(asIdentifier("item/chromatic_resonator"))
			.displayName("Chromatic Resonator")
			.maxDamage(512)

		event.create(asIdentifier("dye_entangled_singularity"))
			.texture(asIdentifier("item/dye_entangled_singularity"))
			.unstackable()
			.displayName("Dyed Entangled Singularity")

		event.create(asIdentifier("flash_drive"))
			.texture(asIdentifier("item/boot_medium"))
			.displayName("Flash Drive")
			.maxDamage(512)

		event.create(asIdentifier("crushed_cobalt_ore"))
			.texture(asIdentifier("item/crushed/crushed_cobalt_ore"))
			.displayName("Crushed Cobalt Ore")

		event.create(asIdentifier("crushed_desh_ore"))
			.texture(asIdentifier("item/crushed/crushed_desh_ore"))
			.displayName("Crushed Desh Ore")

		event.create(asIdentifier("crushed_ostrum_ore"))
			.texture(asIdentifier("item/crushed/crushed_ostrum_ore"))
			.displayName("Crushed Ostrum Ore")

		event.create(asIdentifier("crushed_calorite_ore"))
			.texture(asIdentifier("item/crushed/crushed_calorite_ore"))
			.displayName("Crushed Calorite Ore")

		let processors = ["Calculation", "Logic", "Engineering"]
		let processorsLocalName = ["Calculation", "Logic", "Engineering"]
		processors.forEach(name => {
			let id = name.toLowerCase()
			let localName = processorsLocalName[processors.indexOf(name)]
			event.create(asIdentifier("incomplete_" + id + "_processor"))
				.texture(asIdentifier("item/processor/incomplete_" + id + "_processor"))
				.displayName("Incomplete " + localName + " Processor")
		})
	}

	let initSlimeFerns = () => {
		let slime = (id, color) => {
			id = id.toLowerCase()
			event.create(asIdentifier(id + "_slime_fern_leaf"))
				.color(0, color)
				.texture(asIdentifier("item/fern/slime_fern_leaf"))
				.displayName("Slimy Fern Leaf")

			event.create(asIdentifier(id + "_slime_fern_paste"))
				.color(0, color)
				.texture(asIdentifier("item/fern/slime_fern_paste"))
				.displayName("Slimy Fern Paste")
		}
		slime("earth", 0x8FDB84)
		slime("sky", 0x00F9DE)
		slime("ender", 0xAC2EFC)
	}

	//	Final init
	let initItems = () => {
		initMechanisms()
		initMachineParts()
		initToolMaterials()
		initTypicalItems()
		initSlimeFerns()
	}
	initItems()
	let number = (name, localName) => {
		let id = name.toLowerCase()
		event.create(asIdentifier(id))
			.texture(asIdentifier("item/number/" + id))
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
	number("Missingno", "א")

	/*
		event.create(asIdentifier("number_array")
			.texture(asIdentifier("item/number/number_array")
			.displayName("Number Array")
			.glow(true)
	*/

	event.create(asIdentifier("three_cast")).texture(asIdentifier("item/cast/three_cast")).displayName("Integer Cast (3)").unstackable()
	event.create(asIdentifier("eight_cast")).texture(asIdentifier("item/cast/eight_cast")).displayName("Integer Cast (8)").unstackable()
	event.create(asIdentifier("plus_cast")).texture(asIdentifier("item/cast/plus_cast")).displayName("Operator Cast (+)").unstackable()
	event.create(asIdentifier("minus_cast")).texture(asIdentifier("item/cast/minus_cast")).displayName("Operator Cast (-)").unstackable()
	event.create(asIdentifier("multiply_cast")).texture(asIdentifier("item/cast/multiply_cast")).displayName("Operator Cast (×)").unstackable()
	event.create(asIdentifier("divide_cast")).texture(asIdentifier("item/cast/divide_cast")).displayName("Operator Cast (÷)").unstackable()

	event.create(asIdentifier("silver_coin_top")).displayName("§f§l▲").unstackable().parentModel(asIdentifier("item/coin/silver_coin_top")).group("search")
	event.create(asIdentifier("silver_coin_bottom")).displayName("§7§l▼").unstackable().parentModel(asIdentifier("item/coin/silver_coin_bottom")).group("search")
	event.create(asIdentifier("gold_coin_top")).displayName("§e§l▲").unstackable().parentModel(asIdentifier("item/coin/gold_coin_top")).group("search")
	event.create(asIdentifier("gold_coin_bottom")).displayName("§6§l▼").unstackable().parentModel(asIdentifier("item/coin/gold_coin_bottom")).group("search")

	let dice = [1, 2, 3, 4, 5, 6]

	dice.forEach(serial => {
		event.create(asIdentifier(`dice_${serial}`)).displayName(`§5§l${serial}`).unstackable().parentModel(asIdentifier("item/dice/" + serial)).group("search")
	})
})

onEvent("block.registry", event => {
	//	Machine
	let registerMachine = (name, localName, layer) => {
		let id = name.toLowerCase() + "_machine"
		event.create(asIdentifier(id))
			.model(asIdentifier("block/machine/" + id))
			.material("lantern")
			.hardness(3.0)
			.displayName(localName + " Machine")
			.notSolid()
			.renderType(layer)
			.tagBlock("create:wrench_pickup")
			.tagBlock("minecraft:mineable/pickaxe")
	}
	registerMachine("Andesite", "Andesite", "solid")
	registerMachine("Brass", "Brass", "translucent")
	registerMachine("Copper", "Copper", "cutout")
	registerMachine("Zinc", "Zinc", "cutout")
	registerMachine("Enderium", "Enderium", "cutout")
	registerMachine("Obsidian", "Obsidian", "translucent")

	let registerCasing = (name, localName) => {
		let id = name.toLowerCase() + "_casing"
		event.create(asIdentifier(id))
			.model(asIdentifier("block/casing/" + id))
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

	event.create(asIdentifier("computation_matrix"))
		.model(asIdentifier("block/computation_matrix"))
		.material("metal")
		.hardness(3.0)
		.displayName("Computation Matrix")
		.tagBlock("minecraft:mineable/pickaxe")

	event.create(asIdentifier("dice"))
		.model(asIdentifier("block/dice"))
		.material("stone")
		.hardness(2.0)
		.displayName("§5Dice")
		.tagBlock("minecraft:mineable/pickaxe")
})

onEvent("fluid.registry", event => {
	event.create(asIdentifier("sky_stone"))
		.displayName("Sky Stone")
		.thinTexture(0x404344)
		.noBlock()

	event.create(asIdentifier("waste"))
		.displayName("Waste")
		.thinTexture(0x123d36)
		.noBlock()

	event.create(asIdentifier("powered_water"))
		.displayName("Powered Water")
		.thinTexture(0x76d0f9)

	event.create(asIdentifier("coke"))
		.displayName("Coke")
		.thinTexture(0x323232)
		.noBlock()

	event.create(asIdentifier("fine_sand"))
		.displayName("Fine Sand")
		.thickTexture(0xded6a4)
		.noBlock()

	let colors = [0xCBE827, 0xAEE827, 0x68E827, 0x27E86E, 0x27E8B1, 0x27DEE8, 0x27B5E8, 0x2798E8, 0x2778E8, 0x2748E8]
	event.create(asIdentifier("raw_logic"))
		.displayName("Liquefied Logic (Unprocessed)")
		.thinTexture(0xE7FFCB)
		.noBlock()

	for (let i = 0; i < 10; i++) {
		event.create(asIdentifier("number_" + i))
			.displayName("Liquefied Computation (" + i + ")")
			.thinTexture(colors[i])
			.noBlock()
			.noBucket()
	}
	event.create(asIdentifier("matrix"))
		.displayName("Liquefied Computation Matrix")
		.thinTexture(colors[0])
		.noBlock()

	event.create(asIdentifier("molten_desh"))
		.displayName("Molten Desh")
		.thickTexture(0xc88448)

	event.create(asIdentifier("molten_ostrum"))
		.displayName("Molten Ostrum")
		.thickTexture(0x6c4c59)

	event.create(asIdentifier("molten_calorite"))
		.displayName("Molten Calorite")
		.thickTexture(0x931d3b)
})