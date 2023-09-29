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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;


public class Reagent extends Substrate {
	private final Identifier item;

	private Reagent(Identifier id, Identifier item, int tint) {
		super(id, tint);
		this.item = item;
	}

	public static Reagent of(String id, Identifier item, int tint) {
		return new Reagent(Cabricality.id(id), item, tint);
	}

	@Override
	public boolean isConsumable() {
		return true;
	}

	@Override
	public String getType() {
		return "reagent";
	}

	@NotNull
	public Identifier getItemId() {
		return this.item;
	}

	public Item getItem() {
		return Objects.requireNonNull(Registries.ITEM.get(this.getItemId()));
	}
}
