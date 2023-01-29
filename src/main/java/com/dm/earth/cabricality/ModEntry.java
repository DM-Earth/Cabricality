package com.dm.earth.cabricality;

import com.simibubi.create.content.contraptions.processing.ProcessingOutput;
import net.krlite.equator.util.IdentifierBuilder;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

public enum ModEntry {
	// Abbreviations
	MC("minecraft"),

	C("c"),

	CR("create"),

	CABF(Cabricality.ID),

	PM("promenade"),

	IR("indrev"),

	FD("farmersdelight"),

	AP("architects_palette"),

	TC("tconstruct"),

	MLM("malum"),

	AE2("ae2"),

	TRE("terrestria"),

	AD("ad_astra"),

	KB("kibe"),

	CX("coxinhautilities"),

	ED("extended_drawers"),
	LED("led"),
	CC("computercraft"),
	IF("itemfilters"),
	CI("catwalksinc");

	final String modId;

	ModEntry(String modId) {
		this.modId = modId;
	}

	public String getModId() {
		return modId;
	}

	public boolean checkContains(@Nullable Identifier id) {
		return id != null && id.getNamespace().equals(modId);
	}

	public Identifier id(String... path) {
		return new IdentifierBuilder.Specified(this.modId).id(path);
	}

	public Item asItem(String... paths) {
		return Registry.ITEM.get(id(paths));
	}

	public TagKey<Item> asItemTag(String... paths) {
		return TagKey.of(Registry.ITEM_KEY, id(paths));
	}

	public ItemStack asStack(String name, int count) {
		return new ItemStack(asItem(name), count);
	}

	public ItemStack asStack(String name) {
		return new ItemStack(asItem(name), 1);
	}

	public Ingredient asIngredient(String... paths) {
		return Ingredient.ofItems(asItem(paths));
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

	public Fluid asFluid(String... paths) {
		return Registry.FLUID.get(id(paths));
	}

	public Block asBlock(String... paths) {
		return Registry.BLOCK.get(id(paths));
	}

	public SoundEvent asSoundEvent(String... paths) {
		return Registry.SOUND_EVENT.get(id(paths));
	}
}
