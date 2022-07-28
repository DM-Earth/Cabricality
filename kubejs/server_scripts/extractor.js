let debug = false
onEvent("block.right_click", event => {
	var isNextToLog = false
	if (event.block.id == "kubejs:extractor_machine") {
		if (event.block.west.hasTag("minecraft:logs")) isNextToLog = true
		if (event.block.east.hasTag("minecraft:logs")) isNextToLog = true
		if (event.block.north.hasTag("minecraft:logs")) isNextToLog = true
		if (event.block.south.hasTag("minecraft:logs")) isNextToLog = true
		if (event.block.down.id != "minecraft:air" && event.block.down.id != "kubejs:resin") isNextToLog = false
		if (isNextToLog == true && Math.random() > 0.973) {
			x = event.block.x
			y = event.block.y - 1
			z = event.block.z
			event.server.runCommandSilent("setblock " + x + " " + y + " " + z + " kubejs:resin")
		}

		//	Smelter
		smeltSpeed = 1.0
		downBlock = event.block.down.id
		smeltChance = 1.0
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
				downBlock = "indrev:molten_iron"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "minecraft:deepslate_iron_ore") {
				downBlock = "indrev:molten_iron"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "minecraft:iron_block" || event.block.down.id == "minecraft:raw_iron_block") {
				downBlock = "indrev:molten_iron"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "minecraft:gold_ore") {
				downBlock = "indrev:molten_gold"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "minecraft:deepslate_gold_ore") {
				downBlock = "indrev:molten_gold"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "minecraft:gold_block" || event.block.down.id == "minecraft:raw_gold_block") {
				downBlock = "indrev:molten_gold"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "minecraft:copper_ore") {
				downBlock = "indrev:molten_copper"
				smeltChance = 0.25
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "minecraft:deepslate_copper_ore") {
				downBlock = "indrev:molten_copper"
				smeltChance = 0.5
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "minecraft:copper_block" || event.block.down.id == "minecraft:raw_copper_block") {
				downBlock = "indrev:molten_copper"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "create:zinc_ore") {
				downBlock = "kubejs:molten_zinc"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "create:deepslate_zinc_ore") {
				downBlock = "kubejs:molten_zinc"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "create:zinc_block" || event.block.down.id == "create:raw_zinc_block") {
				downBlock = "kubejs:molten_zinc"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			//	Indrev
			if (event.block.down.id == "indrev:tungsten_ore") {
				downBlock = "kubejs:molten_tungsten"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "indrev:deepslate_tungsten_ore") {
				downBlock = "kubejs:molten_tungsten"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:tungsten_block" || event.block.down.id == "indrev:raw_tungsten_block") {
				downBlock = "kubejs:molten_tungsten"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "indrev:tin_ore") {
				downBlock = "indrev:molten_tin"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "indrev:deepslate_tin_ore") {
				downBlock = "indrev:molten_tin"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:tin_block" || event.block.down.id == "indrev:raw_tin_block") {
				downBlock = "indrev:molten_tin"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "indrev:silver_ore") {
				downBlock = "indrev:molten_silver"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "indrev:deepslate_silver_ore") {
				downBlock = "indrev:molten_silver"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:silver_block" || event.block.down.id == "indrev:raw_silver_block") {
				downBlock = "indrev:molten_silver"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "indrev:lead_ore") {
				downBlock = "indrev:molten_lead"
				smeltChance = 0.125
				smeltSpeed = 0.85
			}
			if (event.block.down.id == "indrev:deepslate_lead_ore") {
				downBlock = "indrev:molten_lead"
				smeltChance = 0.25
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:lead_block" || event.block.down.id == "indrev:raw_lead_block") {
				downBlock = "indrev:molten_lead"
				smeltChance = 1.0
				smeltSpeed = 0.5
			}

			if (event.block.down.id == "minecraft:redstone_block") {
				downBlock = "kubejs:redstone"
				smeltChance = 1.0
				smeltSpeed = 1.0
			}
			if (event.block.down.id == "create:brass_block") {
				downBlock = "kubejs:molten_brass"
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

			if (event.block.down.id == "indrev:molten_iron") {
				downBlock = "minecraft:iron_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:molten_gold") {
				downBlock = "minecraft:gold_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:molten_copper") {
				downBlock = "minecraft:copper_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "kubejs:molten_zinc") {
				downBlock = "create:zinc_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "kubejs:molten_tungsten") {
				downBlock = "indrev:tungsten_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:molten_tin") {
				downBlock = "indrev:tin_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:molten_lead") {
				downBlock = "indrev:lead_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
			if (event.block.down.id == "indrev:molten_silver") {
				downBlock = "indrev:silver_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}

			if (event.block.down.id == "kubejs:molten_brass") {
				downBlock = "create:brass_block"
				smeltChance = 1.0
				smeltSpeed = 0.75
			}
		}

		let isFluidSource = false
		if (debug == true) {
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
			//	if (debug == true) event.server.runCommandSilent("say " + event.block.down.getBlockState().toString())
			if (debug == true) event.server.runCommandSilent(`say ${event.block.down.getBlockState().toString()}`)
			x = event.block.x
			y = event.block.y - 1
			yup = event.block.y + 1
			z = event.block.z
			downPos = x + " " + y + " " + z
			upPos = x + " " + yup + " " + z

			smeltChance = smeltChance * 1.2
			if (Math.random() <= smeltChance) {
				//		event.server.runCommandSilent("setblock " + downPos + " " + downBlock)
				event.server.runCommandSilent(`setblock ${downPos} ${downBlock}`)
			} else {
				//		event.server.runCommandSilent("setblock " + downPos + " " + "minecraft:air")
				event.server.runCommandSilent(`setblock ${downPos} minecraft:air`)
			}
			smeltSpeed2 = 1.05 - smeltSpeed
			//	if (Math.random() <= smeltSpeed2) event.server.runCommandSilent("setblock " + upPos + " minecraft:air")
			if (Math.random() <= smeltSpeed2) event.server.runCommandSilent(`setblock ${upPos} minecraft:air`)
		}
	}
})