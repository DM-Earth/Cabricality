let debug_extractor = false
onEvent("block.right_click", event => {
	let isNextToLog = false
	if (event.block.id == "kubejs:extractor_machine") {
		if (event.block.west.hasTag("minecraft:logs")) isNextToLog = true
		if (event.block.east.hasTag("minecraft:logs")) isNextToLog = true
		if (event.block.north.hasTag("minecraft:logs")) isNextToLog = true
		if (event.block.south.hasTag("minecraft:logs")) isNextToLog = true
		if (event.block.down.id != "minecraft:air" && event.block.down.id != "kubejs:resin") isNextToLog = false
		if (isNextToLog == true && Math.random() > 0.973) {
			let x = event.block.x
			let y = event.block.y - 1
			let z = event.block.z
			dimensional_commanding(event.server, event.block.dimension, "setblock " + x + " " + y + " " + z + " kubejs:resin")
		}

		//	Smelter
		let smeltSpeed = 1.0
		let downBlock = event.block.down.id
		let smeltChance = 1.0
		if (event.block.up.id == "minecraft:lava") {
			if (event.block.west.id == "minecraft:lava") smeltSpeed = smeltSpeed + 0.25
			if (event.block.north.id == "minecraft:lava") smeltSpeed = smeltSpeed + 0.25
			if (event.block.east.id == "minecraft:lava") smeltSpeed = smeltSpeed + 0.25
			if (event.block.south.id == "minecraft:lava") smeltSpeed = smeltSpeed + 0.25
			if (event.block.west.id == "minecraft:water") smeltSpeed = smeltSpeed - 0.15
			if (event.block.north.id == "minecraft:water") smeltSpeed = smeltSpeed - 0.15
			if (event.block.east.id == "minecraft:water") smeltSpeed = smeltSpeed - 0.15
			if (event.block.south.id == "minecraft:water") smeltSpeed = smeltSpeed - 0.15

			//	Vanilla metal
			if (event.block.down.id == "minecraft:iron_ore") {
				downBlock = "tconstruct:molten_iron_fluid"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "minecraft:deepslate_iron_ore") {
				downBlock = "tconstruct:molten_iron_fluid"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "minecraft:iron_block" || event.block.down.id == "minecraft:raw_iron_block") {
				downBlock = "tconstruct:molten_iron_fluid"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "minecraft:gold_ore") {
				downBlock = "tconstruct:molten_gold_fluid"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "minecraft:deepslate_gold_ore") {
				downBlock = "tconstruct:molten_gold_fluid"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "minecraft:gold_block" || event.block.down.id == "minecraft:raw_gold_block") {
				downBlock = "tconstruct:molten_gold_fluid"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "minecraft:copper_ore") {
				downBlock = "tconstruct:molten_copper_fluid"
				smeltChance = 0.25
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "minecraft:deepslate_copper_ore") {
				downBlock = "tconstruct:molten_copper_fluid"
				smeltChance = 0.5
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "minecraft:copper_block" || event.block.down.id == "minecraft:raw_copper_block") {
				downBlock = "tconstruct:molten_copper_fluid"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "create:zinc_ore") {
				downBlock = "tconstruct:molten_zinc_fluid"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "create:deepslate_zinc_ore") {
				downBlock = "tconstruct:molten_zinc_fluid"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "create:zinc_block" || event.block.down.id == "create:raw_zinc_block") {
				downBlock = "tconstruct:molten_zinc_fluid"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			//	Indrev
			if (event.block.down.id == "indrev:tungsten_ore") {
				downBlock = "tconstruct:molten_tungsten_fluid"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "indrev:deepslate_tungsten_ore") {
				downBlock = "tconstruct:molten_tungsten_fluid"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:tungsten_block" || event.block.down.id == "indrev:raw_tungsten_block") {
				downBlock = "tconstruct:molten_tungsten_fluid"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "indrev:tin_ore") {
				downBlock = "tconstruct:molten_tin_fluid"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "indrev:deepslate_tin_ore") {
				downBlock = "tconstruct:molten_tin_fluid"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:tin_block" || event.block.down.id == "indrev:raw_tin_block") {
				downBlock = "tconstruct:molten_tin_fluid"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "indrev:silver_ore") {
				downBlock = "tconstruct:molten_silver_fluid"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "indrev:deepslate_silver_ore") {
				downBlock = "tconstruct:molten_silver_fluid"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:silver_block" || event.block.down.id == "indrev:raw_silver_block") {
				downBlock = "tconstruct:molten_silver_fluid"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "indrev:lead_ore") {
				downBlock = "tconstruct:molten_lead_fluid"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "indrev:deepslate_lead_ore") {
				downBlock = "tconstruct:molten_lead_fluid"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:lead_block" || event.block.down.id == "indrev:raw_lead_block") {
				downBlock = "tconstruct:molten_lead_fluid"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "minecraft:redstone_block") {
				downBlock = "kubejs:redstone"
				smeltChance = 1.0
				smeltSpeed = 1.0
			}
			if (event.block.down.id == "create:brass_block") {
				downBlock = "tconstruct:molten_brass_fluid"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}
		}

		if (event.block.up.id == "minecraft:water") {
			if (event.block.west.id == "minecraft:lava") smeltSpeed = smeltSpeed - 0.25
			if (event.block.north.id == "minecraft:lava") smeltSpeed = smeltSpeed - 0.25
			if (event.block.east.id == "minecraft:lava") smeltSpeed = smeltSpeed - 0.25
			if (event.block.south.id == "minecraft:lava") smeltSpeed = smeltSpeed - 0.25
			if (event.block.west.id == "minecraft:water") smeltSpeed = smeltSpeed + 0.15
			if (event.block.north.id == "minecraft:water") smeltSpeed = smeltSpeed + 0.15
			if (event.block.east.id == "minecraft:water") smeltSpeed = smeltSpeed + 0.15
			if (event.block.south.id == "minecraft:water") smeltSpeed = smeltSpeed + 0.15

			if (event.block.down.id == "tconstruct:molten_iron") {
				downBlock = "minecraft:iron_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "tconstruct:molten_gold") {
				downBlock = "minecraft:gold_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "tconstruct:molten_copper") {
				downBlock = "minecraft:copper_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "tconstruct:molten_zinc") {
				downBlock = "create:zinc_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "tconstruct:molten_tungsten") {
				downBlock = "indrev:tungsten_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "tconstruct:molten_tin") {
				downBlock = "indrev:tin_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "tconstruct:molten_lead") {
				downBlock = "indrev:lead_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "tconstruct:molten_silver") {
				downBlock = "indrev:silver_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}

			if (event.block.down.id == "tconstruct:molten_brass") {
				downBlock = "create:brass_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
		}

		let isFluidSource = false
		if (debug_extractor == true) {
			event.server.runCommandSilent("say start")
			event.server.runCommandSilent("say " + event.block.down.getBlockState().toString())
			event.server.runCommandSilent("say " + event.block.down.getBlockState().toString().indexOf("[level=").toString())
			event.server.runCommandSilent("say " + event.block.down.getBlockState().toString().indexOf("[level=0]").toString())
			event.server.runCommandSilent("say end")
		}
		if (!event.block.down.getBlockState().toString().includes("[level=") || event.block.down.getBlockState().toString().includes("[level=0]")) isFluidSource = true
		if (!event.block.up.getBlockState().toString().includes("[level=0]")) isFluidSource = false

		if (downBlock != event.block.down.id && Math.random() <= smeltSpeed

			/*		&& event.block.down.east.id != event.block.down.id &&
						event.block.down.west.id != event.block.down.id &&
						event.block.down.north.id != event.block.down.id &&
						event.block.down.south.id != event.block.down.id	*/

			&& isFluidSource == true
		) {
			//	if (debug_extractor == true) event.server.runCommandSilent("say " + event.block.down.getBlockState().toString())
			if (debug_extractor == true) event.server.runCommandSilent(`say ${event.block.down.getBlockState().toString()}`)
			let x = event.block.x
			let y = event.block.y - 1
			let yup = event.block.y + 1
			let z = event.block.z
			let downPos = x + " " + y + " " + z
			let upPos = x + " " + yup + " " + z

			smeltChance = smeltChance * 1.2
			if (Math.random() <= smeltChance) {
				//		event.server.runCommandSilent("setblock " + downPos + " " + downBlock)
				dimensional_commanding(event.server, event.block.dimension, `setblock ${downPos} ${downBlock}`)
			} else {
				//		event.server.runCommandSilent("setblock " + downPos + " " + "minecraft:air")
				dimensional_commanding(event.server, event.block.dimension, `setblock ${downPos} minecraft:air`)
			}
			let smeltSpeed2 = 1.05 - smeltSpeed
			//	if (Math.random() <= smeltSpeed2) event.server.runCommandSilent("setblock " + upPos + " minecraft:air")
			if (Math.random() <= smeltSpeed2) dimensional_commanding(event.server, event.block.dimension, `setblock ${upPos} minecraft:air`)
		}
	}
})