onEvent("recipes", event => {
  event.recipes.createCompacting(KJ("matter_plastics"), [AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball"), AE2("matter_ball")]).superheated()
	let gear = KJ("sturdy_mechanism")
	let plastic = KJ("matter_plastics")
	let machine = AE2("controller")
	let matrix = KJ("computation_matrix")
	event.remove({ output: IV("controller") })
	event.recipes.createMechanicalCrafting(IV("controller"), [
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
  smithAndMechCraft(AR("rocket_launch_pad"), IV("tin_plate"), plastic)
	smithAndMechCraft(AR("cryo_freezer"), IV("condenser_mk4"), plastic)
	smithAndMechCraft(AR("oxygen_sensor"), MC("observer"), plastic)
	smithAndMechCraft(AR("fuel_refinery"), IV("smelter_mk4"), plastic)
	let astra_materials = [
		"steel",
		"desh",
		"ostrum",
		"calorite",
		"iron"
	]
	let decor_types = [
		"pillar",
		"plating"
	]
	astra_materials.forEach(material => {
		event.remove({ output: AR(material + "_engine") })
		event.remove({ output: AR(material + "_tank") })
		decor_types.forEach(decor_type => {
			let block_id = AR(material + "_" + decor_type)
			event.remove({ output: block_id })
			event.stonecutting("2x " + block_id, C("#plates/" + material))
		})
	})
	event.remove({ type: AR("compressing") })
	event.remove({ type: AR("oxygen_conversion") })
	event.remove({ type: AR("nasa_workbench") })
	event.remove({ output: AR("oxygen_loader") })
	event.remove({ output: AR("oxygen_distributer") })
	event.remove({ output: AR("energizer") })
	event.remove({ output: AR("water_pump") })
	event.remove({ output: AR("nasa_workbench") })
	event.remove({ output: AR("compressor") })

	event.recipes.createPressing(AR("desh_plate"), AR("desh_ingot"))
	event.recipes.createPressing(AR("compressed_ostrum"), AR("ostrum_ingot"))
	event.recipes.createPressing(AR("compressed_calorite"), AR("calorite_ingot"))

	let rocket_tiers = [
		[1, "steel"],
		[2, "desh"],
		[3, "ostrum"],
		[4, "calorite"]
	]
	rocket_tiers.forEach(tier => {
		let engine = AR(tier[1] + "_engine")
		let tank = AR(tier[1] + "_tank")
		let block = AR(tier[1] + "_block")
		event.recipes.createMechanicalCrafting(Item.of('ad_astra:tier_' + tier[0] + '_rocket', '{amount:243000L,fluid:{fluid:"ad_astra:fuel"}}'), [
			"   R   ",
			"  SBS  ",
			"  SBS  ",
			"  BCB  ",
			"  SBS  ",
			" FSBSF ",
			"FTBTBTF",
			"FEEEEEF"
		], {
			R: AR("rocket_nose_cone"),
			S: [AR("steel_pillar"), AR("steel_pillar")],
			B: block,
			T: tank,
			E: engine,
			F: AR("rocket_fin"),
			C: IV("controller")
		})
	})
	let ore_map = [
		["ad_astra:raw_desh", "kubejs:crushed_desh_ore"],
		["ad_astra:raw_ostrum", "kubejs:crushed_ostrum_ore"],
		["ad_astra:raw_calorite", "kubejs:crushed_calorite_ore"]
	]
	ore_map.forEach(mapped => {
		event.replaceOutput({type: CR("crushing")}, mapped[0], mapped[1])
	})
})