package com.dm.earth.cabricality.content.alchemist;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.*;
import static com.dm.earth.cabricality.ModEntry.PM;
import static com.dm.earth.cabricality.content.alchemist.core.Reagent.of;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dm.earth.cabricality.content.alchemist.block.JarBlock;

import net.minecraft.block.Block;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.alchemist.core.Catalyst;
import com.dm.earth.cabricality.content.alchemist.core.Reagent;
import net.minecraft.util.registry.Registry;

public enum Reagents {
	IGNEOUS("igneous", 0x6C8191, 16, true,
			of("andesite", MC.id("andesite"), 0x868887),
			of("diorite", MC.id("diorite"), 0xE6E2E6),
			of("granite", MC.id("granite"), 0x9E6B5A),
			of("cobblestone", MC.id("cobblestone"), 0xA6A6A6),
			of("basalt", MC.id("basalt"), 0x32333D),
			of("tuff", MC.id("tuff"), 0x85837B),
			of("limestone", CR.id("limestone"), 0xBBB6A9),
			of("scoria", CR.id("scoria"), 0x493A34),
			of("blunite", PM.id("blunite"), 0x5F6874),
			of("carbonite", PM.id("carbonite"), 0x514E52)),

	HERBAL("herbal", 0xB5CDA3, 1, true,
			of("white", MC.id("lily_of_the_valley"), 0xE8E8E8),
			of("orange", MC.id("orange_tulip"), 0xFD9E28),
			of("magenta", MC.id("allium"), 0xBA63E2),
			of("light_blue", MC.id("blue_orchid"), 0x21C1FD),
			of("yellow", MC.id("dandelion"), 0xFFEC4E),
			of("pink", MC.id("pink_tulip"), 0xF7C8FE),
			of("light_gray", MC.id("white_tulip"), 0xD0E9E9),
			of("blue", MC.id("cornflower"), 0x2F6EEC),
			of("red", MC.id("red_tulip"), 0xEB3334),
			of("black", MC.id("wither_rose"), 0x45322B)),

	VOLATILE("volatile", 0x9F5F80, 1, true,
			of("blaze", MC.id("blaze_powder"), 0xFDA228),
			of("slime", MC.id("slime_ball"), 0x8CD382),
			of("nether", MC.id("nether_wart"), 0xA42733),
			of("obsidian", CR.id("powdered_obsidian"), 0x271F3C),
			of("gunpowder", MC.id("gunpowder"), 0x727272),
			of("prismarine", MC.id("prismarine_shard"), 0xA5D1C2),
			of("hex", MLM.id("hex_ash"), 0xA235BC),
			of("ender", AE2.id("ender_dust"), 0x38CCB1)),

	CRYSTAL("crystal", 0xffb037, 1, true,
			of("sulfur", IR.id("sulfur_dust"), 0xc7a94a),
			of("certus_quartz", AE2.id("certus_quartz_dust"), 0xbbdcfd),
			of("cinnabar", MC.id("redstone"), 0xe8364f),
			of("blazing_quartz", MLM.id("blazing_quartz"), 0xfee568),
			of("nether_quartz", MC.id("quartz"), 0xddd4c7)),

	METAL("metal", 0xc9c9c9, 1, true,
			of("iron", IR.id("iron_dust"), 0xd1d1d1),
			of("gold", IR.id("gold_dust"), 0xeed73c),
			of("copper", IR.id("copper_dust"), 0xe7763a),
			of("tin", IR.id("tin_dust"), 0xb6b9ba),
			of("lead", IR.id("lead_dust"), 0x313150),
			of("zinc", CABF.id("zinc_dust"), 0xaecbb9),
			of("nickel", CABF.id("nickel_dust"), 0xd9b669)),

	CHAOTIC("chaos", 0xb200ed, 1, false,
			of("silver", IR.id("silver_dust"), 0x9fadb4),
			of("silicon", AE2.id("silicon"), 0x85837b));

	private final String name;
	private final int tint;
	private final int price;
	private final List<Reagent> entries;
	private final Catalyst catalyst;
	private final boolean link;

	Reagents(String name, int tint, int price, boolean link, Reagent... reagents) {
		this.name = name;
		this.tint = tint;
		this.price = price;
		this.entries = List.of(reagents);
		this.catalyst = Catalyst.of(name, tint);
		this.link = link;
	}

	@Nullable
	public static Reagent getReagentFromHash(String hashStr) {
		return Arrays.stream(values()).flatMap(reagents -> reagents.getReagents().stream())
				.filter(reagent -> reagent.hashString().equals(hashStr)).findFirst().orElse(null);
	}

	@Nullable
	public static Catalyst getCatalystFromHash(String hashStr) {
		return Arrays.stream(values()).map(Reagents::getCatalyst)
				.filter(catalyst -> catalyst.hashString().equals(hashStr)).findFirst().orElse(null);
	}

	@NotNull
	public static Reagent getReagentFromBlock(ReagentJarBlock block) {
		Reagent reagent = getReagentFromHash(Registry.BLOCK.getId(block).getPath()
				.replaceAll(block.getDefaultBlockId().getPath() + "_", ""));
		// Check Content Validity
		if (reagent == null) {
			Cabricality.LOGGER.error("Invalid Reagent " + Registry.BLOCK.getId(block)
					+ "! Valid Reagents:"
					+ Arrays.stream(Reagents.values())
							.map(reagents -> reagents.getReagents().stream()
									.map(value -> "\n" + value.getName().getString())
									.collect(Collectors.joining()))
							.collect(Collectors.joining()));
			throw new EnumConstantNotPresentException(Reagents.class,
					Registry.BLOCK.getId(block).toString());
		}
		return reagent;
	}

	@NotNull
	public static Catalyst getCatalystFromBlock(CatalystJarBlock block) {
		Catalyst catalyst = getCatalystFromHash(Registry.BLOCK.getId(block).getPath()
				.replaceAll(block.getDefaultBlockId().getPath() + "_", ""));
		// Check Content Validity
		if (catalyst == null) {
			Cabricality.LOGGER
					.error("Invalid Catalyst" + Registry.BLOCK.getId(block) + "! Valid Catalysts:"
							+ Arrays.stream(Reagents.values())
									.map(value -> "\n" + value.getCatalyst().toString())
									.collect(Collectors.joining()));
			throw new EnumConstantNotPresentException(Reagents.class,
					Registry.BLOCK.getId(block).toString());
		}
		return catalyst;
	}

	@Contract(pure = true)
	public String getName() {
		return name;
	}

	@Contract(pure = true)
	public int getTint() {
		return tint;
	}

	@Contract(pure = true)
	public List<Reagent> getReagents() {
		return entries;
	}

	@Contract(pure = true)
	public Catalyst getCatalyst() {
		return catalyst;
	}

	@Contract(pure = true)
	public boolean isLinked() {
		return link;
	}

	@Contract(pure = true)
	public int getPrice() {
		return price;
	}

	public static List<Block> getJarBlocks(boolean includeBlank) {
		return Registry.BLOCK.getEntries().stream()
				.filter(
						entry -> entry.getValue() instanceof ReagentJarBlock
								|| includeBlank && entry.getValue() instanceof JarBlock)
				.map(Map.Entry::getValue).collect(Collectors.toList());
	}

	@Nullable
	public static Reagents get(Catalyst catalyst) {
		return Arrays.stream(values()).filter(value -> value.getCatalyst().equals(catalyst))
				.findFirst().orElse(null);
	}

	@Nullable
	public static Reagents get(Reagent reagent) {
		return Arrays.stream(values()).filter(value -> value.getReagents().contains(reagent))
				.findFirst().orElse(null);
	}

	public static void load() {
	}
}
