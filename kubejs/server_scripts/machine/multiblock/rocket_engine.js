let engine_materials = [
  ["ad_astra", "steel"],
  ["ad_astra", "desh"],
  ["ad_astra", "ostrum"],
  ["ad_astra", "calorite"]
]
let engine_height = 2
let engine_end_height = 1

function check_structure_engine(event, material, modid) {
  let block = event.block
  let level_directions_engine = [
    [Direction.NORTH, Direction.WEST],
    [Direction.SOUTH, Direction.EAST],
    [Direction.WEST, Direction.SOUTH],
    [Direction.EAST, Direction.NORTH]
  ]
  let return_boolean = true
  level_directions_engine.forEach(director => {
    let top_block = block.offset(director[0])
    if (top_block.id != modid + ":" + material + "_plating") return_boolean = false
    if (top_block.offset(director[1]).id != modid + ":" + material + "_plating") return_boolean = false
    for (let i = 1; i <= engine_height; i++) {
      if (top_block.offset(Direction.DOWN, i) != modid + ":" + material + "_pillar") return_boolean = false
      if (top_block.offset(Direction.DOWN, i).offset(director[1]) != modid + ":" + material + "_pillar") return_boolean = false
    }
    if (top_block.offset(Direction.DOWN, engine_height + 1) != modid + ":" + material + "_plating") return_boolean = false
    if (top_block.offset(Direction.DOWN, engine_height + 1).offset(director[1]) != modid + ":" + material + "_plating") return_boolean = false

    top_block = top_block.offset(Direction.DOWN, engine_height + 1)
    for (let i = 1; i <= engine_end_height; i++) {
      if (top_block.offset(Direction.DOWN, i) != "ad_astra:iron_pillar") return_boolean = false
    }
    if (top_block.offset(Direction.DOWN, engine_end_height + 1) != "ad_astra:iron_plating") return_boolean = false
    if (top_block.offset(Direction.DOWN, engine_end_height + 1).offset(director[1]) != "ad_astra:iron_plating") return_boolean = false
  })
  return return_boolean
}

onEvent("block.right_click", event => {
  let material_id = null
  let modid = null
  engine_materials.forEach(loop_material => {
    if (event.block.id == loop_material[0] + ":" + loop_material[1] + "_block") {
      material_id = loop_material[1]
      modid = loop_material[0]
    }
  })
  if (material_id != null) {
    if (check_structure_engine(event, material_id, modid) == true) {
      let block = event.block
      let y = block.y
      let x = block.x
      let z = block.z
      dimensional_commanding(event.server, event.block.dimension, "fill " + (x+1) + " " + y + " " + (z+1) + " " + (x-1) + " " + (y-(engine_height+engine_end_height+2)) + " " + (z-1) + " minecraft:air")
      dimensional_commanding(event.server, event.block.dimension, `summon minecraft:item ${x} ${y} ${z} {Item:{id:"ad_astra:${material_id}_engine",Count:1b}}`)
    }
  }
})