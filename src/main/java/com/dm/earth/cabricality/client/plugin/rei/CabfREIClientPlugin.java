package com.dm.earth.cabricality.client.plugin.rei;

import static com.dm.earth.cabricality.ModEntry.AD;
import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.C;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CC;
import static com.dm.earth.cabricality.ModEntry.CI;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.FD;
import static com.dm.earth.cabricality.ModEntry.IF;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.KB;
import static com.dm.earth.cabricality.ModEntry.LED;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.PM;
import static com.dm.earth.cabricality.ModEntry.TC;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.shedaniel.rei.api.client.registry.screen.ExclusionZones;
import org.quiltmc.loader.api.QuiltLoader;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.ModEntry;
import com.dm.earth.cabricality.content.entries.CabfItemTags;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.lib.util.debug.CabfDebugger;
import com.dm.earth.cabricality.tweak.RecipeTweaks;
import com.google.common.collect.ImmutableList;

import me.shedaniel.rei.api.client.entry.filtering.base.BasicFilteringRule;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.EntryType;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.Items;
import net.minecraft.tag.TagKey;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slimeknights.tconstruct.tables.client.inventory.TinkerStationScreen;

@SuppressWarnings("UnstableApiUsage")
public class CabfREIClientPlugin implements REIClientPlugin {
	/**
	 * Registers a collapsible entry from the given {@link TagKey}.
	 *
	 * @param registry The registry to register the entry to.
	 * @param modEntry The mod entry to register the entry for.
	 * @param tagPaths The tag's paths.
	 */
	private void registerCollapsibleEntryFromTag(CollapsibleEntryRegistry registry, ModEntry modEntry, String... tagPaths) {
		registry.group(modEntry.id(tagPaths), tag(modEntry.id(tagPaths)),
				EntryIngredients.ofItemTag(modEntry.asItemTag(tagPaths)));
	}

	/**
	 * Predicates all entries that are of the given {@link Identifier}.
	 *
	 * @param piece The identifier to predicate.
	 * @return The predicate result.
	 * @param <E> The entry type.
	 */
	private <E> Predicate<? extends EntryStack<E>> predicateIds(Identifier piece) {
		return entryStack -> entryStack.getIdentifier() != null && entryStack.getIdentifier().equals(piece);
	}

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

	private TranslatableText convertToTranslatableText(String prefix, Identifier identifier) {
		return new TranslatableText(prefix + "." + identifier.getNamespace() + "." + String.join(".", identifier.getPath()));
	}

	/**
	 * Gets a {@link TranslatableText} from the given {@link Identifier}, prefixed
	 * with <code>"tag"</code>.
	 *
	 * @param identifier The identifier.
	 * @return The tagged text.
	 */
	private TranslatableText tag(Identifier identifier) {
		return convertToTranslatableText("tag", identifier);
	}

	/**
	 * Gets a {@link TranslatableText} from the given {@link Identifier}, prefixed
	 * with <code>"col"</code>.
	 *
	 * @param identifier The identifier.
	 * @return The coled text.
	 */
	private TranslatableText col(Identifier identifier) {
		return convertToTranslatableText("col", identifier);
	}

	private static final String[] COLORS = {
			"black", "red", "green", "brown", "blue", "purple",
			"cyan", "light_gray", "gray", "pink", "lime",
			"yellow", "light_blue", "magenta", "orange", "white"
	};

	@Override
	public void registerCollapsibleEntries(CollapsibleEntryRegistry registry) {
		CabfDebugger.debug("Registering Collapsible Entries");

		// Fluids
		registry.group(MC.id("fluids"),
				col(MC.id("fluids")),
				entryStack -> entryStack.getType() == VanillaEntryTypes.FLUID);

		// Spawn eggs
		registry.group(MC.id("spawn_eggs"),
				col(MC.id("spawn_eggs")),
				entryStack -> entryStack.getIdentifier() != null
						&& entryStack.getIdentifier().getPath().endsWith("spawn_egg"));

		/*
		 * C
		 */
		// Shulker boxes
		registerCollapsibleEntryFromTag(registry, C, "shulker_boxes");
		// Glass
		registry.group(C.id("glass"),
				tag(C.id("glass")),
				entryStack -> entryStack.getTagsFor().anyMatch(tag -> tag.equals(C.asItemTag("glass"))) ||
						(entryStack.getType() != VanillaEntryTypes.FLUID &&
								(TC.checkContains(entryStack.getIdentifier())
										|| AE2.checkContains(entryStack.getIdentifier()))
								&&
								entryStack.getIdentifier().getPath().endsWith("glass"))); // Special case for glass in
																							// TC and AE2
		// Glass panes
		registry.group(C.id("glass_panes"),
				tag(C.id("glass_panes")),
				entryStack -> entryStack.getTagsFor().anyMatch(tag -> tag.equals(C.asItemTag("glass_panes"))) ||
						(entryStack.getType() != VanillaEntryTypes.FLUID &&
								TC.checkContains(entryStack.getIdentifier()) &&
								entryStack.getIdentifier().getPath().endsWith("glass_pane"))); // Special case for glass
																								// panes in TC

		/*
		 * Minecraft
		 */
		// Enchanted books
		registry.group(MC.id("enchanted_books"),
				col(MC.id("enchanted_books")),
				predicateIds(Registry.ITEM.getId(Items.ENCHANTED_BOOK)));
		// Potions
		Arrays.stream(new String[] { null, "lingering", "splash" }).forEach(
				prefix -> registry.group(MC.id(joinAll(prefix, "potions")),
						col(MC.id(joinAll(prefix, "potions"))),
						predicateIds(MC.id(joinAll(prefix, "potion")))));
		// Tipped arrows
		registry.group(MC.id("tipped_arrows"),
				col(MC.id("tipped_arrows")),
				predicateIds(Registry.ITEM.getId(Items.TIPPED_ARROW)));
		// Glazed terracottas
		registry.group(MC.id("glazed_terracottas"),
				col(MC.id("glazed_terracottas")),
				EntryIngredients.ofItems(Arrays.stream(COLORS).map(
						color -> MC.asItem(color + "_glazed_terracotta")).collect(Collectors.toList())));
		// Music discs & carpets & banners & candles & beds & signs & leaves & logs &
		// planks & stairs & slabs
		Arrays.stream(new String[] {
				"music_discs", "carpets", "banners", "candles", "beds",
				"signs", "leaves", "logs", "planks", "stairs", "slabs"
		}).forEach(tag -> registerCollapsibleEntryFromTag(registry, MC, tag));

		/*
		 * Cabricality
		 */
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

		/*
		 * AE2
		 */
		// Paint balls
		{
			final String postfix = "paint_ball";
			Arrays.stream(new String[] { null, "lumen" }).forEach(
					type -> registry.group(AE2.id(joinAll(type, "paint_balls")),
							col(AE2.id(joinAll(type, "paint_balls"))),
							EntryIngredients.ofItems(Arrays.stream(COLORS).map(
									color -> AE2.asItem(joinAll(color, type, postfix))).collect(Collectors.toList()))));
		}

		if (QuiltLoader.isModLoaded(IF.getModid())) {
			/*
			 * Item Filters
			 */
			// Filters
			registerCollapsibleEntryFromTag(registry, IF, "filters");
		}

		/*
		 * Catwalks Inc.
		 */
		// Paint rollers
		registry.group(CI.id("filled_paint_rollers"),
				tag(CI.id("filled_paint_rollers")),
				entryStack -> CI.checkContains(entryStack.getIdentifier()) &&
						(entryStack.getTagsFor().anyMatch(tag -> tag.equals(CI.asItemTag("filled_paint_rollers")) ||
								entryStack.getIdentifier().getPath().contains("filled_paint_rollers"))));

		/*
		 * Create
		 */
		// Stone types
		Arrays.stream(new String[] {
				"veridium", "scorchia", "scoria", "ochrum", "limestone",
				"crimsite", "asurine", "tuff", "deepslate", "dripstone",
				"calcite", "andesite", "diorite", "granite"
		}).forEach(type -> registry.group(CR.id("stone_types", type),
				tag(CR.id("stone_types", type)),
				entryStack -> CR.checkContains(entryStack.getIdentifier()) &&
						(entryStack.getTagsFor().anyMatch(tag -> tag.equals(CR.asItemTag("stone_types", type))) ||
								entryStack.getIdentifier().getPath().contains(type))));
		// Copper tiles & shingles
		Arrays.stream(new String[] { "tile", "shingle" }).forEach(
				type -> registry.group(CR.id("blocks", joinAll("copper", type)),
						col(CR.id("blocks", joinAll("copper", type))),
						entryStack -> CR.checkContains(entryStack.getIdentifier())
								&& entryStack.getIdentifier().getPath().contains(joinAll("copper", type))));
		// Toolboxes & seats
		Arrays.stream(new String[] { "toolboxes", "seats" }).forEach(
				tag -> registerCollapsibleEntryFromTag(registry, CR, tag));

		/*
		 * Farmer's Delight
		 */
		// Canvas signs
		registerCollapsibleEntryFromTag(registry, FD, "canvas_signs");

		/*
		 * Promenade
		 */
		// Piles
		registry.group(PM.id("piles"),
				col(PM.id("piles")),
				entryStack -> PM.checkContains(entryStack.getIdentifier())
						&& entryStack.getIdentifier().getPath().endsWith("pile"));
		// Mushrooms & mushroom blocks
		Arrays.stream(new String[] { null, "block" }).forEach(
				type -> registry.group(PM.id("blocks", joinAll("mushroom", type)),
						col(PM.id("blocks", joinAll("mushroom", type))),
						entryStack -> PM.checkContains(entryStack.getIdentifier())
								&& entryStack.getIdentifier().getPath().contains(joinAll("mushroom", type))));

		/*
		 * Kibe
		 */
		// Colorful blocks
		Arrays.stream(new String[] { "sleeping_bag", "glider", "rune", "elevator" }).forEach(
				type -> registry.group(KB.id("things", type),
						col(KB.id("things", type)),
						EntryIngredients.ofItems(Stream.concat(
								Arrays.stream(COLORS).map(color -> KB.asItem(joinAll(color, type))),
								Objects.equals(type, "glider") ? // The glider has a special case: right/left wings
										Stream.of(KB.asItem("glider_right_wing"), KB.asItem("glider_left_wing"))
										: Stream.empty())
								.collect(Collectors.toList()))));

		/*
		 * Hephaestus
		 */
		// Modifiers
		registry.group(TC.id("modifiers"),
				col(TC.id("modifiers")),
				EntryType.deferred(TC.id("modifier_entry")),
				entryStack -> TC.checkContains(entryStack.getIdentifier()));
		// Slime helmets
		registry.group(TC.id("slime_helmets"),
				col(TC.id("slime_helmets")),
				predicateIds(TC.id("slime_helmet")));
		// Casts
		Arrays.stream(new String[] { "red_sand", "sand", "gold" }).forEach(
				cast -> registerCollapsibleEntryFromTag(registry, TC, "casts", cast));
		// Tools
		Arrays.stream(new String[] {
				"cleaver", "sword", "dagger", "scythe", "kama",
				"broad_axe", "hand_axe", "excavator", "pickadze",
				"mattock", "vein_hammer", "sledge_hammer", "pickaxe"
		}).forEach(tool -> registry.group(TC.id("tools", tool),
				col(TC.id("tools", tool)),
				predicateIds(TC.id(tool))));
		// Parts
		Arrays.stream(new String[] {
				"tough_handle", "tool_handle", "tool_binding",
				"large_plate", "round_plate", "broad_blade",
				"small_blade", "broad_axe_head", "small_axe_head",
				"hammer_head", "pick_head", "repair_kit"
		}).forEach(part -> registry.group(TC.id("parts", part),
				col(TC.id("parts", part)),
				predicateIds(TC.id(part))));

		// Anvils
		registry.group(TC.id("anvils"),
				col(TC.id("anvils")),
				entryStack -> TC.checkContains(entryStack.getIdentifier()) &&
						(entryStack.getIdentifier().getPath().equals("scorched_anvil") ||
								entryStack.getIdentifier().getPath().equals("tinkers_anvil")));
		// Stations
		Arrays.stream(new String[] { "part_builder", "tinker_station", "crafting_station" }).forEach(
				station -> registry.group(TC.id("stations", station),
						col(TC.id("stations", station)),
						predicateIds(TC.id(station))));
		// Foundries & Smelteries
		Arrays.stream(new String[] { "foundry", "smeltery" }).forEach(
				type -> registry.group(TC.id("blocks", type),
						tag(TC.id("blocks", type)),
						EntryIngredients.ofItemTag(TC.asItemTag(type))));

		// Buckets
		registry.group(MC.id("buckets"),
				col(MC.id("buckets")), entryStack -> ((MC.checkContains(entryStack.getIdentifier()) ||
						CABF.checkContains(entryStack.getIdentifier()) /* Also including CABF's buckets */ ||
						TC.checkContains(entryStack.getIdentifier()) /* Also including TC's buckets */ ||
						CR.checkContains(entryStack.getIdentifier()) /* Also including CR's buckets */ ||
						IR.checkContains(entryStack.getIdentifier()) /* Also including IR's buckets */ ||
						AD.checkContains(entryStack.getIdentifier()) /* Also including AD's buckets */ ) &&
						entryStack.getIdentifier().getPath().endsWith("bucket") &&
						// Avoid including potion buckets
						!entryStack.getIdentifier().getPath().equals("potion_bucket")) /*
																						 * Avoid including potion
																						 * buckets
																						 */ ||
				// Also including KB's liquid xp bucket
						entryStack.getIdentifier().equals(KB.id("liquid_xp_bucket")));
		// Potion buckets
		registry.group(TC.id("buckets", "potion"),
				col(TC.id("buckets", "potion")),
				predicateIds(TC.id("potion_bucket")));
		// Slime grasses
		Arrays.stream(new String[] { "ichor", "ender", "sky", "earth", "vanilla" }).forEach(
				type -> registry.group(TC.id("slime_grasses", type),
						col(TC.id("slime_grasses", type)),
						entryStack -> TC.checkContains(entryStack.getIdentifier()) &&
								entryStack.getIdentifier().getPath().endsWith(joinAll(type, "slime_grass"))));
		// Slime dirts & congealed slimes & slimes
		Arrays.stream(new String[] { "slime_dirt", "congealed_slime", "slime" }).forEach(
				suffix -> registry.group(TC.id("blocks", suffix),
						col(TC.id("blocks", suffix)),
						entryStack -> TC.checkContains(entryStack.getIdentifier()) &&
								entryStack.getIdentifier().getPath().endsWith(suffix)));

		/*
		 * Ad Astra!
		 */
		// Flags
		registry.group(AD.id("flags"),
				col(AD.id("flags")),
				entryStack -> AD.checkContains(entryStack.getIdentifier())
						&& entryStack.getIdentifier().getPath().startsWith("flag"));

		/*
		 * Indrev
		 */
		// Modules
		registry.group(IR.id("modules"),
				col(IR.id("modules")),
				entryStack -> IR.checkContains(entryStack.getIdentifier()) &&
						entryStack.getIdentifier().getPath().startsWith("module"));

		/*
		 * LED
		 */
		// Functional blocks
		Arrays.stream(new String[] { "switch", "button" }).forEach(
				block -> registry.group(LED.id("blocks", block),
						col(LED.id("blocks", block)),
						EntryIngredients.ofItems(Arrays.stream(COLORS).map(
								color -> LED.asItem(joinAll(block, color))).collect(Collectors.toList()))));
		// Lamps
		Arrays.stream(new String[] { null, "shaded", "reinforced", "shaded_reinforced" }).forEach(
				prefix -> {
					// Sizes
					{
						final String POSTFIX = "fixture";
						Arrays.stream(new String[] { "flat", "large", "medium", "small" }).forEach(
								size -> registry.group(LED.id("lamps", joinAll(prefix, size, POSTFIX)),
										col(LED.id("lamps", joinAll(prefix, size, POSTFIX))),
										EntryIngredients.ofItems(Arrays.stream(COLORS).map(
												color -> LED.asItem(joinAll(prefix, size, POSTFIX, color)))
												.collect(Collectors.toList()))));
					}
					// Clear full blocks
					{
						final String POSTFIX = "clear_full";
						registry.group(LED.id("lamps", joinAll(prefix, POSTFIX)),
								col(LED.id("lamps", joinAll(prefix, POSTFIX))),
								EntryIngredients.ofItems(Arrays.stream(COLORS).map(
										color -> LED.asItem(joinAll(prefix, POSTFIX, color)))
										.collect(Collectors.toList())));
					}
				});

		/*
		 * Computer Craft
		 */
		// Disks
		registry.group(CC.id("disks"), col(CC.id("disks")), predicateIds(CC.id("disk")));
		{
			final String[] POSTFIX = { "advanced", "normal" };
			// Turtles and pocket computers
			Arrays.stream(new String[] { "turtle", "pocket_computer" }).forEach(
					thing -> registry.group(CC.id("things", thing), col(CC.id("things", thing)),
							entryStack -> CC.checkContains(entryStack.getIdentifier()) &&
									Arrays.stream(POSTFIX).map(p -> joinAll(thing, p))
											.anyMatch(p -> entryStack.getIdentifier().getPath().equals(p))));
		}
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
							.forEach(postfix -> rule.hide(EntryIngredients.of(IR.asItem(joinAll(prefix, postfix))))));
		}
	}

	@Override
	public void registerExclusionZones(ExclusionZones zones) {
		zones.register(TinkerStationScreen.class, new TinkersAnvilExclusionZones());
	}
}
