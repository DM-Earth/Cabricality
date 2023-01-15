package com.dm.earth.cabricality.tweak;

import static com.dm.earth.cabricality.ModEntry.C;
import com.dm.earth.cabricality.content.entries.CabfItemTags;
import com.dm.earth.cabricality.tweak.cutting.WoodCuttingEntry;
import com.dm.earth.tags_binder.api.LoadTagsCallback;
import com.dm.earth.tags_binder.api.ResourceConditionCheckTagCallback;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemTagTweaks
		implements LoadTagsCallback<Item>, ResourceConditionCheckTagCallback<Item> {
	private static final ItemTagTweaks INSTANCE = new ItemTagTweaks();
	private static final String[] COMPRESSED_TO_PLATE_CONVERSION = {"desh", "ostrum", "calorite"};

	public static void load() {
		LoadTagsCallback.ITEM.register(INSTANCE);
		ResourceConditionCheckTagCallback.ITEM.register(INSTANCE);
	}

	@Override
	public ActionResult apply(TagKey<Item> arg0) {
		Identifier rid = arg0.id();
		if (!rid.getNamespace().equals("c"))
			return ActionResult.PASS;
		String id = rid.getPath();
		if (id.startsWith("plates/")) {
			String mat = id.replaceAll("plates/", "");
			for (String m : COMPRESSED_TO_PLATE_CONVERSION)
				if (m.equals(mat))
					return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}

	@Override
	public void load(TagHandler<Item> handler) {
		for (WoodCuttingEntry entry : WoodCuttingEntry.values()) {
			if (entry.isStrippedLogExist())
				handler.register(CabfItemTags.STRIPPED_LOGS,
						Registry.ITEM.get(entry.getStrippedLogId()));
			if (entry.isStrippedWoodExist())
				handler.register(CabfItemTags.STRIPPED_WOODS,
						Registry.ITEM.get(entry.getStrippedWoodId()));
		}

		for (String mat : COMPRESSED_TO_PLATE_CONVERSION) {
			Item[] items = handler.get(C.id("compressed_" + mat)).toArray(new Item[0]);
			handler.register(C.id(mat + "_plates"), items);
			handler.register(C.id("plates", mat), items);
			handler.remove(C.id("compressed_" + mat));
		}
	}
}
