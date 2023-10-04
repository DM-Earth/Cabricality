package dm.earth.cabricality.tweak;

import dm.earth.cabricality.content.entries.CabfItemTags;
import dm.earth.cabricality.tweak.base.TagUnifyEntry;
import dm.earth.cabricality.tweak.cutting.WoodCuttingEntry;
import com.dm.earth.tags_binder.api.LoadTagsCallback;
import com.dm.earth.tags_binder.api.ResourceConditionCheckTagCallback;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

import static dm.earth.cabricality.Mod.Entry.AD_ASTRA;
import static dm.earth.cabricality.Mod.Entry.C;
import static dm.earth.cabricality.Mod.Entry.CREATE;
import static dm.earth.cabricality.Mod.Entry.INDREV;

public class ItemTagTweaks implements LoadTagsCallback<Item>, ResourceConditionCheckTagCallback<Item> {

	private static final ItemTagTweaks INSTANCE = new ItemTagTweaks();
	private static final String[] COMPRESSED_TO_PLATE_CONVERSION = { "desh", "ostrum", "calorite" };

	public static void load() {
		LoadTagsCallback.ITEM.register(INSTANCE);
		ResourceConditionCheckTagCallback.ITEM.register(INSTANCE);
		unifyTags();
	}

	private static void unifyTags() {
		TagUnifyEntry.register(() -> CREATE.asItem("iron_sheet"), "iron_plate");
		TagUnifyEntry.register(() -> AD_ASTRA.asItem("steel_block"), "steel_block");
		TagUnifyEntry.register(() -> AD_ASTRA.asItem("steel_nugget"), "steel_nugget");
		TagUnifyEntry.register(() -> CREATE.asItem("gold_sheet"), "gold_plate");
		TagUnifyEntry.register(() -> CREATE.asItem("copper_sheet"), "copper_plate");
		TagUnifyEntry.register(() -> INDREV.asItem("steel_ingot"), "steel_ingot");
		TagUnifyEntry.register(() -> INDREV.asItem("steel_plate"), "steel_plate", "compressed_steel");
		TagUnifyEntry.register(() -> CREATE.asItem("copper_nugget"), "copper_nugget");
	}

	@Override
	public ActionResult resourceConditionCheckTag(TagKey<Item> arg0) {
		Identifier rid = arg0.id();
		if (!rid.getNamespace().equals("c"))
			return ActionResult.PASS;
		String id = rid.getPath();
		if (id.startsWith("plates/") || id.endsWith("_plates")) {
			String mat = id.replaceAll("plates/", "").replaceAll("_plates", "");
			for (String m : COMPRESSED_TO_PLATE_CONVERSION)
				if (m.equals(mat))
					return ActionResult.SUCCESS;
		}
		if (id.startsWith("ores/")) {
			String mat = id.replaceAll("ores/", "");
			String[] allowList = { "tin", "lead", "nickel" };
			for (String m : allowList)
				if (mat.equals(m))
					return ActionResult.CONSUME;
		}
		return ActionResult.PASS;
	}

	@Override
	public void onTagsLoad(TagHandler<Item> handler) {
		for (WoodCuttingEntry entry : WoodCuttingEntry.values()) {
			if (entry.isStrippedLogExist())
				handler.register(CabfItemTags.STRIPPED_LOGS,
						Registries.ITEM.get(entry.getStrippedLogId()));
			if (entry.isStrippedWoodExist())
				handler.register(CabfItemTags.STRIPPED_WOODS,
						Registries.ITEM.get(entry.getStrippedWoodId()));
		}

		handler.register(C.id("compressed_steel"), INDREV.asItem("steel_plate"));
		handler.register(C.id("desh_plates"), AD_ASTRA.asItem("compressed_desh"));
		handler.register(C.id("ostrum_plates"), AD_ASTRA.asItem("compressed_ostrum"));
		handler.register(C.id("calorite_plates"), AD_ASTRA.asItem("compressed_calorite"));
	}

}
