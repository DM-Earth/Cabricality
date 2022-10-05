onEvent('item.right_click', event => {
	let player = event.getPlayer()
	let messagePrefix = Text.translate(`event.cabricality.coin_flip`, player.getName(), event.getItem().getName()).getString()

	if (event.getItem() == 'cabricality:silver_coin'
	&& randomEventCooling == 0) {
		randomEventCooling = coinCoolingConst

		let side = Math.round(Math.random())
		let coinSide = `top`
		let messagePostfix = `§8[ §f§l▲§8 ]`

		if (side == 0) {
			coinSide = `bottom`
			messagePostfix = `§8[ §7§l▼§8 ]`
		}

		Minecraft.getInstance().gameRenderer.displayItemActivation(`cabricality:silver_coin_${coinSide}`)
		randomEventMessage = messagePrefix + messagePostfix
	}

	if (event.getItem() == 'cabricality:gold_coin'
	&& randomEventCooling == 0) {
		randomEventCooling = coinCoolingConst

		let side = Math.round(Math.random())
		let coinSide = `top`
		let messagePostfix = `§8[ §e§l▲§8 ]`

		if (side == 0) {
			coinSide = `bottom`
			messagePostfix = `§8[ §6§l▼§8 ]`
		}

		Minecraft.getInstance().gameRenderer.displayItemActivation(`cabricality:gold_coin_${coinSide}`)
		randomEventMessage = messagePrefix + messagePostfix
	}
})