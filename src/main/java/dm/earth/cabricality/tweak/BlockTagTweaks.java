package dm.earth.cabricality.tweak;

import com.dm.earth.tags_binder.api.LoadTagsCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static dm.earth.cabricality.ModEntry.PROMENADE;
import static dm.earth.cabricality.ModEntry.TERRESTRIA;

public class BlockTagTweaks implements LoadTagsCallback<Block> {

	public static void load() {
		LoadTagsCallback.BLOCK.register(new BlockTagTweaks());
	}

	@Override
	public void onTagsLoad(TagHandler<Block> handler) {
		// Promenade
		compatLeaves(handler, PROMENADE.asBlock("autumn_birch_leaves"), Blocks.BIRCH_LOG);
		compatLeaves(handler, PROMENADE.asBlock("autumn_oak_leaves"), Blocks.OAK_LOG);
		compatLeaves(handler, PROMENADE.asBlock("pink_cherry_oak_leaves"), PROMENADE.asBlock("cherry_oak_log"));
		compatLeaves(handler, PROMENADE.asBlock("white_cherry_oak_leaves"), PROMENADE.asBlock("cherry_oak_log"));
		compatLeaves(handler, PROMENADE.asBlock("palm_leaves"), PROMENADE.asBlock("palm_log"));
		// Terrestria
		compatLeaves(handler, TERRESTRIA.asBlock("redwood_leaves"), TERRESTRIA.asBlock("redwood_log"),
				TERRESTRIA.asBlock("redwood_quarter_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("hemlock_leaves"), TERRESTRIA.asBlock("hemlock_log"),
				TERRESTRIA.asBlock("hemlock_quarter_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("rubber_leaves"), TERRESTRIA.asBlock("rubber_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("cypress_leaves"), TERRESTRIA.asBlock("cypress_log"),
				TERRESTRIA.asBlock("cypress_quarter_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("willow_leaves"), TERRESTRIA.asBlock("willow_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("japanese_maple_leaves"), TERRESTRIA.asBlock("japanese_maple_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("rainbow_eucalyptus_leaves"), TERRESTRIA.asBlock("rainbow_eucalyptus_log"),
				TERRESTRIA.asBlock("rainbow_eucalyptus_quarter_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("sakura_leaves"), TERRESTRIA.asBlock("sakura_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("yucca_palm_leaves"), TERRESTRIA.asBlock("yucca_palm_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("japanese_maple_shrub_leaves"), TERRESTRIA.asBlock("japanese_maple_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("dark_japanese_maple_leaves"), TERRESTRIA.asBlock("japanese_maple_log"));
		compatLeaves(handler, TERRESTRIA.asBlock("jungle_palm_leaves"), Blocks.JUNGLE_LOG);
	}

	private void compatLeaves(TagHandler<Block> handler, Block leaves, Block... logs) {
		Identifier leaf = Registries.BLOCK.getId(leaves);
		handler.register(new Identifier(leaf.getNamespace(), "leaves_groups/" + leaf.getPath()), logs);
	}

}
