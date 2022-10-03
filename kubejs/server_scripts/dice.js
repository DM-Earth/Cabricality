function playsound_dice(server, player) {
	server.runCommandSilent(`playsound minecraft:entity.drowned.shoot ambient @a ${player.x} ${player.y} ${player.z}`)
	server.runCommandSilent(`playsound minecraft:block.anvil.place ambient @a ${player.x} ${player.y} ${player.z} 0.7 1.7`)
	server.runCommandSilent(`playsound ad_astra:passing_spaceship ambient @a ${player.x} ${player.y} ${player.z} 0.3`)
}

var diceCooling = []

var coins = ['cabricality:silver_coin', 'cabricality:gold_coin']

onEvent('block.right_click', event => {
	let player = event.getEntity()

	if (event.getBlock() == 'cabricality:dice') {
		event.cancel()
		if (!diceCooling.includes(player.toString())
		&& !coins.includes(player.getMainHandItem().toString())) {
			diceCooling.push(player.toString())

			let serial = Math.floor(Math.random() * 6) + 1

			Minecraft.getInstance().gameRenderer.displayItemActivation(`cabricality:dice_${serial}`) 
			playsound_dice(event.server, player)

			event.server.scheduleInTicks(12, event.server, () => {
				Minecraft.getInstance().gui.getChat().addMessage(Text.translate(`event.cabricality.dice_roll`, player.getName(), event.getBlock().getItem().getName()).getString() + `§8[ §5§l${serial}§8 ]`)
				event.server.scheduleInTicks(35, event.server, () => {
					diceCooling = diceCooling.filter(function(item) { return item != player.toString() })
				})
			})
		}
	}
})