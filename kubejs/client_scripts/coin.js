const coinCoolingConst = 41

var coinCooling = 0
var message = ''

onEvent('client.tick', () => {
	if (coinCooling == coinCoolingConst - 12) {
		Minecraft.getInstance().gui.getChat().addMessage(message)
	}

	if (coinCooling > 0) {
		coinCooling--
	}
})

onEvent('item.right_click', event => {
	let player = event.getPlayer()
	let messagePrefix = Text.translate(`event.cabricality.coin_flip`, player.getName(), event.getItem().getName()).getString()

	if (event.getItem() == 'cabricality:silver_coin'
	&& coinCooling == 0) {
		coinCooling = coinCoolingConst

		let side = Math.round(Math.random())
		let coinSide = `top`
		let messagePostfix = `§8[ §f§l▲§8 ]`

		if (side == 0) {
			coinSide = `bottom`
			messagePostfix = `§8[ §7§l▼§8 ]`
		}

		Minecraft.getInstance().gameRenderer.displayItemActivation(`cabricality:silver_coin_${coinSide}`)

		message = messagePrefix + messagePostfix
	}

	if (event.getItem() == 'cabricality:gold_coin'
	&& coinCooling == 0) {
		coinCooling = coinCoolingConst

		let side = Math.round(Math.random())
		let coinSide = `top`
		let messagePostfix = `§8[ §e§l▲§8 ]`

		if (side == 0) {
			coinSide = `bottom`
			messagePostfix = `§8[ §6§l▼§8 ]`
		}

		Minecraft.getInstance().gameRenderer.displayItemActivation(`cabricality:gold_coin_${coinSide}`)

		message = messagePrefix + messagePostfix
	}
})