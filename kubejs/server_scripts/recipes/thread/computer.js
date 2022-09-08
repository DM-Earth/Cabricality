onEvent("recipes", event => {
  event.remove({ output: CC("computer_normal") })
	event.custom({
		"type": "tconstruct:casting_basin",
		"cast": {
			"item": "ae2:controller"
		},
		"cast_consumed": true,
		"fluid": {
			"name": "tconstruct:molten_electrum",
			"amount": 27000
		},
		"result": "computercraft:computer_normal",
		"cooling_time": 20
	})
})