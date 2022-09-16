onEvent("recipes", event => {
  let missing_wood_types_normal = [
    ["malum", "runewood"],
    ["malum", "soulwood"],

    ["terrestria", "rainbow_eucalyptus"],
    ["terrestria", "cypress"],
    ["terrestria", "hemlock"],
    ["terrestria", "redwood"],
    ["terrestria", "yucca_palm"],
    ["terrestria", "sakura"],
    ["terrestria", "japanese_maple"],
    ["terrestria", "willow"],
    ["terrestria", "rubber"]
  ]

  missing_wood_types_normal.forEach(e => {
    event.recipes.createCutting(asIdentifier("stripped_" + e[1] + "_log", e[0]), asIdentifier(e[1] + "_log", e[0])).processingTime(150)
    event.recipes.createCutting(asIdentifier("stripped_" + e[1], e[0]), asIdentifier(e[1], e[0])).processingTime(150)
    event.recipes.createCutting("6x " + asIdentifier(e[1] + "_planks", e[0]), asIdentifier("stripped_" + e[1] + "_log", e[0])).processingTime(150)
    event.recipes.createCutting("6x " + asIdentifier(e[1] + "_planks", e[0]), asIdentifier("stripped_" + e[1], e[0])).processingTime(150)

    event.recipes.createCutting("2x " + asIdentifier(e[1] + "_slab", e[0]),  asIdentifier(e[1] + "_planks", e[0])).processingTime(150)
  })
})