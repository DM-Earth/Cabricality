package dm.earth.cabricality;

import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import dm.earth.cabricality.lib.util.ArrayUtil;
import dm.earth.cabricality.lib.util.UrlUtil;
import dm.earth.cabricality.lib.util.log.CabfLogger;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import net.fabricmc.loader.api.FabricLoader;
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
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Mod {
	public enum Entry {
		MC("minecraft"),

		C("c"),

		CREATE("create"),

		CABF(Cabricality.ID),

		PROMENADE("promenade"),

		INDREV("indrev"),

		FARMERS_DELIGHT("farmersdelight"),

		TC("tconstruct"),

		AE2("ae2"),

		TERRESTRIA("terrestria"),

		AD_ASTRA("ad_astra"),

		KIBE("kibe"),

		COXINHA("coxinhautilities"),

		EXT_DRAWERS("extended_drawers"),

		LET("let"),

		CC("computercraft"),

		ITEM_FILTERS("itemfilters"),

		CATWALKS("catwalksllc"),

		BNC("bitsandchisels");

		final String modid;

		Entry(String modid) {
			this.modid = modid;
		}

		public String modid() {
			return modid;
		}

		public boolean isLoaded() {
			return FabricLoader.getInstance().isModLoaded(modid());
		}

		public boolean checkContains(@Nullable Identifier id) {
			return id != null && id.getNamespace().equals(modid());
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
			return new Identifier(modid(), String.join("/", path));
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

	public enum Dependency {
		FTB_LIBRARY("ftblibrary", Text.translatable("mod.ftblibrary.name"),
				UrlUtil.generateCurseForgeModFileUrl("ftb-library-fabric", 4720055),
				false, false),

		FTB_QUESTS("ftbquests", Text.translatable("mod.ftbquests.name"),
				UrlUtil.generateCurseForgeModFileUrl("ftb-quests-fabric", 4774299),
				false, false),

		FTB_TEAMS("ftbteams", Text.translatable("mod.ftbteams.name"),
				UrlUtil.generateCurseForgeModFileUrl("ftb-teams-fabric", 4623115),
				false, false),

		/* TODO: Waiting for Quests Additions
		QUESTS_ADDITIONS("questsadditions", Text.translatable("mod.questsadditions.name"),
				LinkUtil.generateCurseForgeModFileUrl("quests-additions-fabric", 4774299),
				false, false),

		 */

		ITEM_FILTERS("itemfilters", Text.translatable("mod.itemfilters.name"),
				UrlUtil.generateCurseForgeModFileUrl("item-filters", 4728208),
				false, false);

		final String modid;
		final Text name;
		@Nullable final URL url;
		final boolean required;
		final boolean isClient;

		Dependency(String id, Text name, String url, boolean required, boolean isClient) {
			this.modid = id;
			this.name = name;
			this.required = required;
			this.url = getUrl(url);
			this.isClient = isClient;
		}

		public String getModid() {
			return modid;
		}

		public Text getName() {
			return name;
		}

		public String getNameAsString() {
			return name.getString();
		}

		public boolean hasUrl() {
			return url != null;
		}

		@Nullable
		public URL getUrl() {
			if (!hasUrl())
				CabfLogger.debugAndError("Invalid URL for mod " + getNameAsString() + "!");
			return url;
		}

		public boolean isRequired() {
			return required;
		}

		public boolean isClient() {
			return isClient;
		}

		public boolean isLoaded() {
			return FabricLoader.getInstance().isModLoaded(modid);
		}

		public void openUrl() {
			if (hasUrl()) {
				try {
					Util.getOperatingSystem().open(url.toURI());
				} catch (URISyntaxException uriSyntaxException) {
					CabfLogger.debugAndError("Cannot handle URL for mod " + getNameAsString() + "!",
							uriSyntaxException);
				}
			} else {
				CabfLogger.info("No URL found for mod " + getNameAsString() + " (" + modid + ")!");
			}
		}

		public boolean matchesSide(boolean isServer) {
			return !isServer || !isClient;
		}

		public static ArrayList<Dependency> getMissing(boolean required, boolean isServer) {
			return ArrayUtil.asArrayList(Arrays.stream(values())
					.filter(dep ->
							(dep.isRequired() || !required)
									&& dep.matchesSide(isServer) && !dep.isLoaded()
					)
			);
		}

		public static ArrayList<Dependency> getAllMissing() {
			return ArrayUtil.asArrayList(Arrays.stream(values())
					.filter(dep -> !dep.isLoaded())
			);
		}

		public static boolean isLoaded(boolean required, boolean isServer) {
			return getMissing(required, isServer).stream().findAny().isEmpty();
		}

		public static boolean isAllLoaded() {
			return getAllMissing().stream().findAny().isEmpty();
		}

		public static String asString(boolean required, boolean isServer) {
			return getMissing(required, isServer).stream().map(dep -> dep.getName().getString())
					.reduce((a, b) -> a + ", " + b).orElse("");
		}

		private static URL getUrl(String spec) {
			try {
				return new URL(spec);
			} catch (MalformedURLException e) {
				return null;
			}
		}
	}

	public enum Conflict {
		ESSENTIAL("essential-loader");

		final String modid;

		Conflict(String modid) {
			this.modid = modid;
		}

		public String getModid() {
			return modid;
		}

		public boolean isLoaded() {
			return FabricLoader.getInstance().isModLoaded(modid);
		}

		public static boolean isAnyLoaded() {
			return Stream.of(values()).anyMatch(Conflict::isLoaded);
		}

		public static void checkAndExit() {
			if (isAnyLoaded())
				throw new RuntimeException();
		}
	}
}
