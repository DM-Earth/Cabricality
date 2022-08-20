onEvent("worldgen.remove", event => {
	event.removeFeatureById("underground_ores", [
		"indrev:nikolite_ore",
		"indrev:silver_ore",
		"indrev:sulfur_crystal_overworld",
		"indrev:sulfuric_acid_lake",
		"indrev:tungsten_ore",
	])

	event.removeFeatureById("underground_decoration", [
		"indrev:sulfur_crystal_overworld"
	])

	event.removeFeatureById("underground_structures", [
		"tconstruct:sky_geode"
	])
})