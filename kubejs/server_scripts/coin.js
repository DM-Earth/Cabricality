const Minecraft = java('net.minecraft.client.Minecraft')

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

onEvent('item.right_click', event => {
	if (event.getItem() == 'cabricality:silver_coin') {
		var side = Math.round(Math.random())
		var player = event.getEntity()
		var coin = ``
		var message = ``

		if (side == 1) {
			coin = `cabricality:silver_coin_top`
			message = `§8[ §f§l▲§8 ]`
		}
		else {
			coin = `cabricality:silver_coin_bottom`
			message = `§8[ §7§l▼§8 ]`

		}

		Minecraft.getInstance().gameRenderer.displayItemActivation(coin)
		Minecraft.getInstance().gui.getChat().addMessage(Text.translate(`event.cabricality.coin_flip`, player.getName(), event.getItem().getName()).getString() + message)
		playsound_coin(event.server, player, 'silver')
	}

	if (event.getItem() == 'cabricality:gold_coin') {
		var side = Math.round(Math.random())
		var player = event.getEntity()
		var coin = ``
		var message = ``

		if (side == 1) {
			coin = `cabricality:gold_coin_top`
			message = `§8[ §e§l▲§8 ]`
		}
		else {
			coin = `cabricality:gold_coin_bottom`
			message = `§8[ §6§l▼§8 ]`
		}

		Minecraft.getInstance().gameRenderer.displayItemActivation(coin)
		Minecraft.getInstance().gui.getChat().addMessage(Text.translate(`event.cabricality.coin_flip`, player.getName(), event.getItem().getName()).getString() + message)
		playsound_coin(event.server, player, 'gold')
	}
})