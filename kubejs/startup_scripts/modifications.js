onEvent("item.modification", event => {
	let ball_colors = ["red", "yellow", "green", "blue", "magenta", "black"]
	ball_colors.forEach(element => {
		event.modify("ae2:" + element + "_paint_ball", item => {
			item.maxStackSize = 1
		})
	});

    let tool_materials = [
        //modid, material, miningl, dura, minings, attack, enchant
        ["indrev", "steel", 3, 750, 7, 2.5, 13],
        ["indrev", "bronze", 2, 300, 5, 1.5, 14]
    ]

    tool_materials.forEach(e => {
        let get = (aftfix) => { return asIdentifier(e[1] + "_" + aftfix, e[0]) }
        let setCommonTier = (tierOptions) => {
            tierOptions.setLevel(e[2])
            tierOptions.setSpeed(e[4])
            tierOptions.setAttackDamageBonus(e[5])
            tierOptions.setEnchantmentValue(e[6])
        }
    //    let tools = ["hoe", "shovel", "axe", "pickaxe", "sword"]
        let tools = [
            ["sword", 4, 1.6, ]
        ]

        tools.forEach(p => {
            event.modify(get(p), item => {
                item.tier = (tierOptions) => {
                    setCommonTier(tierOptions)
                }
                item.setMaxDamage(e[3])
            })
        })
    })
})
