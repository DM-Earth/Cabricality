onEvent('block.right_click', event => {
	let player = event.getPlayer()
	let messagePrefix = Text.translate(`event.cabricality.dice_roll`, player.getName(), event.getBlock().getItem().getName()).getString()

	if (event.getBlock() == 'cabricality:dice'
	&& player.getMainHandItem() == null
	&& player.getId() != '9e2faded-cafe-4ec2-c314-dad129ae971d') {
		event.cancel

		if (randomEventCooling == 0) {
			randomEventCooling = diceCoolingConst
	
			let serial = Math.floor(Math.random() * 6) + 1
			let messagePostfix = `§8[ §5§l${serial}§8 ]`
	
			Minecraft.getInstance().gameRenderer.displayItemActivation(`cabricality:dice_${serial}`)
			randomEventMessage = messagePrefix + messagePostfix
		}
	}
})