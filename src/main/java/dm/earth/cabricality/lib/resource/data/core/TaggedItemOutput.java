package dm.earth.cabricality.lib.resource.data.core;

import dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.HolderSet;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.recipe.helper.ItemOutput;

import java.util.Optional;

public class TaggedItemOutput extends ItemOutput {

	public final TagKey<Item> tagKey;
	public final int count;

	public TaggedItemOutput(TagKey<Item> tagKey, int count) {
		this.tagKey = tagKey;
		this.count = count;
	}

	@Override
	public @NotNull ItemStack get() {
		Optional<HolderSet.NamedSet<Item>> set = Registries.ITEM.getTag(this.tagKey);
		if (set.isPresent() && set.get().size() > 0) {
			return ProcessItemOutputCallback.process(new ItemStack(set.get().get(0).value(), this.count));
		}
		return ItemStack.EMPTY;
	}

	@Override
	public @NotNull JsonElement serialize() {
		JsonObject json = new JsonObject();
		json.addProperty("tag", this.tagKey.id().toString());
		if (count != 1) {
			json.addProperty("count", count);
		}
		return json;
	}
}
