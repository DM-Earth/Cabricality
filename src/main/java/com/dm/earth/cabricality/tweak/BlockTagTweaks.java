package com.dm.earth.cabricality.tweak;

import static com.dm.earth.cabricality.ModEntry.PM;
import static com.dm.earth.cabricality.ModEntry.TRE;

import com.dm.earth.tags_binder.api.LoadTagsCallback;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockTagTweaks implements LoadTagsCallback<Block> {

	public static void load() {
		LoadTagsCallback.BLOCK.register(new BlockTagTweaks());
	}

	@Override
	public void load(TagHandler<Block> handler) {
		// Promenade
		compatLeaves(handler, PM.asBlock("autumn_birch_leaves"), Blocks.BIRCH_LOG);
		compatLeaves(handler, PM.asBlock("autumn_oak_leaves"), Blocks.OAK_LOG);
		compatLeaves(handler, PM.asBlock("pink_cherry_oak_leaves"), PM.asBlock("cherry_oak_log"));
		compatLeaves(handler, PM.asBlock("white_cherry_oak_leaves"), PM.asBlock("cherry_oak_log"));
		compatLeaves(handler, PM.asBlock("palm_leaves"), PM.asBlock("palm_log"));
		// Terrestria
		compatLeaves(handler, TRE.asBlock("redwood_leaves"), TRE.asBlock("redwood_log"),
				TRE.asBlock("redwood_quarter_log"));
		compatLeaves(handler, TRE.asBlock("hemlock_leaves"), TRE.asBlock("hemlock_log"),
				TRE.asBlock("hemlock_quarter_log"));
		compatLeaves(handler, TRE.asBlock("rubber_leaves"), TRE.asBlock("rubber_log"));
		compatLeaves(handler, TRE.asBlock("cypress_leaves"), TRE.asBlock("cypress_log"),
				TRE.asBlock("cypress_quarter_log"));
		compatLeaves(handler, TRE.asBlock("willow_leaves"), TRE.asBlock("willow_log"));
		compatLeaves(handler, TRE.asBlock("japanese_maple_leaves"), TRE.asBlock("japanese_maple_log"));
		compatLeaves(handler, TRE.asBlock("rainbow_eucalyptus_leaves"), TRE.asBlock("rainbow_eucalyptus_log"),
				TRE.asBlock("rainbow_eucalyptus_quarter_log"));
		compatLeaves(handler, TRE.asBlock("sakura_leaves"), TRE.asBlock("sakura_log"));
		compatLeaves(handler, TRE.asBlock("yucca_palm_leaves"), TRE.asBlock("yucca_palm_log"));
		compatLeaves(handler, TRE.asBlock("japanese_maple_shrub_leaves"), TRE.asBlock("japanese_maple_log"));
		compatLeaves(handler, TRE.asBlock("dark_japanese_maple_leaves"), TRE.asBlock("japanese_maple_log"));
		compatLeaves(handler, TRE.asBlock("jungle_palm_leaves"), Blocks.JUNGLE_LOG);
	}

	private void compatLeaves(TagHandler<Block> handler, Block leaves, Block... logs) {
		Identifier leaf = Registry.BLOCK.getId(leaves);
		handler.register(new Identifier(leaf.getNamespace(), "leaves_groups/" + leaf.getPath()), logs);
	}

}
