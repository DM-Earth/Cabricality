let outputItem = ""
let length = 16
let debug_laser = false

function rnd(seed) {
	let seedO = (seed * 9301 + 49297) % 233280 //toxic, don't touch it
	return seedO / (233280.0)
}

function rand(number, seed) {
	return Math.ceil(rnd(seed) * number)
};

function select(input, seed) {
	let selectTemp = input
	let selected = []
	for (i = 0; i < 4; i++) {
		num = rand(selectTemp.length - 1, seed + i)
		selected.push(selectTemp[num])
		selectTemp.splice(num, 1)
	}
	return selected
}

function itemRecipes(inputBlock) {
	if (inputBlock == "minecraft:basalt") { return "kubejs:basalz_shard" }
	if (inputBlock == "minecraft:snow_block") { return "kubejs:blizz_cube" }
	return ""
}
function blockRecipes(inputBlock) {
	if (inputBlock == "minecraft:water") { return "kubejs:powered_water" }
	return ""
}
function chaosT(subs, seed, event) {
	if (debug_laser == true) event.server.runCommandSilent("say " + subs + " seed " + seed)
	let igneous = ["andesite", "diorite", "granite", "cobblestone", "basalt", "limestone"]
	let herbal = ["red", "orange", "yellow", "green", "blue", "magenta"]
	let volatile = ["blaze", "slime", "nether", "obsidian", "gunpowder", "prismarine"]
	let metal = ["zinc", "copper", "iron", "lead", "gold"]
	let crystal = ["sulfur", "certus"]
	let gem = ["lapis", "emerald", "diamond", "ruby", "sapphire"]
	let chaos = ["igneous", "herbal", "volatile", "crystal", "metal", "gem"]
	let allSubstrate = ["andesite", "diorite", "granite", "cobblestone", "basalt", "limestone", "red", "orange", "yellow", "green", "blue", "magenta", "blaze", "slime", "nether", "obsidian", "gunpowder", "prismarine", "zinc", "copper", "iron", "lead", "gold", "sulfur", "certus", "lapis", "emerald", "diamond", "ruby", "sapphire"]

	if (debug_laser == true) event.server.runCommandSilent("say silicon: " + allSubstrate[rand(allSubstrate.length, seed + 114514)] + " silver: " + allSubstrate[rand(allSubstrate.length, seed + 1919810)])

	if (subs == select(chaos, seed).sort().toString()) return "chaos"
	if (subs == select(igneous, seed).sort().toString()) return "igneous"
	if (subs == select(herbal, seed).sort().toString()) return "herbal"
	if (subs == select(volatile, seed).sort().toString()) return "volatile"
	if (subs == select(metal, seed).sort().toString()) return "metal"
	if (subs == select(gem, seed).sort().toString()) return "gem"
	if (subs == ["sulfur", "certus"].sort().toString()) return "crystal"



	if (subs == ["chaos", allSubstrate[rand(allSubstrate.length, seed + 114514)]].sort().toString()) return "silicon"
	if (subs == ["chaos", allSubstrate[rand(allSubstrate.length, seed + 1919810)]].sort().toString()) return "silver"

	return ""
}

onEvent("block.left_click", event => {
	let seedL = Math.abs(event.getEntity().getServer().getOverworld().getSeed())
	if (event.block.id == "kibe:fluid_hopper" && event.block.down.id == "indrev:laser_emitter_mk4" && event.block.up.id == "kubejs:powered_water" && event.block.up.getBlockState().toString().includes("[level=0]")) {
		let block = event.block
		x = block.x
		y = block.y
		z = block.z
		yUp = y + 1
		yDown = y - 1

		let stringDirection = block.down.properties.facing
		let facing = ""

		if (stringDirection == "north") facing = Direction.NORTH
		if (stringDirection == "south") facing = Direction.SOUTH
		if (stringDirection == "east") facing = Direction.EAST
		if (stringDirection == "west") facing = Direction.WEST
		if (stringDirection == "up") facing = Direction.UP
		if (stringDirection == "down") facing = Direction.DOWN

		let substrateList = []

		for (let i = 1; i <= length; i++) {
			if (i < 6) continue
			let targetBlock = block.down.offset(facing, i)
			let blockX = targetBlock.x
			let blockY = targetBlock.y
			let blockZ = targetBlock.z

			if (targetBlock.id.startsWith("kubejs:substrate_")) {
				//		if (debug_laser == true) event.server.runCommandSilent("say " + targetBlock.id.replace("kubejs:substrate_", ""))
				if (debug_laser == true) event.server.runCommandSilent(`say ${targetBlock.id.replace("kubejs:substrate_", "")}`)
				substrateList.push(targetBlock.id.replace("kubejs:substrate_", ""))
				//		event.server.runCommandSilent("setblock " + blockX + " " + blockY + " " + blockZ + " minecraft:air")
				event.server.runCommandSilent(`setblock ${blockX} ${blockY} ${blockZ} minecraft:air`)
				//		if (targetBlock.id == "kubejs:substrate_chaos") event.server.runCommandSilent("summon minecraft:item " + x + " " + yUp + " " + z + " {Health:32767, Item:{id:\"kubejs:substrate_chaos\",Count:1b}}")
				if (targetBlock.id == "kubejs:substrate_chaos") event.server.runCommandSilent(`summon minecraft:item ${x} ${yUp} ${z} {Health:32767, Item:{id:"kubejs:substrate_chaos",Count:1b}}`)
			}

			let outputItem = itemRecipes(targetBlock.id)
			let outputBlock = blockRecipes(targetBlock.id)
			if (outputItem != "") {
				//		event.server.runCommandSilent("setblock " + blockX + " " + blockY + " " + blockZ + " minecraft:air")
				event.server.runCommandSilent(`setblock ${blockX} ${blockY} ${blockZ} minecraft:air`)
				//		event.server.runCommandSilent("summon minecraft:item " + blockX + " " + blockY + " " + blockZ + " {Health:32767, Item:{id:\"" + outputItem + "\",Count:1b}}")
				event.server.runCommandSilent(`summon minecraft:item ${blockX} ${blockY} ${blockZ} {Health:32767, Item:{id:"${outputItem}",Count:1b}}`)
			}
			if (outputBlock != "") {
				//		event.server.runCommandSilent("setblock " + blockX + " " + blockY + " " + blockZ + " " + outputBlock)
				event.server.runCommandSilent(`setblock ${blockX} ${blockY} ${blockZ} ${outputBlock}`)
			}
		}
		if (debug_laser == true) event.server.runCommandSilent("say " + substrateList.toString() + " seed " + seedL)
		// if (debug_laser == true) event.server.runCommandSilent(`say ${substrateList.toString} seed ${seedL}`)

		let unifiedSubstrates = substrateList.sort().toString()
		let outputChaos = chaosT(unifiedSubstrates, seedL, event)
		if (outputChaos != "") {
			//		event.server.runCommandSilent("summon minecraft:item " + x + " " + yUp + " " + z + " {Health:32767, Item:{id:\"kubejs:substrate_" + outputChaos + "\",Count:1b}}")
			event.server.runCommandSilent(`summon minecraft:item ${x} ${yUp} ${z} {Health:32767, Item:{id:"kubejs:substrate_${outputChaos}",Count:1b}}`)
		}

		for (let i = length; i >= 1; i = i - 1) {
			let targetBlock = block.down.offset(facing, i)
			let explosion = targetBlock.createExplosion();
			if (i >= 6) {
				explosion = explosion.strength(3.2)
			} else {
				explosion = explosion.strength(0.1)
			}
			explosion = explosion.causesFire(false)
			explosion.explode()
		}
		if (Math.random() > 0.5) {
			//	event.server.runCommandSilent("setblock " + x + " " + yUp + " " + z + " minecraft:water")
			event.server.runCommandSilent(`setblock ${x} ${yUp} ${z} minecraft:water`)
		}
	}
})