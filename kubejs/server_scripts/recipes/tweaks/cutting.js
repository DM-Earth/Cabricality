onEvent("recipes", event => {
    wood_types.forEach(wood => {
        if (wood.startsWith("tconstruct")) {
            event.recipes.createCutting("2x " + wood + "_planks_slab", wood + "_planks").processingTime(150)
        } else {
            event.recipes.createCutting("2x " + wood + "_slab", wood + "_planks").processingTime(150)
        }
    })

    missing_wood_types_normal.forEach(e => {
        let plank = asIdentifier(e[1] + "_planks", e[0])
        let stripped_log = asIdentifier("stripped_" + e[1] + "_log", e[0])
        let stripped_wood = asIdentifier("stripped_" + e[1] + "_wood", e[0])
        let log = asIdentifier(e[1] + "_log", e[0])
        let wood = asIdentifier(e[1] + "_wood", e[0])

        if (e[0] == "malum") {
            wood = asIdentifier(e[1], e[0])
            stripped_wood = asIdentifier("stripped_" + e[1], e[0])
        }

        if (!listIncludes(wood_types_no_wood, e)) {
            event.recipes.createCutting("6x " + plank, stripped_wood).processingTime(150)
            event.recipes.createCutting(stripped_wood, wood).processingTime(150)
        }

        event.recipes.createCutting(stripped_log, log).processingTime(150)
        event.recipes.createCutting("6x " + plank, stripped_log).processingTime(150)

        event.recipes.createCutting("2x " + asIdentifier(e[1] + "_slab", e[0]), asIdentifier(e[1] + "_planks", e[0])).processingTime(150)
    })
})