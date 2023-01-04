package com.dm.earth.cabricality.tweak;

import com.dm.earth.cabricality.tweak.cutting.WoodCuttingEntry;
import com.dm.earth.tags_binder.api.LoadTagsCallback;
import com.dm.earth.tags_binder.api.ResourceConditionCheckTagCallback;
import com.simibubi.create.AllTags.AllItemTags;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.registry.Registry;

public class ItemTagTweaks implements LoadTagsCallback<Item>, ResourceConditionCheckTagCallback<Item> {
	private static final ItemTagTweaks INSTANCE = new ItemTagTweaks();

	public static void load() {
		LoadTagsCallback.ITEM.register(INSTANCE);
		ResourceConditionCheckTagCallback.ITEM.register(INSTANCE);
	}

	@Override
	public ActionResult apply(TagKey<Item> arg0) {
		return ActionResult.PASS;
	}

	@Override
	public void load(TagHandler<Item> handler) {
		for (WoodCuttingEntry entry : WoodCuttingEntry.values()) {
			if (entry.isStrippedLogExist())
				handler.register(AllItemTags.MODDED_STRIPPED_LOGS.tag, Registry.ITEM.get(entry.getStrippedLogId()));
			if (entry.isStrippedWoodExist())
				handler.register(AllItemTags.MODDED_STRIPPED_WOOD.tag, Registry.ITEM.get(entry.getStrippedWoodId()));
		}
	}
}
