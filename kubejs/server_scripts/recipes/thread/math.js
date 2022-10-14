onEvent("recipes", event => {
  let types = ["three", "eight", "plus", "minus", "multiply", "divide"]
	types.forEach(e => {
		event.stonecutting(CABF(e + "_cast"), IV("bronze_plate"))
		event.custom({
			"type": "tconstruct:casting_table",
			"cast": {
				"item": CABF(e + "_cast")
			},
			"fluid": {
				"name": asIdentifier("raw_logic"),
				"amount": 81
			},
			"result": Item.of(CABF(e)).toResultJson(),
			"cooling_time": 10
		})
	})

	let nums = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
	let ops = [(a, b) => a + b, (a, b) => a - b, (a, b) => a * b, (a, b) => b == 0 ? "error" : a / b]
	let opNames = ["plus", "minus", "multiply", "divide"]
	for (let a = 0; a < 10; a++) {
		for (let b = 0; b < 10; b++) {
			for (let op = 0; op < ops.length; op++) {

				let result = ops[op](a, b)
				let output;

				if (result == "error")
					output = CABF("missingno")
				else if (result < 0)
					continue
				else if (result > 9)
					continue
				else if (result % 1 != 0)
					continue
				else
					output = CABF(nums[result])

				event.custom({
					"type": "create:mechanical_crafting",
					"pattern": [
						"AOB"
					],
					"key": {
						"A": {
							"item": CABF(nums[a])
						},
						"O": {
							"item": CABF(opNames[op])
						},
						"B": {
							"item": CABF(nums[b])
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
			{ "name": asIdentifier("number_0"), "amount": alloyAmount },
			{ "name": asIdentifier("number_1"), "amount": alloyAmount },
			{ "name": asIdentifier("number_2"), "amount": alloyAmount },
			{ "name": asIdentifier("number_3"), "amount": alloyAmount },
			{ "name": asIdentifier("number_4"), "amount": alloyAmount },
			{ "name": asIdentifier("number_5"), "amount": alloyAmount },
			{ "name": asIdentifier("number_6"), "amount": alloyAmount },
			{ "name": asIdentifier("number_7"), "amount": alloyAmount },
			{ "name": asIdentifier("number_8"), "amount": alloyAmount },
			{ "name": asIdentifier("number_9"), "amount": alloyAmount }
		],
		"result": {
			"fluid": asIdentifier("matrix"),
			"amount": outAmount
		},
		"temperature": 200
	})

	meltOrCrucible(CABF("calculation_mechanism"), CABF("raw_logic"), 2430)
	meltOrCrucible(CABF("zero"), CABF("number_0"), 81)
	meltOrCrucible(CABF("one"), CABF("number_1"), 81)
	meltOrCrucible(CABF("two"), CABF("number_2"), 81)
	meltOrCrucible(CABF("three"), CABF("number_3"), 81)
	meltOrCrucible(CABF("four"), CABF("number_4"), 81)
	meltOrCrucible(CABF("five"), CABF("number_5"), 81)
	meltOrCrucible(CABF("six"), CABF("number_6"), 81)
	meltOrCrucible(CABF("seven"), CABF("number_7"), 81)
	meltOrCrucible(CABF("eight"), CABF("number_8"), 81)
	meltOrCrucible(CABF("nine"), CABF("number_9"), 81)

	event.custom({
		"type": "tconstruct:casting_basin",
		"fluid": {
			"name": asIdentifier("matrix"),
			"amount": 81000
		},
		"result": Item.of(CABF("computation_matrix")).toResultJson(),
		"cooling_time": 20
	})
})