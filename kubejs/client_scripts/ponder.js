onEvent("ponder.registry", (event) => {
    event.create("indrev:laser_emitter_mk4")
        .scene("alchemy_setup", "The Alchemy Setup", (scene, util) => {
            scene.configureBasePlate(0, 0, 5);
            scene.showBasePlate();
            scene.idle(10);

            scene.world.setBlocks([4, 1, 2], "indrev:laser_emitter_mk4", false);
            scene.world.modifyBlocks([4, 1, 2], (curState) => curState.with("facing", "west").with("powered", "false"), false);
            scene.world.showSection([4, 1, 2], Facing.WEST);
            scene.idle(10);

            scene.world.setBlocks([4, 2, 2], "kibe:fluid_hopper", false);
            scene.world.modifyBlocks([4, 2, 2], (curState) => curState.with("facing", "down").with("enabled", "true"), false);
            scene.world.showSection([4, 2, 2], Facing.WEST);
            scene.idle(10);

            scene.text(50, "Start with a Laser Emitter, and attach a fluid hopper on top")
                .pointAt(util.vector.topOf(4, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(60);

            scene.world.setBlocks([4, 3, 2], "kubejs:powered_water", false);
            scene.world.modifyBlocks([4, 3, 2], (curState) => curState.with("falling", "false"), false);
            scene.world.showSection([4, 3, 2], Facing.DOWN);

            scene.text(50, "Place powered water above the hopper")
                .pointAt(util.vector.centerOf(4, 3, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(60);

            scene.addKeyframe();
            scene.showControls(50, [4, 3, 2], "right").leftClick();
            scene.idle(20);
            scene.particles.simple(5, "explosion", [3, 3, 2]).density(3).motion([0, 0, 0]).area([-5, 3, 2]).lifetime(15);
            scene.world.modifyBlocks([0, 0, 0], "minecraft:barrier");
            scene.world.modifyBlocks([0, 0, 1], "minecraft:barrier");
            scene.world.modifyBlocks([0, 0, 2], "minecraft:barrier");
            scene.world.modifyBlocks([0, 0, 3], "minecraft:barrier");
            scene.world.modifyBlocks([1, 0, 1], "minecraft:barrier");
            scene.world.modifyBlocks([1, 0, 2], "minecraft:barrier");
            scene.world.modifyBlocks([1, 0, 3], "minecraft:barrier");
            scene.world.modifyBlocks([2, 0, 1], "minecraft:barrier");
            scene.world.modifyBlocks([2, 0, 2], "minecraft:barrier");
            scene.idle(40);

            scene.text(50, "The explosion can break blocks but has no damage")
                .pointAt(util.vector.centerOf(2, 2, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(60);

            scene.showControls(70, [4, 3, 2], "right").leftClick();
            scene.idle(20);
            scene.particles.simple(5, "explosion", [3, 3, 2]).density(3).motion([0, 0, 0]).area([-5, 3, 2]).lifetime(15);
            scene.world.modifyBlocks([0, 0, 4], "minecraft:barrier");
            scene.world.modifyBlocks([1, 0, 0], "minecraft:barrier");
            scene.idle(40);
            scene.particles.simple(5, "explosion", [3, 3, 2]).density(3).motion([0, 0, 0]).area([-5, 3, 2]).lifetime(15);
            scene.world.modifyBlocks([1, 0, 4], "minecraft:barrier");
            scene.idle(2);
            scene.world.replaceBlocks([4, 3, 2], "minecraft:water", true);
            scene.world.modifyBlocks([4, 3, 2], (curState) => curState.with("falling", "false"), false);
            scene.idle(60);

            scene.text(50, "Powered water has a 50% chance of becoming water")
                .pointAt(util.vector.centerOf(4, 3, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(60);


            scene.world.hideSection([4, 3, 2], Facing.EAST);
            scene.world.modifyBlocks([0, 0, 0, 4, 0, 4], "minecraft:obsidian");
            scene.idle(35);
            scene.world.setBlocks([4, 3, 2], "kubejs:powered_water", true);
            scene.world.modifyBlocks([4, 3, 2], (curState) => curState.with("falling", "false"), false);
            scene.world.showSection([4, 3, 2], Facing.DOWN);

            scene.addKeyframe();
            scene.world.setBlocks([4, 1, 0], "indrev:laser_emitter_mk4", false);
            scene.world.modifyBlocks([4, 1, 0], (curState) => curState.with("facing", "west").with("powered", "false"), false);
            scene.world.showSection([4, 1, 0], Facing.WEST);
            scene.world.setBlocks([4, 2, 0], "kibe:fluid_hopper", false);
            scene.world.modifyBlocks([4, 2, 0], (curState) => curState.with("facing", "down").with("enabled", "true"), false);
            scene.world.showSection([4, 2, 0], Facing.WEST);
            scene.world.setBlocks([4, 3, 0], "kubejs:powered_water", false);
            scene.world.modifyBlocks([4, 3, 0], (curState) => curState.with("falling", "false"), false);
            scene.world.showSection([4, 3, 0], Facing.DOWN);

            scene.world.setBlocks([4, 1, 4], "indrev:laser_emitter_mk4", false);
            scene.world.modifyBlocks([4, 1, 4], (curState) => curState.with("facing", "west").with("powered", "false"), false);
            scene.world.showSection([4, 1, 4], Facing.WEST);
            scene.world.setBlocks([4, 2, 4], "kibe:fluid_hopper", false);
            scene.world.modifyBlocks([4, 2, 4], (curState) => curState.with("facing", "down").with("enabled", "true"), false);
            scene.world.showSection([4, 2, 4], Facing.WEST);
            scene.world.setBlocks([4, 3, 4], "kubejs:powered_water", false);
            scene.world.modifyBlocks([4, 3, 4], (curState) => curState.with("falling", "false"), false);
            scene.world.showSection([4, 3, 4], Facing.DOWN);

            scene.world.setBlocks([0, 1, 0], "minecraft:basalt", false);
            scene.world.setBlocks([0, 1, 2], "minecraft:snow_block", false);
            scene.world.setBlocks([0, 1, 4], "minecraft:water", false);
            scene.world.showSection([0, 1, 0, 0, 1, 4], Facing.DOWN);
            scene.text(50, "Here are the things that you can make with the laser (in practice you should place them at least 6 blocks far from the laser)")
                .pointAt(util.vector.centerOf(0, 2, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(60);

            scene.particles.simple(5, "explosion", [3, 3, 0]).density(3).motion([0, 0, 0]).area([-5, 3, 0]).lifetime(15);
            scene.particles.simple(5, "explosion", [3, 3, 2]).density(3).motion([0, 0, 0]).area([-5, 3, 2]).lifetime(15);
            scene.particles.simple(5, "explosion", [3, 3, 4]).density(3).motion([0, 0, 0]).area([-5, 3, 4]).lifetime(15);
            scene.world.modifyBlocks([0, 1, 4], "kubejs:powered_water", true);
            scene.world.modifyBlocks([0, 1, 4], (curState) => curState.with("falling", "false"), false);
            scene.world.modifyBlocks([0, 1, 0], "minecraft:air");
            scene.world.modifyBlocks([0, 1, 2], "minecraft:air");
            const this_is_a_shard = scene.world.createItemEntity([0, 2, 4], util.vector.of(0, 0, 0), "kubejs:blizz_cube");
            const this_is_another_shard = scene.world.createItemEntity([0, 2, 0], util.vector.of(0, 0, 0), "kubejs:basalz_shard");
            scene.idle(40);

            scene.text(70, "The basalts turned into basalt shards, the snow turned into blizz cube and the water turned into powered water")
                .pointAt(util.vector.centerOf(0, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(80);

            scene.world.modifyEntity(this_is_a_shard, (e) => { e.kill(); });
            scene.world.modifyEntity(this_is_another_shard, (e) => { e.kill(); });

            scene.world.hideSection([4, 1, 0, 4, 3, 0], Facing.EAST);
            scene.world.hideSection([4, 1, 4, 4, 3, 4], Facing.EAST);
            scene.world.modifyBlocks([0, 1, 4], "minecraft:air");
            scene.world.hideSection([0, 1, 0, 4, 1, 0], Facing.EAST);
            scene.world.hideSection([0, 1, 2, 3, 1, 2], Facing.EAST);
            scene.idle(60);

            scene.addKeyframe();
            scene.text(80, "If you want to mess around with alchemy, you need to place the substrates in front of the laser")
                .pointAt(util.vector.centerOf(0, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.world.setBlocks([4, 1, 0], "kubejs:substrate_gold", false);
            scene.world.setBlocks([3, 1, 0], "kubejs:substrate_lead", false);
            scene.world.setBlocks([2, 1, 0], "kubejs:substrate_zinc", false);
            scene.world.setBlocks([1, 1, 0], "kubejs:substrate_copper", false);
            scene.world.setBlocks([0, 1, 0], "kubejs:substrate_iron", false);
            scene.world.setBlocks([3, 1, 2], "kubejs:substrate_gold", false);
            scene.world.setBlocks([2, 1, 2], "kubejs:substrate_zinc", false);
            scene.world.setBlocks([1, 1, 2], "kubejs:substrate_copper", false);
            scene.world.setBlocks([0, 1, 2], "kubejs:substrate_iron", false);
            scene.world.showSection([0, 1, 0, 4, 1, 0], Facing.DOWN);
            scene.idle(90);

            scene.text(80, "You should place four at a time, and make sure that all 4 are of the same kind. The recipe is random for each map.")
                .pointAt(util.vector.centerOf(0, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(90);

            scene.text(80, "For example, in order to make the metal substrate, we should try gold, lead, zinc, copper and iron substrate")
                .pointAt(util.vector.centerOf(0, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(90);

            scene.text(80, "There're five of them, so we should only take out one in order to do the conversion")
                .pointAt(util.vector.centerOf(0, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(90);

            scene.text(80, "Let's say... lead (in practice you should place them at least 6 blocks far from the laser)")
                .pointAt(util.vector.centerOf(0, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.world.showSection([0, 1, 2, 3, 1, 2], Facing.DOWN);
            scene.idle(90);

            scene.text(60, "And then we fire up the laser...")
                .pointAt(util.vector.centerOf(0, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.idle(70);

            scene.particles.simple(5, "explosion", [3, 3, 2]).density(3).motion([0, 0, 0]).area([-5, 3, 2]).lifetime(15);
            scene.world.setBlocks([0, 1, 2, 3, 1, 2], "minecraft:air", true);
            scene.idle(60);

            scene.text(80, "Hmm... That didn't work out, and the substrates are gone, too.")
                .pointAt(util.vector.centerOf(0, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.world.hideSection([0, 1, 2, 3, 1, 2], Facing.EAST);
            scene.idle(90);

            scene.text(60, "Let's try taking out iron this time")
                .pointAt(util.vector.centerOf(0, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.world.setBlocks([3, 1, 2], "kubejs:substrate_gold", false);
            scene.world.setBlocks([2, 1, 2], "kubejs:substrate_lead", false);
            scene.world.setBlocks([1, 1, 2], "kubejs:substrate_zinc", false);
            scene.world.setBlocks([0, 1, 2], "kubejs:substrate_copper", false);
            scene.world.showSection([0, 1, 2, 3, 1, 2], Facing.DOWN);
            scene.idle(70);

            scene.addKeyframe();
            scene.world.showSection([0, 1, 2, 3, 1, 2], Facing.DOWN);
            scene.particles.simple(5, "explosion", [3, 3, 2]).density(3).motion([0, 0, 0]).area([-5, 3, 2]).lifetime(15);
            scene.world.setBlocks([0, 1, 2, 3, 1, 2], "minecraft:air", true);
            const woc_goldenlegend = scene.world.createItemEntity([4, 3, 2], util.vector.of(-0.02, 0.3, 0), "kubejs:substrate_metal");
            scene.idle(60);

            scene.text(80, "And we got it! Once you found out the correct recipe, the product substrate will appear in the powered water")
                .pointAt(util.vector.centerOf(3, 1, 2))
                .colored(PonderPalette.WHITE)
                .placeNearTarget();
            scene.world.hideSection([0, 1, 2, 3, 1, 2], Facing.EAST);
            scene.idle(90);
        })
})
