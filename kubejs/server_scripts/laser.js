let outputItem = ''
let length = 16
global.seed = 114514

function itemRecipes(inputBlock) {
  if (inputBlock == 'minecraft:basalt') { return 'kubejs:basalz_shard' }
  if (inputBlock == 'minecraft:snow_block') { return 'kubejs:blizz_cube' }
  return ''
}
function blockRecipes(inputBlock) {
  if (inputBlock == 'minecraft:water') { return 'kubejs:powered_water' }
  return ''
}

onEvent('server.load', event => {
  global.seed = event.server.getOverworld().getSeed()
})

onEvent('block.left_click', event => {
  if (event.block.id == "kibe:fluid_hopper" && event.block.down.id == "indrev:laser_emitter_mk4" && event.block.up.id == "kubejs:powered_water") {
    let block = event.block
    x = block.x
    y = block.y
    z = block.z
    yUp = y + 1
    yDown = y - 1

    let stringDirection = block.down.properties.facing
    let facing = ''

    if (stringDirection == 'north') facing = Direction.NORTH
    if (stringDirection == 'south') facing = Direction.SOUTH
    if (stringDirection == 'east') facing = Direction.EAST
    if (stringDirection == 'west') facing = Direction.WEST
    if (stringDirection == 'up') facing = Direction.UP
    if (stringDirection == 'down') facing = Direction.DOWN

    for (let i = 1; i <= length; i++) {
      let targetBlock = block.down.offset(facing, i)
      let blockX = targetBlock.x
      let blockY = targetBlock.y
      let blockZ = targetBlock.z

      let outputItem = itemRecipes(targetBlock.id)
      let outputBlock = blockRecipes(targetBlock.id)
      if (outputItem != '') {
        event.server.runCommandSilent('setblock ' + blockX + ' ' + blockY + ' ' + blockZ + ' minecraft:air')
        event.server.runCommandSilent('summon minecraft:item ' + blockX + ' ' + blockY + ' ' + blockZ + ' {Health:32767, Item:{id:"' + outputItem + '",Count:1b}}')
      }
      if (outputBlock != '') {
        event.server.runCommandSilent('setblock ' + blockX + ' ' + blockY + ' ' + blockZ + ' ' + outputBlock)
      }
    }

    for (let i = length; i >= 1; i = i - 1) {
      let targetBlock = block.down.offset(facing, i)
      let explosion = targetBlock.createExplosion();
      if (i >= 6) {
        explosion = explosion.strength(3.2)
      } else {
        explosion = explosion.strength(0.1)
      }
      explosion = explosion.causesFire(false)
      explosion.explode()
    }
    if (Math.random() > 0.5) {
      event.server.runCommandSilent('setblock ' + x + ' ' + yUp + ' ' + z + ' minecraft:water')
    }
  }
})