package com.dm.earth.cabricality;

import com.simibubi.create.content.contraptions.processing.ProcessingOutput;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModEntry {
	/* Abbreviations */
	MC("minecraft"), C("c"), CR("create"), CABF(Cabricality.ID), PMD("promenade"), IR("indrev"), FD(
			"farmersdelight"), AP("architects_palette"), TC("tconstruct"), MLM("malum"), AE2(
					"ae2"), TRE("terrestria"), AD(
							"ad_astra"), KB("kibe"), CX("coxinhautilities"), ED("extended_drawers");

	final String modId;

	ModEntry(String modId) {
		this.modId = modId;
	}

	public Identifier id(String... path) {
		return new Identifier(this.modId, String.join("/", path));
	}

	public Item asItem(String name) {
		return Registry.ITEM.get(id(name));
	}

	public ItemStack asStack(String name, int count) {
		return new ItemStack(asItem(name), count);
	}

	public ItemStack asStack(String name) {
		return new ItemStack(asItem(name), 1);
	}

	public Ingredient asIngredient(String name) {
		return Ingredient.ofItems(asItem(name));
	}

	public ProcessingOutput asProcessingOutput(String name) {
		return asProcessingOutput(name, 1);
	}

	public ProcessingOutput asProcessingOutput(String name, float chance) {
		return new ProcessingOutput(asStack(name), chance);
	}

	public ProcessingOutput asProcessingOutput(String name, float chance, int count) {
		return new ProcessingOutput(asStack(name, count), chance);
	}

	public Fluid asFluid(String name) {
		return Registry.FLUID.get(id(name));
	}

	public Block asBlock(String name) {
		return Registry.BLOCK.get(id(name));
	}

	public SoundEvent asSoundEvent(String name) {
		return Registry.SOUND_EVENT.get(id(name));
	}
}
