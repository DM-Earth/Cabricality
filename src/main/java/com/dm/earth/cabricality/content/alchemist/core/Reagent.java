package com.dm.earth.cabricality.content.alchemist.core;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.dm.earth.cabricality.Cabricality;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
	public String getType() {
		return "reagent";
	}

	@Override
	public boolean consume() {
		return true;
	}

	@NotNull
	public Identifier getItemId() {
		return this.item;
	}

	public Item getItem() {
		return Objects.requireNonNull(Registry.ITEM.get(this.getItemId()));
	}
}
