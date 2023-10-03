package dm.earth.cabricality.client.plugin.rei;

import com.google.common.collect.ImmutableList;
import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.ModEntry;
import dm.earth.cabricality.content.entries.CabfItemTags;
import dm.earth.cabricality.content.entries.CabfItems;
import dm.earth.cabricality.lib.util.debug.CabfDebugger;
import dm.earth.cabricality.tweak.RecipeTweaks;
import me.shedaniel.rei.api.client.entry.filtering.base.BasicFilteringRule;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZones;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dm.earth.cabricality.ModEntry.CABF;
import static dm.earth.cabricality.ModEntry.INDREV;

@SuppressWarnings("UnstableApiUsage")
public class CabfREIClientPlugin implements REIClientPlugin {
	/**
	 * Joins all the given sub strings into a single string by underscores, ignoring
	 * nulls and empties.
	 * <br />
	 * <code>["sub", null, "string"] -> ["sub_string"]</code>
	 *
	 * @param subs The sub strings to join.
	 * @return The joined string.
	 */
	private String joinAll(String... subs) {
		if (subs.length < 1)
			return "";
		return Arrays.stream(subs).filter(Objects::nonNull).filter(s -> !s.isEmpty()).reduce((f, s) -> f + "_" + s)
				.orElse(subs[0]);
	}

	private Text convertToTranslatableText(String prefix, Identifier identifier) {
		return Text.translatable(prefix + "." + identifier.getNamespace() + "." + String.join(".", identifier.getPath()));
	}

	/**
	 * Gets a {@link Text} from the given {@link Identifier}, prefixed
	 * with <code>"tag"</code>.
	 *
	 * @param identifier The identifier.
	 * @return The tagged text.
	 */
	private Text tag(Identifier identifier) {
		return convertToTranslatableText("tag", identifier);
	}

	@Override
	public void registerCollapsibleEntries(CollapsibleEntryRegistry registry) {
		// Alchemist Jars
		registry.group(Cabricality.id("catalyst_jars"),
				Cabricality.genTranslatableText("tag",
						CabfItemTags.CATALYST_JARS.id().getPath()),
				EntryIngredients.ofItemTag(CabfItemTags.CATALYST_JARS));
		registry.group(Cabricality.id("reagent_jars"),
				Cabricality.genTranslatableText("tag",
						CabfItemTags.REAGENT_JARS.id().getPath()),
				EntryIngredients.ofItemTag(CabfItemTags.REAGENT_JARS));

		// Trading
		registry.group(Cabricality.id("trade_cards"),
				Cabricality.genTranslatableText("tag",
						CabfItemTags.TRADE_CARDS.id().getPath()),
				EntryIngredients.ofItemTag(CabfItemTags.TRADE_CARDS));
		registry.group(Cabricality.id("profession_cards"),
				Cabricality.genTranslatableText("tag",
						CabfItemTags.PROFESSION_CARDS.id().getPath()),
				EntryIngredients.ofItemTag(CabfItemTags.PROFESSION_CARDS));

		// Numbers
		registry.group(Cabricality.id("numbers"),
				Cabricality.genTranslatableText("col", "numbers"),
				EntryIngredients.ofItems(Stream.concat(CabfItems.NUMBERS.stream().map(n -> CABF.asItem("number_" + n)),
						CabfItems.OPERATORS.keySet().stream().map(CABF::asItem)).collect(Collectors.toList())));

		// Math casts
		registry.group(Cabricality.id("math_casts"),
				Cabricality.genTranslatableText("col", "math_casts"),
				EntryIngredients.ofItems(
						CabfItems.MATH_CASTS.stream().map(c -> CABF.asItem(c + "_cast")).collect(Collectors.toList())));
	}

	@Override
	public void registerBasicEntryFiltering(BasicFilteringRule<?> rule) {
		CabfDebugger.debug("Filtering Entries");

		// Deprecations
		rule.hide(EntryIngredients.ofItems(RecipeTweaks.DEPRECATED_ITEMS));

		// Substitutes
		rule.hide(EntryIngredients.ofItems(ImmutableList.of(
				ModEntry.CABF.asItem("gold_coin_top"), ModEntry.CABF.asItem("gold_coin_bottom"),
				ModEntry.CABF.asItem("silver_coin_top"), ModEntry.CABF.asItem("silver_coin_bottom"))));

		// Indrev
		{
			final String[] POSTFIX = { "pickaxe", "axe", "shovel", "hoe", "sword" };
			Arrays.stream(new String[] { "tin", "copper", "steel", "bronze", "lead", "silver" }).forEach(
					prefix -> Arrays.stream(POSTFIX)
							.forEach(postfix -> rule.hide(EntryIngredients.of(INDREV.asItem(joinAll(prefix, postfix))))));
		}
	}

	@Override
	public void registerExclusionZones(ExclusionZones zones) {
		// TODO: Waiting for Hephaestus
		// zones.register(TinkerStationScreen.class, new TinkersAnvilExclusionZones());
	}
}
