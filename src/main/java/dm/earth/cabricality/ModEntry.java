package dm.earth.cabricality;

import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents;

import java.util.Map;
import java.util.function.Predicate;

// TODO: Recipe api
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
		return checkContains(Registries.ITEM.getId(item));
	}

	public boolean checkContains(RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler, @NotNull Recipe<?> recipe) {
		return handler.getRecipes().get(recipe.getType()).entrySet().stream()
				.filter(entry -> entry.getValue().equals(recipe))
				.map(Map.Entry::getKey)
				.findFirst()
				.map(this::checkContains)
				.orElse(false);
	}

	public Identifier id(String... path) {
		return new Identifier(this.modid, String.join("/", path));
	}

	public Item asItem(String... paths) {
		return Registries.ITEM.get(id(paths));
	}

	public TagKey<Item> asItemTag(String... paths) {
		return TagKey.of(RegistryKeys.ITEM, id(paths));
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
		return Registries.FLUID.get(id(paths));
	}

	public Block asBlock(String... paths) {
		return Registries.BLOCK.get(id(paths));
	}

	public SoundEvent asSoundEvent(String... paths) {
		return Registries.SOUND_EVENT.get(id(paths));
	}

	public Predicate<Recipe<?>> predicateOutput(
			RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler,
			boolean containsCabf, int count, String... paths
	) {
		return recipe -> recipe.getResult(handler.getRegistryManager()).equals(asStack(count, paths))
				&& (containsCabf || !CABF.checkContains(handler, recipe));
	}

	public Predicate<Recipe<?>> predicateOutput(
			RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler,
			boolean containsCabf, String... paths
	) {
		return predicateOutput(handler, containsCabf, 1, paths);
	}

	public Predicate<Recipe<?>> predicateOutput(
			RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler,
			String... paths
	) {
		return predicateOutput(handler, false, paths);
	}

	public static Predicate<Recipe<?>> predicateOutput(
			RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler,
			ItemStack stack
	) {
		return recipe -> recipe.getResult(handler.getRegistryManager()).equals(stack);
	}

	public Predicate<Recipe<?>> predicateIngredient(
			RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler,
			boolean containsCabf, String... paths
	) {
		return recipe -> recipe.getIngredients().stream().anyMatch(asIngredient(paths)::equals)
				&& (containsCabf || !CABF.checkContains(handler, recipe));
	}

	public Predicate<Recipe<?>> predicateIngredient(
			RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler,
			String... paths
	) {
		return predicateIngredient(handler, false, paths);
	}

	public static Predicate<Recipe<?>> predicateIngredient(Item item) {
		return recipe -> recipe.getIngredients().stream().anyMatch(Ingredient.ofItems(item)::equals);
	}
}
