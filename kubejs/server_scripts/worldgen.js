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
  event.removeFeatureById('indrev:nikolite_ore')
  event.removeFeatureById('indrev:silver_ore')
  event.removeFeatureById('indrev:sulfur_crystal_overworld'),
  event.removeFeatureById('indrev:sulfuric_acid_lake'),
  event.removeFeatureById('indrev:tungsten_ore')

  event.removeFeatureById('agape:ore_aluminum')
  event.removeFeatureById('agape:ore_copper_large')
  event.removeFeatureById('agape:ore_element_cold')
  event.removeFeatureById('agape:ore_element_cold_titan')
  event.removeFeatureById('agape:ore_element_power')
  event.removeFeatureById('agape:ore_gold_large')
  event.removeFeatureById('agape:ore_iron_large')
  event.removeFeatureById('agape:ore_living')
  event.removeFeatureById('agape:ore_rich_rock')
  event.removeFeatureById('agape:ore_sediment_diamond')
  event.removeFeatureById('agape:ore_slime_titan')
  event.removeFeatureById('agape:ore_space_crystal')
  event.removeFeatureById('agape:ore_titanium')
//  event.removeFeatureById('agape:radiant_tree')
})