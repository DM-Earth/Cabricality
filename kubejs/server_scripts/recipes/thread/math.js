onEvent("recipes", event => {
  let types = ["three", "eight", "plus", "minus", "multiply", "divide"]
	types.forEach(e => {
		event.stonecutting(KJ(e + "_cast"), IV("bronze_plate"))
		event.custom({
			"type": "tconstruct:casting_table",
			"cast": {
				"item": KJ(e + '_cast')
			},
			"fluid": {
				"name": "kubejs:raw_logic",
				"amount": 81
			},
			"result": Item.of(KJ(e)).toResultJson(),
			"cooling_time": 10
		})
	})

	let nums = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
	let ops = [(a, b) => a + b, (a, b) => a - b, (a, b) => a * b, (a, b) => b == 0 ? "error" : a / b]
	let opNames = ["plus", "minus", "multiply", "divide"]
	for (var a = 0; a < 10; a++) {
		for (var b = 0; b < 10; b++) {
			for (var op = 0; op < ops.length; op++) {

				let result = ops[op](a, b)
				var output;

				if (result == "error")
					output = KJ("missingno")
				else if (result < 0)
					continue
				else if (result > 9)
					continue
				else if (result % 1 != 0)
					continue
				else
					output = KJ(nums[result])

				event.custom({
					"type": "create:mechanical_crafting",
					"pattern": [
						"AOB"
					],
					"key": {
						"A": {
							"item": KJ(nums[a])
						},
						"O": {
							"item": KJ(opNames[op])
						},
						"B": {
							"item": KJ(nums[b])
						}
					},
					"result": {
						"item": output
					},
					"acceptMirrored": false
				})
			}
		}
	}

	let meltOrCrucible = (id, out, outAmount) => {
		event.custom({
			"type": "tconstruct:melting",
			"ingredient": { "item": id },
			"result": {
				"fluid": out,
				"amount": outAmount
			},
			"temperature": 200,
			"time": 20
		})
	}

	let alloyAmount = 810
	let outAmount = 4050
	event.custom({
		"type": "tconstruct:alloy",
		"inputs": [
			{ "name": "kubejs:number_0", "amount": alloyAmount },
			{ "name": "kubejs:number_1", "amount": alloyAmount },
			{ "name": "kubejs:number_2", "amount": alloyAmount },
			{ "name": "kubejs:number_3", "amount": alloyAmount },
			{ "name": "kubejs:number_4", "amount": alloyAmount },
			{ "name": "kubejs:number_5", "amount": alloyAmount },
			{ "name": "kubejs:number_6", "amount": alloyAmount },
			{ "name": "kubejs:number_7", "amount": alloyAmount },
			{ "name": "kubejs:number_8", "amount": alloyAmount },
			{ "name": "kubejs:number_9", "amount": alloyAmount }
		],
		"result": {
			"fluid": "kubejs:matrix",
			"amount": outAmount
		},
		"temperature": 200
	})

	meltOrCrucible(KJ("calculation_mechanism"), KJ("raw_logic"), 2430)
	meltOrCrucible(KJ("zero"), KJ("number_0"), 81)
	meltOrCrucible(KJ("one"), KJ("number_1"), 81)
	meltOrCrucible(KJ("two"), KJ("number_2"), 81)
	meltOrCrucible(KJ("three"), KJ("number_3"), 81)
	meltOrCrucible(KJ("four"), KJ("number_4"), 81)
	meltOrCrucible(KJ("five"), KJ("number_5"), 81)
	meltOrCrucible(KJ("six"), KJ("number_6"), 81)
	meltOrCrucible(KJ("seven"), KJ("number_7"), 81)
	meltOrCrucible(KJ("eight"), KJ("number_8"), 81)
	meltOrCrucible(KJ("nine"), KJ("number_9"), 81)

	event.custom({
		"type": "tconstruct:casting_basin",
		"fluid": {
			"name": "kubejs:matrix",
			"amount": 81000
		},
		"result": Item.of(KJ("computation_matrix")).toResultJson(),
		"cooling_time": 20
	})
})