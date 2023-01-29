package com.dm.earth.cabricality.lib.resource.data.recipe;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.base.api.event.Event;
import org.quiltmc.qsl.base.api.event.EventAwareListener;

import net.minecraft.item.ItemStack;

public interface ProcessItemOutputCallback extends EventAwareListener {

	/**
	 * Process an {@code ItemStack} output of a recipe.
	 * @param stack The default output stack.
	 * @return The modified {@code ItemStack} output, can be null if no changes.
	 */
	@Nullable
	ItemStack processOutput(ItemStack stack);

	Event<ProcessItemOutputCallback> EVENT = Event.create(ProcessItemOutputCallback.class,
			listeners -> stack -> {
				ItemStack ret = stack;
				for (var listener : listeners) {
					ItemStack r = listener.processOutput(ret);
					if (r != null)
						ret = r;
				}
				return ret;
			});

}
