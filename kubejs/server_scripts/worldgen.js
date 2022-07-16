onEvent('worldgen.remove', event => {
  event.removeSpawnsByCategory(spawns => {
    spawns.biomes.values = [
      'agape:empty_space',
      'agape:europa_ocean_deep',
      'agape:europa_surface_ground',
      'agape:ganymede_ground',
      'agape:io_ground',
      'agape:mars_plains',
      'agape:mercury_plain',
      'agape:moon_ground',
      'agape:titan_ground',
      'agape:venus_clouds',
      'agape:venus_lowlands',
      'agape:venus_plain'
    ]
  })
})