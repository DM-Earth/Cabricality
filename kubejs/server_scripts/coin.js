function playsound_coin(server, player, type) {
	if (type == 'silver') {
		server.runCommandSilent(`playsound minecraft:block.amethyst_cluster.break ambient @a ${player.x} ${player.y} ${player.z}`)
		server.runCommandSilent(`playsound minecraft:block.anvil.place ambient @a ${player.x} ${player.y} ${player.z} 0.7 1.7`)
		server.runCommandSilent(`playsound ad_astra:passing_spaceship ambient @a ${player.x} ${player.y} ${player.z} 0.3`)
	}

	if (type == 'gold') {
		server.runCommandSilent(`playsound minecraft:block.amethyst_block.break ambient @a ${player.x} ${player.y} ${player.z}`)
		server.runCommandSilent(`playsound minecraft:block.lodestone.place ambient @a ${player.x} ${player.y} ${player.z}`)
		server.runCommandSilent(`playsound ad_astra:passing_spaceship ambient @a ${player.x} ${player.y} ${player.z} 0.3`)
	}
}

var coinCooling = [];

onEvent('item.right_click', event => {
	let player = event.getEntity()

	if (event.getItem() == 'cabricality:silver_coin'
	&& !coinCooling.includes(player.toString())) {
		coinCooling.push(player.toString())

		let side = Math.round(Math.random())
		let coin = ``
		let message = ``

		if (side == 1) {
			coin = `cabricality:silver_coin_top`
			message = `§8[ §f§l▲§8 ]`
		}
		else {
			coin = `cabricality:silver_coin_bottom`
			message = `§8[ §7§l▼§8 ]`

		}

		Minecraft.getInstance().gameRenderer.displayItemActivation(coin)
		playsound_coin(event.server, player, 'silver')

		event.server.scheduleInTicks(12, event.server, () => {
			Minecraft.getInstance().gui.getChat().addMessage(Text.translate(`event.cabricality.coin_flip`, player.getName(), event.getItem().getName()).getString() + message)
			event.server.scheduleInTicks(29, event.server, () => {
				coinCooling = coinCooling.filter(function(item) { return item != player.toString() })
			})
		})
	}

	if (event.getItem() == 'cabricality:gold_coin'
	&& !coinCooling.includes(player.toString())) {
		coinCooling.push(player.toString())

		let side = Math.round(Math.random())
		let coin = ``
		let message = ``

		if (side == 1) {
			coin = `cabricality:gold_coin_top`
			message = `§8[ §e§l▲§8 ]`
		}
		else {
			coin = `cabricality:gold_coin_bottom`
			message = `§8[ §6§l▼§8 ]`
		}

		Minecraft.getInstance().gameRenderer.displayItemActivation(coin)
		playsound_coin(event.server, player, 'gold')

		event.server.scheduleInTicks(12, event.server, () => {
			Minecraft.getInstance().gui.getChat().addMessage(Text.translate(`event.cabricality.coin_flip`, player.getName(), event.getItem().getName()).getString() + message)
			event.server.scheduleInTicks(29, event.server, () => {
				coinCooling = coinCooling.filter(function(item) { return item != player.toString() })
			})
		})
	}
})