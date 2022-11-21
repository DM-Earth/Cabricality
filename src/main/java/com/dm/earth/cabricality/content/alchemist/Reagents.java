package com.dm.earth.cabricality.content.alchemist;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.IV;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.MLM;
import static com.dm.earth.cabricality.ModEntry.PMD;
import static com.dm.earth.cabricality.content.alchemist.substrate.Reagent.of;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.JarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.SubstrateJarBlock;
import com.dm.earth.cabricality.content.alchemist.substrate.Catalyst;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;

import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

//TODO: fill this out
public enum Reagents {
	IGNEOUS("igneous", 0x6c8191, 16, true,
			of("andesite", MC.id("andesite"), 0x868887),
			of("diorite", MC.id("diorite"), 0xe6e2e6),
			of("granite", MC.id("granite"), 0x9e6b5a),
			of("cobblestone", MC.id("cobblestone"), 0xa6a6a6),
			of("basalt", MC.id("basalt"), 0x32333D),
			of("tuff", MC.id("tuff"), 0x85837b),
			of("limestone", CR.id("limestone"), 0xbbb6a9),
			of("scoria", CR.id("scoria"), 0x493a34),
			of("blunite", PMD.id("blunite"), 0x5f6874),
			of("carbonite", PMD.id("carbonite"), 0x514e52)),

	HERBAL("herbal", 0xb5cda3, 1, true,
			of("white", MC.id("lily_of_the_valley"), 0xe8e8e8),
			of("orange", MC.id("orange_tulip"), 0xfd9e28),
			of("magenta", MC.id("allium"), 0xba63e2),
			of("light_blue", MC.id("blue_orchid"), 0x21c1fd),
			of("yellow", MC.id("dandelion"), 0xffec4e),
			of("pink", MC.id("pink_tulip"), 0xf7c8fe),
			of("light_gray", MC.id("white_tulip"), 0xd0e9e9),
			of("blue", MC.id("cornflower"), 0x2f6eec),
			of("red", MC.id("red_tulip"), 0xeb3334),
			of("black", MC.id("wither_rose"), 0x45322b)
	),

	VOLATILE("volatile", 0x9f5f80, 1, true,
			of("blaze", MC.id("blaze_powder"), 0xfda228),
			of("slime", MC.id("slime_ball"), 0x8cd382),
			of("nether", MC.id("nether_wart"), 0xa42733),
			of("obsidian", CR.id("powdered_obsidian"), 0x271f3c),
			of("gunpowder", MC.id("gunpowder"), 0x727272),
			of("prismarine", MC.id("prismarine_shard"), 0xa5d1c2),
			of("hex", MLM.id("hex_ash"), 0xa235bc),
			of("ender", AE2.id("ender_dust"), 0x38ccb1)
	),

	CRYSTAL("crystal", 0xffb037, 1, true,
			of("sulfur", IV.id("sulfur_dust"), 0xc7a94a),
			of("certus_quartz", AE2.id("certus_quartz_dust"), 0xbbdcfd),
			of("cinnabar", MC.id("redstone"), 0xe8364f),
			of("blazing_quartz", MLM.id("blazing_quartz"), 0xfee568),
			of("nether_quartz", MC.id("quartz"), 0xddd4c7)
	),

	CHAOTIC("chaos", 0xb200ed, 1, false,
			of("silver", IV.id("silver_dust"), 0x9fadb4),
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
		for (Reagents reagents : values())
			for (Reagent reagent : reagents.getReagents())
				if (reagent.hashString().equals(hashStr))
					return reagent;
		return null;
	}

	@Nullable
	public static Catalyst getCatalystFromHash(String hashStr) {
		for (Reagents reagents : values())
			if (reagents.getCatalyst().hashString().equals(hashStr))
				return reagents.getCatalyst();
		return null;
	}

	@NotNull
	public static Reagent getReagentFromBlock(ReagentJarBlock block) {
		Reagent reagent = getReagentFromHash(
				Registry.BLOCK
						.getId(block)
						.getPath()
						.replaceAll(
								block.getDefaultBlockId().getPath() + "_", ""
						)
		);
		// Check Content Validity
		if (reagent == null) {
			Cabricality.LOGGER.error(
					"Invalid Reagent " + Registry.BLOCK.getId(block) + "! Valid Reagents:"
							+ Arrays.stream(Reagents.values())
							.map(
									reagents -> reagents.getReagents()
											.stream()
											.map(
													value -> "\n" + value.getName()
															.getString()
											)
											.collect(Collectors.joining())
							)
							.collect(Collectors.joining())
			);
			throw new EnumConstantNotPresentException(Reagents.class, Registry.BLOCK.getId(block).toString());
		}
		return reagent;
	}

	@NotNull
	public static Catalyst getCatalystFromBlock(CatalystJarBlock block) {
		Catalyst catalyst = getCatalystFromHash(
				Registry.BLOCK
						.getId(block)
						.getPath()
						.replaceAll(
								block.getDefaultBlockId().getPath() + "_", ""
						)
		);
		// Check Content Validity
		if (catalyst == null) {
			Cabricality.LOGGER.error(
					"Invalid Catalyst" + Registry.BLOCK.getId(block) + "! Valid Catalysts:"
							+ Arrays.stream(Reagents.values())
							.map(value -> "\n" + value.getCatalyst().toString())
							.collect(Collectors.joining())
			);
			throw new EnumConstantNotPresentException(Reagents.class, Registry.BLOCK.getId(block).toString());
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
		ArrayList<Block> list = new ArrayList<>();
		for (Map.Entry<RegistryKey<Block>, Block> set : Registry.BLOCK.getEntries()) {
			Block block = set.getValue();
			if (block instanceof SubstrateJarBlock || (includeBlank && block instanceof JarBlock))
				list.add(block);
		}
		return list;
	}

	@Nullable
	public static Reagents get(Catalyst catalyst) {
		for (Reagents reagents : values())
			if (reagents.getCatalyst().equals(catalyst))
				return reagents;
		return null;
	}

	@Nullable
	public static Reagents get(Reagent reagent) {
		for (Reagents reagents : values())
			for (Reagent reagent1 : reagents.getReagents())
				if (reagent1.equals(reagent))
					return reagents;
		return null;
	}

	public static void load() {
	}
}
