package com.dm.earth.cabricality.tweak.base;

import java.util.Arrays;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

public interface TagUnifyEntry<T> {

	T getInstance();

	boolean test(T object);

	static void register(TagUnifyEntry<Item> itemEntry) {
		ProcessItemOutputCallback.EVENT.register(new ItemStackCallback(itemEntry));
	}

	static void register(Supplier<Item> item, String... tokens) {
		register(new StringArrayItemTagUnifyEntry(item, tokens));
	}

	class ItemStackCallback implements ProcessItemOutputCallback {

		protected final TagUnifyEntry<Item> entry;

		public ItemStackCallback(TagUnifyEntry<Item> itemEntry) {
			this.entry = itemEntry;
		}

		@Override
		public @Nullable ItemStack processOutput(ItemStack stack) {
			if (entry.test(stack.getItem()) && !stack.isOf(entry.getInstance()))
				return new ItemStack(entry.getInstance(), stack.getCount());
			return null;
		}

	}

	record StringArrayItemTagUnifyEntry(Supplier<Item> instance, String[] tokens) implements TagUnifyEntry<Item> {

		@Override
		public Item getInstance() {
			return instance().get();
		}

		@Override
		public boolean test(Item object) {
			return Arrays.stream(tokens).anyMatch(s -> s.equals(Registry.ITEM.getId(object).getPath()));
		}

	}

}
