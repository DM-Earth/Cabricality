onEvent("recipes", event => {
  event.remove({ id: TC('smeltery/alloys/molten_bronze') })
	event.remove({ id: TC('smeltery/alloys/molten_brass') })
	event.remove({ id: TC('smeltery/alloys/molten_invar') })
	event.remove({ id: TC('smeltery/alloys/molten_electrum') })
	event.remove({ id: TC('smeltery/alloys/molten_enderium') })

	event.remove({ type: MC("crafting_shapeless"), output: "createplus:brass_dust" })

	event.remove({ id: TC("smeltery/casting/seared/smeltery_controller") })
	event.remove({ id: TC("smeltery/melting/copper/smeltery_controller") })
	donutCraft(event, TC('smeltery_controller'), TC('seared_bricks'), KJ('sealed_mechanism'))

	event.remove({ id: TC('smeltery/casting/scorched/foundry_controller') })
	donutCraft(event, TC('foundry_controller'), TC('scorched_bricks'), KJ('infernal_mechanism'))
	event.remove({ id: TC('smeltery/melting/soul/sand') })

	event.custom({
		"type": "tconstruct:melting",
		"ingredient": {
			"item": "minecraft:redstone"
		},
		"result": {
			"fluid": "kubejs:redstone",
			"amount": 9000
		},
		"temperature": 250,
		"time": 15
	})
	event.custom({
		"type": "tconstruct:melting",
		"ingredient": {
			"item": "minecraft:redstone_block"
		},
		"result": {
			"fluid": "kubejs:redstone",
			"amount": 81000
		},
		"temperature": 250,
		"time": 135
	})
})