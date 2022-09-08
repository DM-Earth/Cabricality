onEvent("recipes", event => {
  event.remove({ id: CR("crafting/materials/andesite_alloy") })
  event.remove({ id: CR("crafting/materials/andesite_alloy_from_zinc") })
  event.remove({ id: CR("mixing/andesite_alloy") })
  event.remove({ id: CR("mixing/andesite_alloy_from_zinc") })

  event.remove({ output: AP("algal_brick") })
  event.smelting(AP("algal_brick"), AP("algal_blend")).xp(0).cookingTime(120)
  event.remove({ id: AP("algal_blend_shapeless") })

  event.shaped(Item.of(AP("algal_blend"), 4), [
    "SS",
    "AA"
  ], {
    A: "minecraft:clay_ball",
    S: ["minecraft:kelp", "minecraft:seagrass"]
  })
  event.shaped(Item.of(AP("algal_blend"), 4), [
    "AA",
    "SS"
  ], {
    A: "minecraft:clay_ball",
    S: ["minecraft:kelp", "minecraft:seagrass"]
  })
  event.shaped(Item.of(CR("andesite_alloy"), 2), [
    "SS",
    "AA"
  ], {
    A: ["minecraft:andesite", CR("andesite_cobblestone")],
    S: AP("algal_brick")
  })
  event.shaped(Item.of(CR("andesite_alloy"), 2), [
    "AA",
    "SS"
  ], {
    A: ["minecraft:andesite", CR("andesite_cobblestone")],
    S: AP("algal_brick")
  })

  event.recipes.createMixing(Item.of(AP("algal_blend"), 2), ["minecraft:clay_ball", ["minecraft:kelp", "minecraft:seagrass"]])
  event.recipes.createMixing(Item.of(CR("andesite_alloy"), 2), [AP("algal_brick"), ["minecraft:andesite", CR("andesite_cobblestone")]])

  let transitional = "kubejs:incomplete_kinetic_mechanism"
  event.recipes.createSequencedAssembly([
    "kubejs:kinetic_mechanism",
  ], "#minecraft:wooden_slabs", [
    event.recipes.createDeploying(transitional, [transitional, CR("andesite_alloy")]),
    event.recipes.createDeploying(transitional, [transitional, CR("andesite_alloy")]),
    event.recipes.createDeploying(transitional, [transitional, "#fabric:saws"])
  ]).transitionalItem(transitional)
    .loops(1)
    .id("kubejs:kinetic_mechanism")
  event.shapeless(KJ("kinetic_mechanism"), [CR("cogwheel"), CR("andesite_alloy"), "#minecraft:logs"]).id("kubejs:kinetic_mechanism_manual_only")//.damageIngredient(1)
  event.shaped(KJ("andesite_machine"), [
    "SSS",
    "SCS",
    "SSS"
  ], {
    C: CR("andesite_casing"),
    S: KJ("kinetic_mechanism")
  })

  let andesite_machine = (id, amount, other_ingredient) => {
    event.remove({ output: id })
    if (other_ingredient) {
      event.smithing(Item.of(id, amount), "kubejs:andesite_machine", other_ingredient)
      event.recipes.createMechanicalCrafting(Item.of(id, amount), "AB", { A: "kubejs:andesite_machine", B: other_ingredient })
    }
    else
      event.stonecutting(Item.of(id, amount), "kubejs:andesite_machine")
  }

  event.remove({ output: IV("iron_drill_head") })
  event.shaped(IV("iron_drill_head"), [
    "NN ",
    "NLP",
    " PL"
  ], {
    N: MC("iron_nugget"),
    P: CR("iron_sheet"),
    L: IV("lead_ingot")
  })
  event.shaped(KJ("saw_blade"), [
    "NPN",
    "PLP",
    "NPN"
  ], {
    N: MC("iron_nugget"),
    P: CR("iron_sheet"),
    L: IV("lead_ingot")
  })

  andesite_machine("create:portable_storage_interface", 2)
  andesite_machine("create:encased_fan", 1, IV("fan"))
  andesite_machine("create:mechanical_press", 1, MC("iron_block"))
  andesite_machine("create:mechanical_mixer", 1, CR("whisk"))
  andesite_machine("create:mechanical_drill", 1, IV("iron_drill_head"))
  andesite_machine("create:mechanical_saw", 1, KJ("saw_blade"))
  andesite_machine("create:deployer", 1, CR("brass_hand"))
  andesite_machine(AR("coal_generator"), 1, IV("heat_coil"))
  andesite_machine("kubejs:extractor_machine", 1, MC("bucket"))
  andesite_machine("create:mechanical_harvester", 2)
  andesite_machine("create:mechanical_plough", 2)
  andesite_machine("create:andesite_funnel", 4)
  andesite_machine("create:andesite_tunnel", 4)
  andesite_machine(AE2("charger"), 1, AE2("fluix_crystal"))
})