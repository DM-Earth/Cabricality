let tank_materials = [
  ["ad_astra", "steel"],
  ["ad_astra", "desh"],
  ["ad_astra", "ostrum"],
  ["ad_astra", "calorite"]
]
let tank_height = 5

function check_structure_tank(event, material, modid) {
  let block = event.block
  let level_directions_tank = [
    Direction.NORTH,
    Direction.SOUTH,
    Direction.WEST,
    Direction.EAST
  ]
  let return_boolean = true
  level_directions_tank.forEach(director => {
    let bottom_block = block.offset(director, 1)
    if (bottom_block.id != modid + ":" + material + "_plating") return_boolean = false
    for (let i = 1; i <= tank_height; i++) {
      if (bottom_block.offset(Direction.UP, i).id != modid + ":" + material + "_pillar") return_boolean = false
    }
    if (bottom_block.offset(Direction.UP, tank_height + 1).id != modid + ":" + material + "_plating") return_boolean = false
  })
  for (let i = 1; i < tank_height; i++) {
    if (!block.offset(Direction.UP, i).hasTag("cabricality:rocket_fuel")) return_boolean = false
  }
  if (block.offset(Direction.UP, tank_height + 1).id != modid + ":" + material + "_block") return_boolean = false
  return return_boolean
}
onEvent("block.right_click", event => {
  let material_id = null
  let modid = null
  tank_materials.forEach(loop_material => {
    if (event.block.id == loop_material[0] + ":" + loop_material[1] + "_block") {
      material_id = loop_material[1]
      modid = loop_material[0]
    }
  })
  if (material_id != null) {
    if (check_structure_tank(event, material_id, modid) == true) {
      let block = event.block
      let y = block.y
      let x = block.x
      let z = block.z

      let XP = x + 1
      let XM = x - 1
      let YH = y + tank_height + 1
      let ZP = z + 1
      let ZM = z -1 

      event.server.runCommandSilent("fill " + XP + " " + y + " " + z + " " + XM + " " + YH + " " + z + " minecraft:air")
      event.server.runCommandSilent("fill " + x + " " + y + " " + ZP + " " + x + " " + YH + " " + ZM + " minecraft:air")
      event.server.runCommandSilent(`summon minecraft:item ${x} ${y} ${z} {Item:{id:"ad_astra:${material_id}_tank",Count:1b}}`)
    }
  }
})