function playsound_dice(server, player) {
    server.runCommandSilent(`playsound minecraft:entity.drowned.shoot ambient @a ${player.x} ${player.y} ${player.z}`)
    server.runCommandSilent(`playsound minecraft:block.deepslate.place ambient @a ${player.x} ${player.y} ${player.z}`)
    server.runCommandSilent(`playsound minecraft:block.conduit.activate ambient @a ${player.x} ${player.y} ${player.z} 0.7`)
    server.runCommandSilent(`playsound create:mechanical_press_activation_belt_compounded_1 ambient @a ${player.x} ${player.y} ${player.z} 0.7`)
    server.runCommandSilent(`playsound minecraft:block.amethyst_cluster.hit ambient @a ${player.x} ${player.y} ${player.z} 1 0.7`)
    server.runCommandSilent(`playsound ad_astra:passing_spaceship ambient @a ${player.x} ${player.y} ${player.z} 0.3`)
}

var diceCooling = []

onEvent('block.right_click', event => {
    let player = event.getEntity()

    if (event.getBlock() == 'cabricality:dice' &&
        player.getId() != '9e2faded-cafe-4ec2-c314-dad129ae971d' &&
        player.getMainHandItem() == null) {
        event.cancel()
        if (!diceCooling.includes(player.toString())) {
            diceCooling.push(player.toString())

            let serial = Math.floor(Math.random() * 6) + 1

            Minecraft.getInstance().gameRenderer.displayItemActivation(`cabricality:dice_${serial}`)
            playsound_dice(event.server, player)

            event.server.scheduleInTicks(12, event.server, () => {
                let messagePrefix = Text.translate(`event.cabricality.dice_roll`, player.getName(), event.getBlock().getItem().getName()).getString()
                Minecraft.getInstance().gui.getChat().addMessage(messagePrefix + `§8[ §5§l${serial}§8 ]`)
                event.server.scheduleInTicks(35, event.server, () => {
                    diceCooling = diceCooling.filter(function(item) { return item != player.toString() })
                })
            })
        }
    }
})