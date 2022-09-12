onEvent("recipes", event => {
  event.remove({ id: CR("crafting/kinetics/belt_connector") })
	event.shaped(CR("belt_connector", 3), [
		"SSS",
		"SSS"
	], {
		S: CABF("cured_rubber")
	})
	event.smelting(asIdentifier("cured_rubber"), asIdentifier("rubber"))
	event.shaped(CABF("sealed_mechanism"), [
		"SCS"
	], {
		C: CABF("kinetic_mechanism"),
		S: CABF("cured_rubber")
	})
	event.shaped(CABF("copper_machine"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: CR("copper_casing"),
		S: CABF("sealed_mechanism")
	})

	let copper_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), asIdentifier("copper_machine"), other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: asIdentifier("copper_machine"), B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), asIdentifier("copper_machine"))
	}
	copper_machine("create:copper_backtank", 1, MC("copper_block"))
	copper_machine("create:portable_fluid_interface", 2)
	copper_machine("create:spout", 1, KB("fluid_hopper"))
	copper_machine("indrev:tier_upgrade_mk2", 1, MC("redstone"))
	copper_machine("create:hose_pulley", 1)
	copper_machine("create:item_drain", 1, MC("iron_bars"))
	copper_machine("indrev:heat_generator_mk4", 1, IV("heat_coil"))
	copper_machine("indrev:fisher_mk2", 1, MC("bucket"))
	copper_machine("create:steam_engine", 2, MC("piston"))
	copper_machine("create:smart_fluid_pipe", 2)
	copper_machine(CX("fluid_trash_can"), 1, KB("trash_can"))
})