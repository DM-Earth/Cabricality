onEvent("recipes", event => {
  event.custom(ifiniDeploying(AE2("printed_silicon"), AE2("silicon"), AE2("silicon_press")))

	event.shaped(KJ("flash_drive"), [
		"SCA"
	], {
		A: TC("cobalt_ingot"),
		C: AE2("logic_processor"),
		S: MC("iron_ingot")
	})

	/*
	let t = KJ("incomplete_calculation_mechanism")
		event.recipes.createSequencedAssembly([
			KJ("calculation_mechanism"),
		], KJ("inductive_mechanism"), [
			event.recipes.createDeploying(t, [t, AE2("printed_silicon")]),
			event.recipes.createDeploying(t, [t, AE2("printed_silicon")]),
			event.recipes.createDeploying(t, [t, KJ("flash_drive")])
		]).transitionalItem(t)
			.loops(1)
			.id("kubejs:calculation_mechanism")
	*/

	event.remove({ output: AE2("controller") })
	event.shaped(AE2("controller"), [
		"SSS",
		"SCS",
		"SSS"
	], {
		C: KJ("fluix_casing"),
		S: KJ("calculation_mechanism")
	})

	let fluix_machine = (id, amount, other_ingredient) => {
		event.remove({ output: id })
		if (other_ingredient) {
			event.smithing(Item.of(id, amount), AE2("controller"), other_ingredient)
			event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: AE2("controller"), B: other_ingredient })
		}
		else
			event.stonecutting(Item.of(id, amount), AE2("controller"))
	}
	fluix_machine(AE2("condenser"), 1, AE2("fluix_pearl"))
	fluix_machine(AE2("drive"), 1, AE2("engineering_processor"))
	fluix_machine(AE2("formation_core"), 4, AE2("logic_processor"))
	fluix_machine(AE2("annihilation_core"), 4, AE2("calculation_processor"))
	fluix_machine(AE2("chest"), 1, MC("chest"))

	event.replaceInput({ id: AE2("network/cells/item_storage_components_cell_1k_part") }, C("#redstone_dusts"), KJ("calculation_mechanism"))
	event.replaceInput({ id: AE2("network/cells/item_storage_components_cell_1k_part") }, AE2("logic_processor"), MC("redstone"))
	event.replaceInput({ id: AE2("network/cells/spatial_components") }, C("#glowstone_dusts"), KJ("calculation_mechanism"))
	event.replaceInput({ id: AE2("network/cells/spatial_components") }, AE2("engineering_processor"), MC("glowstone_dust"))
	event.replaceInput({ id: AE2("network/crafting/patterns_blank") }, C("#glowstone_dusts"), KJ("calculation_mechanism"))
})