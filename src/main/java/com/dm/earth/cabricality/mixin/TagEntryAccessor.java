package com.dm.earth.cabricality.mixin;

import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Ingredient.TagEntry.class)
public interface TagEntryAccessor {
	@Accessor
	TagKey<Item> getTag();
}
