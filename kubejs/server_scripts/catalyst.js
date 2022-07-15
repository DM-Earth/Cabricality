function rnd(seed){
  seed = ( seed * 9301 + 49297 ) % 233280 //toxic, don't touch it
  return seed / ( 233280.0 )
}

function rand(number, seed){
  return Math.ceil(rnd(seed) * number)
};

function select(input, seed) {
  let selectTemp = input
  let selected = []
  for (i = 0; i < 4; i++) {
    num = rand(selectTemp.length - 1, seed + i)
    selected.push(selectTemp[num])
    selectTemp.splice(num, 1)
  }
  return selected
}

onEvent('recipes', event => {
  let localSeed = global.seed
  let setCount = 1
  let catalystMix = (catalyst, input) => {
    event.custom({
      "type": "alloy_forgery:forging",
      "inputs": [
        { "item": "kubejs:substrate_" + input[0] },
        { "item": "kubejs:substrate_" + input[1] },
        { "item": "kubejs:substrate_" + input[2] },
        { "item": "kubejs:substrate_" + input[3] }
      ],
      "output": {
        "id": "kubejs:substrate_" + catalyst,
        "count": 1
      },
      "min_forge_tier": 3,
      "fuel_per_tick": 25
    })
  }

  let igneous = ['andesite', 'diorite', 'granite', 'cobblestone', 'basalt', 'limestone']
  let herbal = ['red', 'orange', 'yellow', 'green', 'blue', 'magenta']
  let volatile = ['blaze', 'slime', 'nether', 'obsidian', 'gunpowder', 'prismarine']
  let metal = ['zinc', 'copper', 'iron', 'lead', 'gold']
  let crystal = ['sulfur', 'certus']
  let gem = ['lapis', 'emerald', 'diamond', 'ruby', 'sapphire']
  let chaos = ['igneous', 'herbal', 'volatile', 'crystal', 'metal', 'gem']

  catalystMix('igneous', select(igneous, localSeed))
  catalystMix('herbal', select(herbal, localSeed))
  catalystMix('volatile', select(volatile, localSeed))
  catalystMix('metal', select(metal, localSeed))
  catalystMix('gem', select(gem, localSeed))
  catalystMix('chaos', select(chaos, localSeed))

  let allSubstrate = igneous.concat(herbal).concat(volatile).concat(metal).concat(crystal).concat(gem)

  event.custom({
    "type": "alloy_forgery:forging",
    "inputs": [
      { "item": "kubejs:substrate_chaos" },
      { "item": "kubejs:substrate_" + allSubstrate[rand(allSubstrate.length, localSeed + 114514)] }
    ],
    "output": {
      "id": "kubejs:substrate_silicon",
      "count": 1
    },
    "min_forge_tier": 3,
    "fuel_per_tick": 25
  })
  event.custom({
    "type": "alloy_forgery:forging",
    "inputs": [
      { "item": "kubejs:substrate_chaos" },
      { "item": "kubejs:substrate_" + allSubstrate[rand(allSubstrate.length, localSeed + 1919810)] }
    ],
    "output": {
      "id": "kubejs:substrate_silver",
      "count": 1
    },
    "min_forge_tier": 3,
    "fuel_per_tick": 25
  })

  event.custom({
    "type": "alloy_forgery:forging",
    "inputs": [
      { "item": "kubejs:substrate_sulfur" },
      { "item": "kubejs:substrate_certus" }
    ],
    "output": {
      "id": "kubejs:substrate_crystal",
      "count": 1
    },
    "min_forge_tier": 3,
    "fuel_per_tick": 25
  })
})