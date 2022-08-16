onEvent("recipes", event => {
  let upgrade = (input, output, outputTier) => {
		event.remove({ output: output })
		if (outputTier) {
			event.smithing(Item.of(output, 1), input, "indrev:tier_upgrade_mk" + outputTier)
			event.recipes.createMechanicalCrafting(Item.of(output, 1), "AB", { A: input, B: "indrev:tier_upgrade_mk" + outputTier })
		}
	}
	upgrade(IV("solar_generator_mk1"), IV("solar_generator_mk3"), 2)

  upgrade(ED("upgrade_frame"), ED("t1_upgrade"), 2)
  upgrade(ED("t1_upgrade"), ED("t2_upgrade"), 3)
  upgrade(ED("t2_upgrade"), ED("t3_upgrade"), 4)

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
  upgradeAll(CX("portable_tank_mkTL"), 1, 4, 5)
})