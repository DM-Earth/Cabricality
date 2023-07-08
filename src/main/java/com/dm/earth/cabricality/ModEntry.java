package com.dm.earth.cabricality;

import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public enum ModEntry {
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

	CI("catwalksinc"),

	BC("bitsandchisels");

	final String modid;

	public String getModid() {
		return modid;
	}

	ModEntry(String modid) {
		this.modid = modid;
	}

	public boolean checkContains(@Nullable Identifier id) {
		return id != null && id.getNamespace().equals(modid);
	}

	public boolean checkContains(@NotNull Item item) {
		return checkContains(Registry.ITEM.getId(item));
	}

	public boolean checkContains(@NotNull Recipe<?> recipe) {
		return checkContains(recipe.getId());
	}

	public Identifier id(String... path) {
		return new Identifier(this.modid, String.join("/", path));
	}

	public Item asItem(String... paths) {
		return Registry.ITEM.get(id(paths));
	}

	public TagKey<Item> asItemTag(String... paths) {
		return TagKey.of(Registry.ITEM_KEY, id(paths));
	}

	public ItemStack asStack(int count, String... paths) {
		return new ItemStack(asItem(paths), count);
	}

	public ItemStack asStack(String... paths) {
		return new ItemStack(asItem(paths), 1);
	}

	public Ingredient asIngredient(String... paths) {
		return Ingredient.ofItems(asItem(paths));
	}

	public ProcessingOutput asProcessingOutput(String... paths) {
		return asProcessingOutput(1, paths);
	}

	public ProcessingOutput asProcessingOutput(float chance, String... paths) {
		return new ProcessingOutput(asStack(paths), chance);
	}

	public ProcessingOutput asProcessingOutput(float chance, int count, String... paths) {
		return new ProcessingOutput(asStack(count, paths), chance);
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

	public Predicate<Recipe<?>> predicateOutput(boolean containsCabf, int count, String... paths) {
		return recipe -> recipe.getOutput().isItemEqualIgnoreDamage(asStack(count, paths))
				&& (containsCabf || !CABF.checkContains(recipe.getId()));
	}

	public Predicate<Recipe<?>> predicateOutput(boolean containsCabf, String... paths) {
		return predicateOutput(containsCabf, 1, paths);
	}

	public Predicate<Recipe<?>> predicateOutput(String... paths) {
		return predicateOutput(false, paths);
	}

	public static Predicate<Recipe<?>> predicateOutput(ItemStack stack) {
		return recipe -> recipe.getOutput().isItemEqualIgnoreDamage(stack);
	}

	public Predicate<Recipe<?>> predicateIngredient(boolean containsCabf, String... paths) {
		return recipe -> recipe.getIngredients().stream().anyMatch(asIngredient(paths)::equals)
				&& (containsCabf || !CABF.checkContains(recipe.getId()));
	}

	public Predicate<Recipe<?>> predicateIngredient(String... paths) {
		return predicateIngredient(false, paths);
	}

	public static Predicate<Recipe<?>> predicateIngredient(Item item) {
		return recipe -> recipe.getIngredients().stream().anyMatch(Ingredient.ofItems(item)::equals);
	}
}
