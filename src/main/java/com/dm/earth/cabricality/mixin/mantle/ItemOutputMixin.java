package com.dm.earth.cabricality.mixin.mantle;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.dm.earth.cabricality.resource.data.core.TaggedItemOutput;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import slimeknights.mantle.recipe.helper.ItemOutput;

@Mixin(ItemOutput.class)
public class ItemOutputMixin {
	@Inject(method = "fromTag", at = @At("HEAD"), cancellable = true)
	private static void fromTag(TagKey<Item> tag, int count,
			CallbackInfoReturnable<ItemOutput> cir) {
		cir.setReturnValue(new TaggedItemOutput(tag, count));
	}
}
