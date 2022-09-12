let alchemy_setup = (scene, util) => {
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

    scene.world.setBlocks([4, 3, 2], asIdentifier("powered_water"), false);
    scene.world.modifyBlocks([4, 3, 2], (curState) => curState.with("falling", "false"), false);
    scene.world.showSection([4, 3, 2], Facing.DOWN);

    scene.text(50, "Place powered water above the hopper")
        .pointAt(util.vector.centerOf(4, 3, 2))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.idle(60);

    scene.addKeyframe();
    scene.showControls(50, [4, 3, 2], "right").rightClick();
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

    scene.showControls(70, [4, 3, 2], "right").rightClick();
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
    scene.world.setBlocks([4, 3, 2], asIdentifier("powered_water"), true);
    scene.world.modifyBlocks([4, 3, 2], (curState) => curState.with("falling", "false"), false);
    scene.world.showSection([4, 3, 2], Facing.DOWN);

    scene.addKeyframe();
    scene.world.setBlocks([4, 1, 0], "indrev:laser_emitter_mk4", false);
    scene.world.modifyBlocks([4, 1, 0], (curState) => curState.with("facing", "west").with("powered", "false"), false);
    scene.world.showSection([4, 1, 0], Facing.WEST);
    scene.world.setBlocks([4, 2, 0], "kibe:fluid_hopper", false);
    scene.world.modifyBlocks([4, 2, 0], (curState) => curState.with("facing", "down").with("enabled", "true"), false);
    scene.world.showSection([4, 2, 0], Facing.WEST);
    scene.world.setBlocks([4, 3, 0], asIdentifier("powered_water"), false);
    scene.world.modifyBlocks([4, 3, 0], (curState) => curState.with("falling", "false"), false);
    scene.world.showSection([4, 3, 0], Facing.DOWN);

    scene.world.setBlocks([4, 1, 4], "indrev:laser_emitter_mk4", false);
    scene.world.modifyBlocks([4, 1, 4], (curState) => curState.with("facing", "west").with("powered", "false"), false);
    scene.world.showSection([4, 1, 4], Facing.WEST);
    scene.world.setBlocks([4, 2, 4], "kibe:fluid_hopper", false);
    scene.world.modifyBlocks([4, 2, 4], (curState) => curState.with("facing", "down").with("enabled", "true"), false);
    scene.world.showSection([4, 2, 4], Facing.WEST);
    scene.world.setBlocks([4, 3, 4], asIdentifier("powered_water"), false);
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
    scene.world.modifyBlocks([0, 1, 4], asIdentifier("powered_water"), true);
    scene.world.modifyBlocks([0, 1, 4], (curState) => curState.with("falling", "false"), false);
    scene.world.modifyBlocks([0, 1, 0], "minecraft:air");
    scene.world.modifyBlocks([0, 1, 2], "minecraft:air");
    const this_is_a_shard = scene.world.createItemEntity([0, 2, 4], util.vector.of(0, 0, 0), asIdentifier("blizz_cube"));
    const this_is_another_shard = scene.world.createItemEntity([0, 2, 0], util.vector.of(0, 0, 0), asIdentifier("basalz_shard"));
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
    scene.world.setBlocks([4, 1, 0], asIdentifier("substrate_gold"), false);
    scene.world.setBlocks([3, 1, 0], asIdentifier("substrate_lead"), false);
    scene.world.setBlocks([2, 1, 0], asIdentifier("substrate_zinc"), false);
    scene.world.setBlocks([1, 1, 0], asIdentifier("substrate_copper"), false);
    scene.world.setBlocks([0, 1, 0], asIdentifier("substrate_iron"), false);
    scene.world.setBlocks([3, 1, 2], asIdentifier("substrate_gold"), false);
    scene.world.setBlocks([2, 1, 2], asIdentifier("substrate_zinc"), false);
    scene.world.setBlocks([1, 1, 2], asIdentifier("substrate_copper"), false);
    scene.world.setBlocks([0, 1, 2], asIdentifier("substrate_iron"), false);
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
    scene.world.setBlocks([3, 1, 2], asIdentifier("substrate_gold"), false);
    scene.world.setBlocks([2, 1, 2], asIdentifier("substrate_lead"), false);
    scene.world.setBlocks([1, 1, 2], asIdentifier("substrate_zinc"), false);
    scene.world.setBlocks([0, 1, 2], asIdentifier("substrate_copper"), false);
    scene.world.showSection([0, 1, 2, 3, 1, 2], Facing.DOWN);
    scene.idle(70);

    scene.addKeyframe();
    scene.world.showSection([0, 1, 2, 3, 1, 2], Facing.DOWN);
    scene.particles.simple(5, "explosion", [3, 3, 2]).density(3).motion([0, 0, 0]).area([-5, 3, 2]).lifetime(15);
    scene.world.setBlocks([0, 1, 2, 3, 1, 2], "minecraft:air", true);
    const woc_goldenlegend = scene.world.createItemEntity([4, 3, 2], util.vector.of(-0.02, 0.3, 0), asIdentifier("substrate_metal"));
    scene.idle(60);

    scene.text(80, "And we got it! Once you found out the correct recipe, the product substrate will appear in the powered water")
        .pointAt(util.vector.centerOf(3, 1, 2))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.world.hideSection([0, 1, 2, 3, 1, 2], Facing.EAST);
    scene.idle(90);
};

let rocket_introduction = (scene, util) => {
    scene.configureBasePlate(0, 0, 7);
    scene.showBasePlate();
    scene.setSceneOffsetY(-3);
    scene.world.setBlocks([3, 1, 3], "indrev:controller", false);
    scene.idle(10);

    scene.world.showSection([3, 1, 3, 3, 1, 3], Facing.DOWN);
    scene.text(60, "Congratulations! You're just one step away from going to the Moon!")
        .pointAt(util.vector.centerOf(3, 1, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.idle(70);

    scene.world.setBlocks([3, 1, 3], "minecraft:air", true);
    scene.text(60, "But before that happens, you'll need to do some preparations")
        .pointAt(util.vector.centerOf(3, 1, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.idle(20);
    scene.world.setBlocks([3, 1, 3], "minecraft:air", true);
    scene.idle(50);

    scene.text(60, "For starters, you'll need rocket engines and rocket tanks")
        .pointAt(util.vector.topOf(1, 2, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    const engine = scene.world.createItemEntity([3, 1, 3], util.vector.of(-0.1, 1, 0.2), "ad_astra:steel_engine");
    const tank = scene.world.createItemEntity([3, 1, 3], util.vector.of(0.1, 1, 0), "ad_astra:steel_tank");
    scene.idle(70);

    scene.text(60, "Like real rockets, these things are huge, so you'll need multi-block contraptions to make them")
        .pointAt(util.vector.topOf(1, 2, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.idle(70);

    scene.addKeyframe();
    scene.idle(10);

    const t1rocket = scene.world.createEntity("ad_astra:tier_1_rocket", [0, 12, 3]);
    const t2rocket = scene.world.createEntity("ad_astra:tier_2_rocket", [2, 12, 3]);
    const t3rocket = scene.world.createEntity("ad_astra:tier_3_rocket", [4, 12, 3]);
    const t4rocket = scene.world.createEntity("ad_astra:tier_4_rocket", [6, 12, 3]);
    scene.text(60, "There're 4 tiers of rockets, each made up of steel, desh, ostrum, and calorite")
        .pointAt(util.vector.topOf(1, 2, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    for (let i = 0; i < 60; i++) {
        scene.world.modifyEntity(t1rocket, (e) => { e.setPos(e.getX(), (e.getY() - 0.24) < 1 ? 1 : e.getY() - 0.24, e.getZ()); });
        scene.world.modifyEntity(t2rocket, (e) => { e.setPos(e.getX(), (e.getY() - 0.27) < 1 ? 1 : e.getY() - 0.27, e.getZ()); });
        scene.world.modifyEntity(t3rocket, (e) => { e.setPos(e.getX(), (e.getY() - 0.32) < 1 ? 1 : e.getY() - 0.32, e.getZ()); });
        scene.world.modifyEntity(t4rocket, (e) => { e.setPos(e.getX(), (e.getY() - 0.2) < 1 ? 1 : e.getY() - 0.21, e.getZ()); });
        scene.idle(1);
    }
    scene.world.modifyEntity(engine, (e) => { e.kill(); });
    scene.world.modifyEntity(tank, (e) => { e.kill(); });
    scene.idle(20);

    scene.text(60, "Higher tiers require resources from the planets lower tiers could go to")
        .pointAt(util.vector.topOf(1, 2, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.idle(70);

    scene.text(60, "We'll start with tier 1, which is made up of steel")
        .pointAt(util.vector.topOf(1, 2, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.idle(20);
    scene.world.modifyEntity(t2rocket, (e) => { e.kill(); });
    scene.world.modifyEntity(t3rocket, (e) => { e.kill(); });
    scene.world.modifyEntity(t4rocket, (e) => { e.kill(); });
    for (let i = 0; i < 20; i++) {
        scene.world.modifyEntity(t1rocket, (e) => { e.setPos(e.getX() + 3 / 20, 1, 3); });
        scene.idle(1);
    }
    scene.idle(40);
};

let rocket_engine = (scene, util) => {
    scene.configureBasePlate(0, 0, 7);
    scene.showBasePlate();
    scene.setSceneOffsetY(-3);
    scene.idle(10);

    let layer = 1;
    scene.text(100, "For base, we need iron plates and pillars. That's the same for all engines")
        .pointAt(util.vector.topOf(3, 2, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.idle(50);

    layer = 2;
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.idle(70);

    scene.text(150, "For the layers above that, we'll need to use the material that each tier requires")
        .pointAt(util.vector.topOf(3, 4, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    layer = 3;
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.idle(50);

    layer = 4;
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.idle(50);

    layer = 5;
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.idle(70);

    layer = 6;
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.world.hideSection([3, 6, 3], Facing.UP);
    scene.idle(70);

    scene.addKeyframe();
    scene.text(60, "Finally, we seal the top with the block of the material")
        .pointAt(util.vector.centerOf(3, 6, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.world.setBlocks([3, 6, 3], "ad_astra:steel_block", false);
    scene.world.showSection([3, 6, 3], Facing.DOWN);

    scene.idle(70);

    scene.showControls(30, [3, 8, 4.2], "down").rightClick();
    scene.idle(20);
    scene.world.setBlocks([0, 1, 0, 6, 6, 6], "minecraft:air", false);
    const engine2 = scene.world.createItemEntity([3, 3, 3], util.vector.of(0.01, 0, -0.02), "ad_astra:steel_engine");
    scene.idle(20);
    scene.text(60, "And there you have it, the engine of a rocket!")
        .pointAt(util.vector.centerOf(3, 1, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
};

let rocket_tank = (scene, util) => {
    scene.configureBasePlate(0, 0, 7);
    scene.showBasePlate();
    scene.setSceneOffsetY(-3);
    scene.idle(10);
    scene.text(90, "For rocket tanks, you may want to build it two blocks up from the ground for convenience")
    scene.world.showSection([3, 1, 3, 3, 2, 3], Facing.EAST);
    scene.world.showSection([5, 1, 5], Facing.NORTH);
    scene.idle(5);
    scene.world.showSection([5, 2, 5], Facing.NORTH);
    scene.idle(5);
    scene.world.showSection([5, 3, 5], Facing.NORTH);
    scene.idle(5);
    scene.world.showSection([5, 4, 5], Facing.NORTH);
    scene.idle(85);

    scene.world.hideSection([5, 1, 5, 5, 4, 5], Facing.NORTH);
    scene.addKeyframe();
    scene.text(60, "Start with the block of the material")
        .pointAt(util.vector.topOf(3, 2, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.world.showSection([3, 3, 3], Facing.DOWN);
    scene.idle(70);

    let layer = 3;
    scene.text(100, "Then add plates to its sides")
        .pointAt(util.vector.topOf(3, 2, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.world.setBlocks([5, 1, 5, 5, 4, 5], "minecraft:air", false);
    scene.world.setBlocks([3, 1, 3, 3, 2, 3], "minecraft:air", true);
    scene.world.showSection([3, layer, 2], Facing.DOWN);
    scene.world.showSection([3, layer, 4], Facing.DOWN);
    scene.world.showSection([2, layer, 3], Facing.DOWN);
    scene.world.showSection([4, layer, 3], Facing.DOWN);
    scene.idle(110);

    layer = 4;
    scene.text(200, "Use pillars to build up and add fuel in them")
        .pointAt(util.vector.topOf(3, 4, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.world.showSection([3, layer, 2], Facing.DOWN);
    scene.world.showSection([3, layer, 4], Facing.DOWN);
    scene.world.showSection([2, layer, 3], Facing.DOWN);
    scene.world.showSection([4, layer, 3], Facing.DOWN);
    scene.idle(25);
    scene.world.showSection([3, layer, 3], Facing.DOWN);
    scene.idle(25);

    layer = 5;
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.idle(50);

    layer = 6;
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.idle(50);

    layer = 7;
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.idle(70);

    layer = 8;
    scene.world.showSection([0, layer, 0, 6, layer, 6], Facing.DOWN);
    scene.idle(70);

    layer = 9;
    scene.world.showSection([3, layer, 2], Facing.DOWN);
    scene.world.showSection([3, layer, 4], Facing.DOWN);
    scene.world.showSection([2, layer, 3], Facing.DOWN);
    scene.world.showSection([4, layer, 3], Facing.DOWN);
    scene.idle(70);

    scene.addKeyframe();

    scene.text(60, "Finally, we seal the top with the block of the material")
        .pointAt(util.vector.centerOf(3, 9, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.world.showSection([3, 9, 3], Facing.DOWN);

    scene.idle(70);

    scene.world.hideSection([0, 4, 0, 6, 9, 6], Facing.UP);
    scene.showControls(100, [3, 4.3, 3], "down").rightClick();
    scene.idle(40);
    scene.world.showSection([0, 4, 0, 6, 9, 6], Facing.DOWN);
    scene.idle(40);
    scene.world.setBlocks([0, 1, 0, 6, 9, 6], "minecraft:air", false);
    const tank2 = scene.world.createItemEntity([3, 3, 3], util.vector.of(0.01, 0, -0.02), "ad_astra:steel_tank");
    scene.idle(20);
    scene.text(60, "After right-clicking the base block on the bottom, you'll get a rocket tank")
        .pointAt(util.vector.centerOf(3, 1, 3))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
};

let extractor = (scene, util) => {
    scene.configureBasePlate(0, 0, 5);
    scene.showBasePlate();
    scene.idle(10);

    scene.world.showSection([2, 1, 2, 2, 6, 2], Facing.DOWN);
    scene.world.showSection([0, 4, 0, 4, 6, 4], Facing.DOWN);
    scene.text(60, "In order to extract liquid resin, you need to find some trees")
        .pointAt(util.vector.centerOf(2, 1, 2))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.idle(70);

    scene.world.showSection([2, 2, 1, 2, 1, 1], Facing.DOWN);
    scene.text(60, "Place the extractor next to a log. Make sure that there's no block below the machine")
        .pointAt(util.vector.centerOf(2, 2, 1))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.idle(70);

    scene.showControls(60, [2, 2.6, 1], "left").rightClick();
    scene.idle(40);
    scene.world.setBlocks([2, 1, 1], asIdentifier("resin"), true);
    scene.idle(20);

    scene.text(60, "The liquid resin can be harvested by pipes")
        .pointAt(util.vector.centerOf(2, 2, 1))
        .colored(PonderPalette.WHITE)
        .placeNearTarget();
    scene.world.showSection([1, 1, 1, 0, 2, 1], Facing.EAST);
}

onEvent("ponder.registry", (event) => {
    event.create("indrev:laser_emitter_mk4")
        .scene("alchemy_setup", "The Alchemy Setup", alchemy_setup);
    event.create("indrev:controller")
        .scene("rocket_introduction", "Launching Rockets 101", asIdentifier("rocket_engine"), rocket_introduction)
        .scene("rocket_engine", "Making a Rocket Engine", asIdentifier("rocket_engine"), rocket_engine)
        .scene("rocket_tank", "Making a Rocket Tank", asIdentifier("rocket_tank"), rocket_tank);
    event.create("ad_astra:steel_engine")
        .scene("rocket_engine", "Making a Rocket Engine", asIdentifier("rocket_engine"), rocket_engine);
    event.create("ad_astra:steel_tank")
        .scene("rocket_tank", "Making a Rocket Tank", asIdentifier("rocket_tank"), rocket_tank);
    event.create(asIdentifier("extractor_machine")
        .scene("extractor", "Making an Extractor", asIdentifier("extractor")), extractor);
})