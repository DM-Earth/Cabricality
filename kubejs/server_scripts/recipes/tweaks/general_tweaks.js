onEvent("recipes", event => {
  event.remove({ mod: "agape_space" })
  event.remove({ type: IV("sawmill") })
  event.remove({ type: IV("compress") })
  event.remove({ type: AE2("inscriber") })
  event.shaped(KJ("circuit_scrap", 2), [" A ", "ABA", " A "], { A: KJ("invar_ingot"), B: C("#circuit_press") })
  event.stonecutting(AE2("silicon_press"), KJ("circuit_scrap"))
  event.stonecutting(AE2("engineering_processor_press"), KJ("circuit_scrap"))
  event.stonecutting(AE2("calculation_processor_press"), KJ("circuit_scrap"))
  event.stonecutting(AE2("logic_processor_press"), KJ("circuit_scrap"))

  event.remove({ id: FD("flint_knife") })
  event.remove({ id: FD("iron_knife") })
  event.remove({ id: FD("golden_knife") })
  event.remove({ id: FD("diamond_knife") })
  event.shaped(FD("flint_knife"), ["S ", " M"], { M: MC("flint"), S: C("#rods/wooden") })
  event.shaped(FD("iron_knife"), ["S ", " M"], { M: MC("iron_ingot"), S: C("#rods/wooden") })
  event.shaped(FD("golden_knife"), ["S ", " M"], { M: MC("gold_ingot"), S: C("#rods/wooden") })
  event.shaped(FD("diamond_knife"), ["S ", " M"], { M: MC("diamond"), S: C("#rods/wooden") })

  event.remove({ output: IV("fluid_pipe_mk1") })
  event.remove({ output: IV("fluid_pipe_mk2") })
  event.remove({ output: IV("fluid_pipe_mk3") })
  event.remove({ output: IV("fluid_pipe_mk4") })
  event.remove({ output: IV("item_pipe_mk1") })
  event.remove({ output: IV("cable_mk1") })
  event.remove({ output: KB("placer") })
  event.remove({ output: KB("breaker") })
  event.remove({ output: ED("shadow_drawer") })
  event.remove({ output: IV("wrench") })
  event.remove({ output: KB("regular_conveyor_belt") })
  event.remove({ output: KB("fast_conveyor_belt") })
  event.remove({ output: KB("express_conveyor_belt") })

  event.remove({ output: IV("hammer") })
  event.remove({ input: IV("hammer") })
  event.remove({ output: AR("hammer") })
  event.remove({ input: AR("hammer") })

  event.remove({ id: TC("smeltery/casting/metal/gold/coin_sand_cast") })
  event.remove({ id: TC("smeltery/casting/metal/gold/coin_gold_cast") })
  event.remove({ id: TC("smeltery/casting/metal/silver/coin_sand_cast") })
  event.remove({ id: TC("smeltery/casting/metal/silver/coin_gold_cast") })

  event.replaceInput("#c:gold_plates", "create:golden_sheet")
  event.replaceInput("#c:iron_plates", "create:iron_sheet")
  event.replaceInput("#c:copper_plates", "create:copper_sheet")
  event.replaceInput("#c:plates/gold", "create:golden_sheet")
  event.replaceInput("#c:plates/iron", "create:iron_sheet")
  event.replaceInput("#c:plates/copper", "create:copper_sheet")
  event.replaceInput("indrev:nikolite_dust", "minecraft:redstone")
  event.replaceInput("#c:steel_ingots", "indrev:steel_ingot")
  event.replaceInput("#c:compressed_steel", "indrev:steel_plate")
  event.replaceInput("#c:compressed_desh", "ad_astra:desh_plate")

  event.blasting(IV("steel_ingot"), MC("iron_ingot")).cookingTime(400)

  event.remove({ id: CR("splashing/gravel") })
  event.recipes.createSplashing([
    Item.of(MC("iron_nugget", 3)).withChance(0.25),
    Item.of(MC("flint")).withChance(0.25)
  ], "minecraft:gravel")

  event.remove({ id: CR("splashing/red_sand") })
  event.recipes.createSplashing([
    Item.of(MC("gold_nugget", 2)).withChance(0.125),
    Item.of(MC("dead_bush")).withChance(0.05)
  ], "minecraft:red_sand")

  event.shaped("8x indrev:cable_mk1", [
    "PMP"
  ], {
    P: KJ("invar_ingot"),
    M: MC("redstone")
  })
  event.shaped(IV("item_pipe_mk1", 8), [
    "PMP"
  ], {
    P: CR("brass_sheet"),
    M: CR("brass_ingot")
  })
  event.remove({ output: MC("hopper") })
  event.shaped(MC("hopper"), [
    "T T",
    "S S",
    " S "
  ], {
    T: IV("tin_ingot"),
    S: IV("steel_plate")
  })
  event.remove({ output: KB("fluid_hopper") })
  event.shaped(KB("fluid_hopper"), [
    "T T",
    "C C",
    " C "
  ], {
    T: IV("lead_ingot"),
    C: CR("copper_sheet")
  })
  event.remove({ output: IV("planks") })
  event.recipes.createPressing(IV("planks"), MC("#wooden_slabs"))
  event.remove({ output: ED("single_drawer") })
  event.shaped(ED("single_drawer"), [
    "P",
    "B",
    "P"
  ], {
    B: MC("barrel"),
    P: IV("planks")
  })
  event.remove({ output: ED("connector") })
  event.shaped(ED("connector"), [
    "P",
    "W",
    "P"
  ], {
    W: MC("#planks"),
    P: IV("planks")
  })
  event.remove({ output: ED("double_drawer") })
  event.shaped(ED("double_drawer", 2), [
    "PP"
  ], {
    P: ED("single_drawer")
  })
  event.remove({ output: ED("quad_drawer") })
  event.shaped(ED("quad_drawer", 4), [
    "PP",
    "PP"
  ], {
    P: ED("single_drawer")
  })
  event.remove({ output: ED("quad_drawer") })
  event.shaped(ED("quad_drawer", 2), [
    "P",
    "P"
  ], {
    P: ED("double_drawer")
  })
  event.remove({ output: ED("t4_upgrade") })
  event.remove({ output: ED("upgrade_frame") })
  event.remove({ output: CR("propeller") })
  event.stonecutting(ED("upgrade_frame"), IV("planks"))
  event.remove({ output: ED("lock") })
  event.shaped(ED("lock", 1), [
    "B",
    "G"
  ], {
    B: CR("brass_nugget"),
    G: CR("golden_sheet")
  })
  event.replaceInput({ output: AP("nether_brass_blend") }, MC("copper_ingot"), IV("copper_dust"))

  event.remove({ output: BC("smart_chisel") })
  event.shaped(BC("smart_chisel", 1), [
    "BS"
  ], {
    S: MC("stick"),
    B: CR("polished_rose_quartz")
  })

  event.remove({ output: IV("solid_infuser_factory_mk4") })
  event.remove({ output: IV("compressor_factory_mk4") })
  event.remove({ output: IV("pulverizer_factory_mk4") })
  event.remove({ output: IV("electric_furnace_factory_mk4") })

  event.remove({ output: CR("item_vault") })
  event.shaped(CR("item_vault"), [
    "L",
    "B",
    "L"
  ], {
    L: IV("lead_plate"),
    B: MC("barrel")
  })
  event.remove({ output: MC("shulker_box") })
  event.shaped(MC("shulker_box"), [
    "L",
    "B",
    "L"
  ], {
    L: MC("shulker_shell"),
    B: MC("barrel")
  })
  event.remove({ output: IV("cabinet") })
  event.shaped(IV("cabinet"), [
    "L",
    "B",
    "L"
  ], {
    L: CR("iron_sheet"),
    B: MC("barrel")
  })
  event.remove({ output: IV("fan") })
  event.shaped(IV("fan"), [
    " T ",
    "TIT",
    " T "
  ], {
    T: IV("tin_plate"),
    I: MC("iron_ingot")
  })
  event.remove({ output: AP("plating_block") })
  event.stonecutting(AP("plating_block", 2), IV("steel_plate"))

  event.remove({ output: TC('obsidian_pane') })
  event.shaped(TC('obsidian_pane', 8), [
    'SSS',
    'SSS'
  ], {
    S: MC('obsidian')
  })

  event.replaceInput(AP('withered_bone'), TC('necrotic_bone'))


  event.remove({ id: TC('compat/create/andesite_alloy_zinc') })
  event.remove({ id: TC('compat/create/andesite_alloy_iron') })

  event.replaceInput({ output: TC("seared_duct") }, TC("cobalt_ingot"), CR("brass_ingot"))
  event.replaceInput({ output: TC("scorched_duct") }, TC("cobalt_ingot"), CR("brass_ingot"))

  event.replaceInput({ output: TC("scorched_drain") }, TC("obsidian_pane"), CR("sturdy_sheet"))
  event.replaceInput({ output: TC("scorched_chute") }, TC("obsidian_pane"), CR("sturdy_sheet"))

  event.remove({ output: "naturescompass:naturescompass" })
  event.remove({ output: "explorerscompass:explorerscompass" })
  donutCraft(event, "naturescompass:naturescompass", MC("compass"), MC("#leaves"))

  event.remove({ output: CX("portable_tank_mk1") })
  event.shaped(CX("portable_tank_mk1"), [
    "T",
    "P",
    "T"
  ], {
    T: IV("tin_plate"),
    P: KB("tank")
  })
  event.remove({ output: CX("cardboard_box") })
  event.shaped(CX("cardboard_box"), [
    "T",
    "P",
    "T"
  ], {
    T: IV("planks"),
    P: MC("paper")
  })
  event.remove({ output: CX("wooden_hopper") })
  event.shaped(CX("wooden_hopper"), [
    "W W",
    "P P",
    " P "
  ], {
    W: MC("#planks"),
    P: IV("planks")
  })
  event.remove({ output: CX("grannys_sink") })
  event.recipes.createMechanicalCrafting(CX("grannys_sink"), [
    "SSSSS",
    "SSSSS",
    "SSWSS",
    "SSSSS",
    "SSSSS"
  ], {
    W: MC("water_bucket"),
    S: KJ("computation_matrix")
  })
  event.remove({ output: CX("copper_ladder") })
  event.replaceOutput({}, CR("copper_ladder"), CX("copper_ladder"))

  event.remove({ output: "create:sail_frame" })
  event.remove({ output: "create:white_sail" })
  event.shapeless("create:sail_frame", ["create:white_sail"])
  event.shaped("2x create:white_sail", [
    "SSS",
    "NAN",
    "SSS"
  ], {
    A: "#minecraft:wool",
    N: "minecraft:iron_nugget",
    S: "minecraft:stick"
  })

  event.replaceInput({ id: CR("crafting/kinetics/brass_hand") }, "#c:plates/brass", CR("golden_sheet"))
  event.recipes.createPressing(KJ("zinc_sheet"), CR("zinc_ingot"))
  event.replaceInput(IV("steel_block"), AR("steel_block"))
  event.replaceInput(IV("steel_nugget"), AR("steel_nugget"))
  event.replaceInput(AR("steel_ingot"), IV("steel_ingot"))
  event.remove({output: IV("steel_block")})
  event.remove({output: IV("steel_nugget")})
  event.remove({output: AR("steel_ingot")})

  wood_types.forEach(wood => {
    if (wood.startsWith("tconstruct")) {
      event.recipes.createCutting("2x " + wood + "_planks_slab", wood + "_planks").processingTime(150)
    } else {
      event.recipes.createCutting("2x " + wood + "_slab", wood + "_planks").processingTime(150)
    }
  })
})