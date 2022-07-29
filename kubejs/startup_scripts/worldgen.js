onEvent("worldgen.remove", event => {
	event.removeFeatureById("underground_ores", [
		"indrev:nikolite_ore",
		"indrev:silver_ore",
		"indrev:sulfur_crystal_overworld",
		"indrev:sulfuric_acid_lake",
		"indrev:tungsten_ore",
	])

	/*	event.removeFeatureById("agape:ore_aluminum")
		event.removeFeatureById("agape:ore_copper_large")
		event.removeFeatureById("agape:ore_element_cold")
		event.removeFeatureById("agape:ore_element_cold_titan")
		event.removeFeatureById("agape:ore_element_power")
		event.removeFeatureById("agape:ore_gold_large")
		event.removeFeatureById("agape:ore_iron_large")
		event.removeFeatureById("agape:ore_living")
		event.removeFeatureById("agape:ore_rich_rock")
		event.removeFeatureById("agape:ore_sediment_diamond")
		event.removeFeatureById("agape:ore_slime_titan")
		event.removeFeatureById("agape:ore_space_crystal")
		event.removeFeatureById("agape:ore_titanium")	*/

})