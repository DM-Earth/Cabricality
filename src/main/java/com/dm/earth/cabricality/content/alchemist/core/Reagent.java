package com.dm.earth.cabricality.content.alchemist.core;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.lib.util.NotNullBox;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.qsl.base.api.event.Event;
import org.quiltmc.qsl.base.api.event.EventAwareListener;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * A reagent is a type of substrate which is placed in reagent jars.
 * Reagents can be grouped with a certain catalyst, so that they reagent jars
 * can be processed by alchemist laser and turn into the target catalyst jar.
 */
public class Reagent implements Jarred {
	/**
	 * Registry key of the reagent registry.
	 */
	public static final RegistryKey<Registry<Reagent>> REGISTRY_KEY = RegistryKey.ofRegistry(Cabricality.id("reagent"));

	/**
	 * Registry of reagents.
	 */
	public static final SimpleRegistry<Reagent> REGISTRY = Util.make(() -> {
		SimpleRegistry<Reagent> registry = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();
		RegListener.EVENT.invoker().onRegistryInitialized(registry);
		return registry;
	});

	public interface RegListener extends EventAwareListener {
		Event<RegListener> EVENT = Event.create(RegListener.class, listeners -> registry -> {
			for (var listener : listeners) {
				listener.onRegistryInitialized(registry);
			}
		});

		/**
		 * Called when the registry is first accessed lazily.
		 * @param registry The registry.
		 */
		void onRegistryInitialized(Registry<Reagent> registry);
	}

	/**
	 * Item of this reagent, using lazy supplier to avoid non-initialized items.
	 */
	@NotNull
	protected final Supplier<NotNullBox<Item>> item;

	/**
	 * Tint of this reagent.
	 */
	protected final int tint;

	/**
	 * Item cached lazily.
	 */
	@NotNull
	private Optional<Item> cachedItem = Optional.empty();

	/**
	 * Creates a new reagent without registering it.
	 *
	 * @param item The item supplier of this reagent.
	 * @param tint The tint of this reagent.
	 */
	public Reagent(@NotNull Supplier<NotNullBox<Item>> item, int tint) {
		this.item = item;
		this.tint = tint;
	}

	/**
	 * Creates and registers a new reagent instance.
	 *
	 * @param id   Identifier of this reagent.
	 * @param item Item id of this reagent.
	 * @param tint Tint of this reagent.
	 * @return The registered reagent.
	 */
	@Contract("_, _, _ -> new")
	public static @NotNull Reagent of(Identifier id, Identifier item, int tint) {
		return Registry.register(REGISTRY, id, new Reagent(() -> new NotNullBox<>(Registries.ITEM.get(item)), tint));
	}

	@Override
	public boolean isConsumeable() {
		return true;
	}

	/**
	 * Get the target item of this reagent.
	 *
	 * @return The item.
	 */
	@NotNull
	public Item getItem() {
		if (this.cachedItem.isPresent()) {
			return this.cachedItem.get();
		} else {
			Item item = this.item.get().getValue();
			this.cachedItem = Optional.of(item);
			return item;
		}
	}

	@Override
	public int getTint() {
		return this.tint;
	}
}
