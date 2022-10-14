onEvent("ftbquests.custom_reward", event => {
    let reward = event.getReward()
    console.log(reward);
    let id = reward.quest.getCodeString()

    let runCommand = (cmd) => {
        event.server.schedule(10, event.server, () => {
            event.server.runCommandSilent(cmd)
        })
    }

    if (reward.hasTag("reset")) {
        console.log("yes");
        console.log(event.player.name);
        runCommand("ftbquests change_progress " + event.player.name + " reset " + id)
    }

    if (reward.hasTag("bad_omen"))
        runCommand("effect clear " + event.player.name + " minecraft:bad_omen")

    if (reward.hasTag("fortress"))
        event.server.schedule(10, event.server, function(callback) {
            callback.data.runCommand("execute as " + event.player.name + " in minecraft:the_nether run locate fortress")
        })

})