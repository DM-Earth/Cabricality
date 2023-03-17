package com.dm.earth.cabricality.lib.resource.data.core;

import java.util.Optional;

import com.dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;
import org.jetbrains.annotations.NotNull;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.HolderSet;
import net.minecraft.util.registry.Registry;

public class TaggedItemOutput {

	public final TagKey<Item> tagKey;
	public final int count;

	public TaggedItemOutput(TagKey<Item> tagKey, int count) {
		this.tagKey = tagKey;
		this.count = count;
	}

	public @NotNull ItemStack get() {
		Optional<HolderSet.NamedSet<Item>> set = Registry.ITEM.getTag(this.tagKey);
		if (set.isPresent() && set.get().size() > 0) {
			return ProcessItemOutputCallback.process(new ItemStack(set.get().get(0).value(), this.count));
		}
		return ItemStack.EMPTY;
	}

	public @NotNull JsonElement serialize() {
		JsonObject json = new JsonObject();
		json.addProperty("tag", this.tagKey.id().toString());
		if (count != 1) {
			json.addProperty("count", count);
		}
		return json;
	}
}
