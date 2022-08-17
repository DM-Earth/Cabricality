onEvent("recipes", event => {
  let process = (ingot, nugget, raw, crushed, dust, fluid, id) => {
    event.remove({ output: ingot, input: raw })
    event.remove({ output: ingot, input: crushed })

    event.remove({ output: ingot, input: dust })
    event.remove({ output: nugget, input: crushed, type: "create:splashing" })

    event.smelting(nugget, dust).cookingTime(40)
    event.blasting(nugget, dust).cookingTime(20)
    event.smelting(nugget, crushed)
    event.blasting(nugget, crushed)

    event.recipes.createMilling([Item.of(dust, 3)], crushed)
    event.recipes.createCrushing([Item.of(dust, 3), Item.of(dust, 3).withChance(0.5)], crushed)
    event.recipes.createSplashing([Item.of(nugget, 2)], dust)

    event.custom({
      "type": "alloy_forgery:forging",
      "inputs": [
        { "item": dust },
        { "item": dust },
        { "item": dust }
      ],
      "output": {
        "id": ingot,
        "count": 1
      },
      "min_forge_tier": 1,
      "fuel_per_tick": 2
    })

    event.remove({ id: TC("smeltery/melting/metal/" + id + "/dust") })

    event.custom({
      "type": "tconstruct:melting",
      "ingredient": {
        "item": dust
      },
      "result": {
        "fluid": fluid,
        "amount": 3000
      },
      "temperature": 500,
      "time": 15
    })
    event.remove({ id: "alloy_forgery:" + id + "_from_ores" })
    event.remove({ id: "alloy_forgery:" + id + "_from_raw_ores" })
  }
  let metals = [
    //0metalMod, 1metalId, 2nuggetMod, 3crushedMod, 4dustMod, 5moltenMod
    ["minecraft", "iron", "minecraft", "create", "indrev", "tconstruct"],
    ["minecraft", "gold", "minecraft", "create", "indrev", "tconstruct"],
    ["minecraft", "copper", "create", "create", "indrev", "tconstruct"],
    ["minecraft", "iron", "minecraft", "create", "indrev", "tconstruct"],

    ["create", "zinc", "create", "create", "createplus", "tconstruct"],

    ["indrev", "tin", "indrev", "create", "indrev", "tconstruct"],
    ["indrev", "lead", "indrev", "create", "indrev"], "tconstruct",

    ["tconstruct", "cobalt", "tconstruct", "kubejs", "kubejs", "tconstruct"],

    ["ad_astra", "desh", "ad_astra", "kubejs", "kubejs", "kubejs"],
    ["ad_astra", "ostrum", "ad_astra", "kubejs", "kubejs", "kubejs"],
    ["ad_astra", "calorite", "ad_astra", "kubejs", "kubejs", "kubejs"],

    ["kubejs", "nickel", "kubejs", "create", "kubejs", "tconstruct"]
  ]
  metals.forEach(metal => {
    let ingot = metal[0] + ":" + metal[1] + "_ingot"
    let nugget = metal[2] + ":" + metal[1] + "_nugget"
    let raw = metal[0] + ":raw_" + metal[1]
    let crushed = metal[3] + ":crushed_" + metal[1] + "_ore"
    let dust = metal[4] + ":" + metal[1] + "_dust"
    let fluid = metal[5] + ":molten_" + metal[1]

    process(ingot, nugget, raw, crushed, dust, fluid, metal[1])

    event.remove({ output: ingot, input: "#" + metal[0] + ":" + metal[1] + "_ores" })
    event.remove({ output: ingot, input: "#c:" + metal[1] + "_ores" })
    event.remove({ output: ingot, input: "#c:ores/" + metal[1]})
  })
})