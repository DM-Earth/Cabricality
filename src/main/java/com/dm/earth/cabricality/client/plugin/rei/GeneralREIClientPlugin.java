package com.dm.earth.cabricality.client.plugin.rei;

import com.dm.earth.cabricality.ModEntry;
import com.dm.earth.cabricality.util.debug.CabfDebugger;
import com.github.alexnijjar.ad_astra.registry.ModItems;
import com.github.reoseah.catwalksinc.CIncItems;
import com.google.common.collect.ImmutableList;
import io.github.coolmineman.bitsandchisels.BitsAndChisels;
import me.shedaniel.rei.api.client.entry.filtering.base.BasicFilteringRule;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.EntryType;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.steven.indrev.registry.IRItemRegistry;
import net.krlite.equator.util.IdentifierBuilder;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.CC;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.LED;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;

@SuppressWarnings("UnstableApiUsage")
public class GeneralREIClientPlugin implements REIClientPlugin {
	private static final String[] COLORS ={
			"black", "red", "green", "brown", "blue", "purple",
			"cyan", "light_gray", "gray", "pink", "lime",
			"yellow", "light_blue", "magenta", "orange", "white"
	};

	@Override
	public void registerCollapsibleEntries(CollapsibleEntryRegistry registry) {
		CabfDebugger.debug("Registering Collapsible Entries for Generals");

		/*
		 * Minecraft
		 */
		// Enchanted books
		registry.group(MC.id("enchanted_books"),
				col(MC.id("enchanted_books")),
				predicateIds(Registry.ITEM.getId(Items.ENCHANTED_BOOK)));
		// Potions
		Arrays.stream(new String[]{ null, "lingering", "splash" }).forEach(
				prefix -> registry.group(MC.id(joinAll(prefix, "potions")),
						col(MC.id(joinAll(prefix, "potions"))),
						predicateIds(MC.id(joinAll(prefix, "potion")))));
		// Tipped arrows
		registry.group(MC.id("tipped_arrows"),
				col(MC.id("tipped_arrows")),
				predicateIds(Registry.ITEM.getId(Items.TIPPED_ARROW)));

		/*
		 * AE2
		 */
		// Paint balls
		{
			final String postfix = "paint_ball";
			Arrays.stream(new String[]{null, "lumen"}).forEach(
					type -> registry.group(AE2.id(joinAll(type, "paint_balls")),
							col(AE2.id(joinAll(type, "paint_balls"))),
							EntryIngredients.ofItems(Arrays.stream(COLORS).map(
									color -> AE2.asItem(joinAll(color, type, postfix))).collect(Collectors.toList())))
			);
		}

		/*
		 * Hephaestus
		 */
		// Modifiers
		registry.group(TC.id("modifiers"),
				col(TC.id("modifiers")),
				EntryType.deferred(TC.id("modifier_entry")),
				es -> es.getIdentifier() != null && es.getIdentifier().getNamespace().equals(TC.getModId()));
		// Slime helmets
		registry.group(TC.id("slime_helmets"),
				col(TC.id("slime_helmets")),
				predicateIds(TC.id("slime_helmet")));
		// Casts
		Arrays.stream(new String[]{ "red_sand", "sand", "gold" }).forEach(
				cast -> registry.group(TC.id("casts", cast),
						tag(TC.id("casts", cast)),
						EntryIngredients.ofItemTag(TC.asItemTag("casts", cast))));
		// Tools
		Arrays.stream(new String[]{
				"cleaver", "sword", "dagger", "scythe", "kama",
				"broad_axe", "hand_axe", "excavator", "pickadze",
				"mattock", "vein_hammer", "sledge_hammer", "pickaxe"
		}).forEach(tool -> registry.group(TC.id("tools", tool),
				col(TC.id("tools", tool)),
				predicateIds(TC.id(tool))));
		// Parts
		Arrays.stream(new String[]{
				"tough_handle", "tool_handle", "tool_binding",
				"large_plate", "round_plate", "broad_blade",
				"small_blade", "broad_axe_head", "small_axe_head",
				"hammer_head", "pick_head", "repair_kit"
		}).forEach(part -> registry.group(TC.id("parts", part),
				col(TC.id("parts", part)),
				predicateIds(TC.id(part))));
		// Stations
		Arrays.stream(new String[]{ "part_builder", "tinker_station", "crafting_station" }).forEach(
				station -> registry.group(TC.id("stations", station),
						col(TC.id("stations", station)),
						predicateIds(TC.id(station)))
		);

		/*
		 * LED
		 */
		// Functional blocks
		Arrays.stream(new String[]{ "switch", "button" }).forEach(
				block -> registry.group(LED.id("blocks", block),
						col(LED.id("blocks", block)),
						EntryIngredients.ofItems(Arrays.stream(COLORS).map(
								color -> LED.asItem(joinAll(block, color))).collect(Collectors.toList())))
		);
		// Lamps
		Arrays.stream(new String[]{ null, "shaded", "reinforced", "shaded_reinforced" }).forEach(
				prefix -> {
					// Sizes
					{
						final String POSTFIX = "fixture";
						Arrays.stream(new String[]{"flat", "large", "medium", "small"}).forEach(
								size -> registry.group(LED.id("lamps", joinAll(prefix, size, POSTFIX)),
										col(LED.id("lamps", joinAll(prefix, size, POSTFIX))),
										EntryIngredients.ofItems(Arrays.stream(COLORS).map(
												color -> LED.asItem(joinAll(prefix, size, POSTFIX, color))).collect(Collectors.toList())))
						);
					}
					// Clear full blocks
					{
						final String POSTFIX = "clear_full";
						registry.group(LED.id("lamps", joinAll(prefix, POSTFIX)),
								col(LED.id("lamps", joinAll(prefix, POSTFIX))),
								EntryIngredients.ofItems(Arrays.stream(COLORS).map(
										color -> LED.asItem(joinAll(prefix, POSTFIX, color))).collect(Collectors.toList())));
					}
				}
		);

		/*
		 * Computer Craft
		 */
		// Disks
		registry.group(CC.id("disks"), col(CC.id("disks")), predicateIds(CC.id("disk")));
		{
			final String[] POSTFIX = { "advanced", "normal" };
			// Turtles and pocket computers
			Arrays.stream(new String[]{ "turtle", "pocket_computer" }).forEach(
					thing -> registry.group(CC.id("things", thing), col(CC.id("things", thing)),
							es -> es.getIdentifier() != null && es.getIdentifier().getNamespace().equals(CC.getModId()) &&
										  Arrays.stream(POSTFIX).map(p -> joinAll(thing, p))
												  .anyMatch(p -> es.getIdentifier().getPath().equals(p)))
			);
		}
	}

	private <E> Predicate<? extends EntryStack<E>> predicateIds(Identifier piece) {
		return es -> es.getIdentifier() != null && es.getIdentifier().equals(piece);
	}

	private String joinAll(String... subs) {
		if (subs.length < 1) return "";
		return Arrays.stream(subs).filter(Objects::nonNull).filter(s -> !s.isEmpty()).reduce((f, s) -> f + "_" + s).orElse(subs[0]);
	}

	private TranslatableText tag(Identifier identifier) {
		return new IdentifierBuilder.Specified(identifier.getNamespace()).localization("tag", identifier.getPath());
	}

	private TranslatableText col(Identifier identifier) {
		return new IdentifierBuilder.Specified(identifier.getNamespace()).localization("col", identifier.getPath());
	}

	@Override
	public void registerBasicEntryFiltering(BasicFilteringRule<?> rule) {
		CabfDebugger.debug("Filtering Entries for Generals");

		// Substitutes
		rule.hide(EntryIngredients.ofItems(ImmutableList.of(
				ModEntry.CABF.asItem("gold_coin_top"), ModEntry.CABF.asItem("gold_coin_bottom"),
				ModEntry.CABF.asItem("silver_coin_top"), ModEntry.CABF.asItem("silver_coin_bottom")
		)));

		// Deprecations
		rule.hide(EntryIngredients.ofItems(ImmutableList.of(
				// Wrenches
				ModItems.WRENCH, IRItemRegistry.INSTANCE.getWRENCH(), CIncItems.WRENCH, BitsAndChisels.WRENCH_ITEM,

				// Hammers
				ModItems.HAMMER, IRItemRegistry.INSTANCE.getHAMMER(),

				// Indrev
				Registry.ITEM.get(IR.id("gold_plate")), Registry.ITEM.get(IR.id("iron_plate")), Registry.ITEM.get(IR.id("copper_plate")),

				// Ad Astra
				ModItems.COMPRESSED_STEEL, ModItems.IRON_PLATE
		)));
	}
}
