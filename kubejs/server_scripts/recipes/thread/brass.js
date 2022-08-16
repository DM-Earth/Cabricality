onEvent("recipes", event => {
  event.remove({ id: CR("crafting/materials/electron_tube") })
  event.remove({ id: CR("crafting/materials/rose_quartz") })
  let redstone = MC("redstone")
  event.shapeless("create:rose_quartz", [[MC("quartz"), AE2("certus_quartz_crystal"), AE2("charged_certus_quartz_crystal")], redstone, redstone, redstone, redstone])
  event.recipes.createMilling([AE2("certus_quartz_dust")], "#ae2:all_certus_quartz").processingTime(200)
  event.recipes.createMilling([AE2("fluix_dust")], "#ae2:all_fluix").processingTime(200)
  event.recipes.createMechanicalCrafting(Item.of(AE2("certus_crystal_seed"), 2), ["A"], { A: AE2("#all_certus_quartz") })
  event.recipes.createMechanicalCrafting(Item.of(AE2("fluix_crystal_seed"), 2), ["A"], { A: AE2("#all_fluix") })

  event.remove({ id: CR("sequenced_assembly/precision_mechanism") })
  event.shapeless(KJ("screwdriver"), [IV("screwdriver"), MC("iron_ingot"), MC("blue_dye")])

  /*	let transition = CR("incomplete_precision_mechanism")
    event.recipes.createSequencedAssembly([
      CR("precision_mechanism"),
    ], KJ("kinetic_mechanism"), [
      event.recipes.createDeploying(transition, [transition, CR("electron_tube")]),
      event.recipes.createDeploying(transition, [transition, CR("electron_tube")]),
      event.recipes.createDeploying(transition, [transition, "kubejs:screwdriver"])
    ]).transitionalItem(transition)
      .loops(1)
      .id("kubejs:precision_mechanism")	*/

  event.shaped(KJ("brass_machine"), [
    "SSS",
    "SCS",
    "SSS"
  ], {
    C: CR("brass_casing"),
    S: CR("precision_mechanism")
  })
  let brass_machine = (id, amount, other_ingredient) => {
    event.remove({ output: id })
    if (other_ingredient) {
      event.smithing(Item.of(id, amount), "kubejs:brass_machine", other_ingredient)
      event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: "kubejs:brass_machine", B: other_ingredient })
    }
    else
      event.stonecutting(Item.of(id, amount), "kubejs:brass_machine")
  }
  brass_machine("create:mechanical_crafter", 3, MC("crafting_table"))
  brass_machine("create:sequenced_gearshift", 2)
  brass_machine("create:furnace_engine", 1)
  brass_machine("create:rotation_speed_controller", 1)
  brass_machine("create:mechanical_arm", 1)
  brass_machine("create:stockpile_switch", 2)
  brass_machine("create:content_observer", 2)
  brass_machine("indrev:solid_infuser_mk1", 1, MC("dropper"))
  brass_machine("indrev:biomass_generator_mk3", 1, IV("heat_coil"))
  brass_machine("create:brass_funnel", 4)
  brass_machine("create:brass_tunnel", 4)

  event.recipes.createMilling([AE2("sky_dust"), AE2("sky_stone_block")], AE2("sky_stone_block")).processingTime(1000)
})