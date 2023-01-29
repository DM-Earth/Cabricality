package com.dm.earth.cabricality.tweak.base;

import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public record TagUnifyEntry<T>(TagKey<T> tag, T instance) {

	public static void register(TagUnifyEntry<Item> itemEntry) {
		ProcessItemOutputCallback.EVENT.register(new ItemStackCallback(itemEntry));
	}

	public static void register(Identifier tag, Item item) {
		register(new TagUnifyEntry<>(TagKey.of(Registry.ITEM_KEY, tag), item));
	}

	private static class ItemStackCallback implements ProcessItemOutputCallback {

		protected final TagUnifyEntry<Item> entry;

		public ItemStackCallback(TagUnifyEntry<Item> itemEntry) {
			this.entry = itemEntry;
		}

		@Override
		public @Nullable ItemStack processOutput(ItemStack stack) {
			if (stack.isIn(entry.tag()) && !stack.isOf(entry.instance())) {
				var retStack = new ItemStack(entry.instance(), stack.getCount());
				retStack.setNbt(stack.getNbt());
				return retStack;
			}
			return null;
		}

	}

}
