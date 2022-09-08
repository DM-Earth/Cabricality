onEvent("recipes", event => {
  let chop = (type, output) => {
    event.custom({
      "type": "farmersdelight:cutting",
      "ingredients": [{ "item": "tconstruct:" + type + "_slime_fern" }],
      "tool": { "tag": "c:tools/knives" },
      "result": [Item.of(KJ(type + "_slimy_fern_leaf"), 2).toResultJson()]
    })
    event.custom({
      "type": "create:haunting",
      "ingredients": [{ "item": KJ(type + "_slimy_fern_leaf") }],
      "results": [{ "item": TC(type + "_slime_fern") }]
    })
    event.custom(ifiniDeploying(KJ(type + "_slimy_fern_leaf", 2), TC(type + "_slime_fern"), "#c:tools/knives"))
    event.recipes.createMilling([KJ(type + "_slimy_fern_paste")], KJ(type + "_slimy_fern_leaf"))
    event.campfireCooking(output, KJ(type + "_slimy_fern_paste")).cookingTime(300)
  }
  chop("earth", MC("gunpowder"))
  chop("sky", MC("bone_meal"))
  chop("ender", AE2("ender_dust"))

  event.campfireCooking(MC("torch"), MC("stick")).cookingTime(20)

  //	event.shapeless(KJ("nickel_compound"), [KJ("nickel_ingot"), IV("iron_dust"), IV("iron_dust"), IV("iron_dust"), IV("iron_dust")])

  event.blasting(KJ("invar_compound"), KJ("nickel_compound"))
  let s = KJ("invar_compound")
  event.recipes.createSequencedAssembly([
    KJ("invar_ingot"),
  ], KJ("invar_compound"), [
    event.recipes.createPressing(s, s)
  ]).transitionalItem(s)
    .loops(16)
    .id("kubejs:invar_ingot")

  event.remove({ id: CR("mechanical_crafting/crushing_wheel") })
  event.recipes.createMechanicalCrafting(Item.of(CR("crushing_wheel"), 2), [
    " AAA ",
    "AABAA",
    "ABBBA",
    "AABAA",
    " AAA "
  ], {
    A: C("#cobblestone"),
    B: MC("stick")
  })

  event.recipes.createCrushing([Item.of(AE2("singularity")).withChance(1)], CR("crushing_wheel")).processingTime(250)

  let dyes = [MC("orange_dye"), MC("magenta_dye"), MC("light_blue_dye"), MC("yellow_dye"), MC("lime_dye"), MC("pink_dye"), MC("cyan_dye"), MC("purple_dye"), MC("blue_dye"), MC("brown_dye"), MC("green_dye"), MC("red_dye")]
  event.recipes.createCompacting("1x " + KJ("dye_entangled_singularity"), [dyes, Item.of(AE2("quantum_entangled_singularity"), 1)])
  event.recipes.createConversion([AE2("quantum_entangled_singularity")], AE2("singularity"))
  event.recipes.createCrushing([
    Item.of(AE2("red_paint_ball"), 1).withChance(.90),
    Item.of(AE2("yellow_paint_ball"), 1).withChance(.80),
    Item.of(AE2("green_paint_ball"), 1).withChance(.70),
    Item.of(AE2("blue_paint_ball"), 1).withChance(.60),
    Item.of(AE2("magenta_paint_ball"), 1).withChance(.50)],
    KJ("dye_entangled_singularity")).processingTime(50)

  event.recipes.createMechanicalCrafting(CR("chromatic_compound"), [
    "AA",
    "AA"
  ], {
    A: AE2("magenta_paint_ball")
  })

  event.recipes.createPressing(KJ("radiant_sheet"), CR("refined_radiance"))
  event.recipes.createMechanicalCrafting(KJ("radiant_coil"), ["A"], { A: KJ("radiant_sheet") })

  event.shaped(KJ("chromatic_resonator"), [
    " R ",
    "R S",
    "LS "
  ], {
    R: KJ("ruby"),
    L: IV("lead_ingot"),
    S: KJ("sapphire")
  })

  /*	let t = KJ("incomplete_inductive_mechanism")
    event.recipes.createSequencedAssembly([
      KJ("inductive_mechanism"),
    ], CR("precision_mechanism"), [
      event.recipes.createDeploying(t, [t, KJ("radiant_coil")]),
      event.recipes.createDeploying(t, [t, KJ("radiant_coil")]),
      event.recipes.createDeploying(t, [t, KJ("chromatic_resonator")])
    ]).transitionalItem(t)
      .loops(1)
      .id("kubejs:inductive_mechanism")	*/

  event.remove({ output: IV("machine_block") })
  event.shaped(IV("machine_block"), [
    "SSS",
    "SCS",
    "SSS"
  ], {
    C: KJ("invar_casing"),
    S: KJ("inductive_mechanism")
  })
  let invar_machine = (id, amount, other_ingredient) => {
    event.remove({ output: id })
    if (other_ingredient) {
      event.smithing(Item.of(id, amount), IV("machine_block"), other_ingredient)
      event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: IV("machine_block"), B: other_ingredient })
    }
    else
      event.stonecutting(Item.of(id, amount), IV("machine_block"))
  }

  invar_machine(IV("electric_furnace_mk1"), 1, MC("furnace"))
  invar_machine(IV("smelter_mk4"), 1, MC("blast_furnace"))
  invar_machine(IV("pulverizer_mk1"), 1, MC("flint"))
  invar_machine(IV("sawmill_mk1"), 1, KJ("saw_blade"))
  invar_machine(IV("recycler_mk2"), 1, MC("composter"))
  invar_machine(IV("condenser_mk4"), 1, MC("packed_ice"))
  invar_machine(IV("fluid_infuser_mk1"), 1, CR("whisk"))
  invar_machine(IV("modular_workbench_mk4"), 1, MC("crafting_table"))
  invar_machine(IV("lazuli_flux_container_mk1"), 1, MC("redstone_block"))
  invar_machine(IV("laser_emitter_mk4"), 1, MC("lightning_rod"))
  event.remove({ output: IV("compressor_mk1") })
  event.remove({ output: IV("chopper_mk1") })
  event.remove({ output: IV("farmer_mk1") })
  event.remove({ output: IV("slaughter_mk1") })
  event.remove({ output: IV("rancher_mk1") })
  event.remove({ output: IV("pump_mk1") })
  event.remove({ output: IV("mining_rig_mk4") })
  event.remove({ output: IV("data_card_writer_mk4") })
  event.remove({ output: IV("drain_mk1") })
  invar_machine(CX("energy_trash_can"), 1, KB("trash_can"))
})