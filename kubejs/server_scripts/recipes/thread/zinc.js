onEvent("recipes", event => {
  event.shaped(KJ("zinc_machine"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: KJ("zinc_casing"),
		S: KJ("infernal_mechanism")
	})
	let zinc_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), "kubejs:zinc_machine", other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: "kubejs:zinc_machine", B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), "kubejs:zinc_machine")
	}
	zinc_machine(KB("cobblestone_generator_mk1"), 1, MC("piston"))
	zinc_machine(KB("basalt_generator_mk1"), 1, MC("blue_ice"))
	zinc_machine(KB("trash_can"), 1, MC("lava_bucket"))
	zinc_machine(KB("vacuum_hopper"), 1, CR("nozzle"))
	zinc_machine(KB("big_torch"), 1, MC("torch"))
	zinc_machine(AR("solar_panel"), 1, MC("glass"))
	zinc_machine("indrev:tier_upgrade_mk3", 1, MC("redstone"))
	zinc_machine(ED("controller"), 1, ED("connector"))
})