package dm.earth.cabricality.content.alchemist;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import dm.earth.cabricality.content.alchemist.block.JarBlock;
import dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import dm.earth.cabricality.content.alchemist.core.Catalyst;
import dm.earth.cabricality.content.alchemist.core.Reagent;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dm.earth.cabricality.ModEntry.AE2;
import static dm.earth.cabricality.ModEntry.CABF;
import static dm.earth.cabricality.ModEntry.CREATE;
import static dm.earth.cabricality.ModEntry.INDREV;
import static dm.earth.cabricality.ModEntry.MC;
import static dm.earth.cabricality.ModEntry.MLM;
import static dm.earth.cabricality.ModEntry.PROMENADE;

public enum Reagents {
	IGNEOUS("igneous", 0x6C8191, 16, true,
			Reagent.of("andesite", MC.id("andesite"), 0x868887),
			Reagent.of("diorite", MC.id("diorite"), 0xE6E2E6),
			Reagent.of("granite", MC.id("granite"), 0x9E6B5A),
			Reagent.of("cobblestone", MC.id("cobblestone"), 0xA6A6A6),
			Reagent.of("basalt", MC.id("basalt"), 0x32333D),
			Reagent.of("tuff", MC.id("tuff"), 0x85837B),
			Reagent.of("limestone", CREATE.id("limestone"), 0xBBB6A9),
			Reagent.of("scoria", CREATE.id("scoria"), 0x493A34),
			Reagent.of("blunite", PROMENADE.id("blunite"), 0x5F6874),
			Reagent.of("carbonite", PROMENADE.id("carbonite"), 0x514E52)),

	HERBAL("herbal", 0xB5CDA3, 1, true,
			Reagent.of("white", MC.id("lily_of_the_valley"), 0xE8E8E8),
			Reagent.of("orange", MC.id("orange_tulip"), 0xFD9E28),
			Reagent.of("magenta", MC.id("allium"), 0xBA63E2),
			Reagent.of("light_blue", MC.id("blue_orchid"), 0x21C1FD),
			Reagent.of("yellow", MC.id("dandelion"), 0xFFEC4E),
			Reagent.of("pink", MC.id("pink_tulip"), 0xF7C8FE),
			Reagent.of("light_gray", MC.id("white_tulip"), 0xD0E9E9),
			Reagent.of("blue", MC.id("cornflower"), 0x2F6EEC),
			Reagent.of("red", MC.id("red_tulip"), 0xEB3334),
			Reagent.of("black", MC.id("wither_rose"), 0x45322B)),

	VOLATILE("volatile", 0x9F5F80, 1, true,
			Reagent.of("blaze", MC.id("blaze_powder"), 0xFDA228),
			Reagent.of("slime", MC.id("slime_ball"), 0x8CD382),
			Reagent.of("nether", MC.id("nether_wart"), 0xA42733),
			Reagent.of("obsidian", CREATE.id("powdered_obsidian"), 0x271F3C),
			Reagent.of("gunpowder", MC.id("gunpowder"), 0x727272),
			Reagent.of("prismarine", MC.id("prismarine_shard"), 0xA5D1C2),
			Reagent.of("hex", MLM.id("hex_ash"), 0xA235BC),
			Reagent.of("ender", AE2.id("ender_dust"), 0x38CCB1)),

	CRYSTAL("crystal", 0xffb037, 1, true,
			Reagent.of("sulfur", INDREV.id("sulfur_dust"), 0xc7a94a),
			Reagent.of("certus_quartz", AE2.id("certus_quartz_dust"), 0xbbdcfd),
			Reagent.of("cinnabar", MC.id("redstone"), 0xe8364f),
			Reagent.of("blazing_quartz", MLM.id("blazing_quartz"), 0xfee568),
			Reagent.of("nether_quartz", MC.id("quartz"), 0xddd4c7)),

	METAL("metal", 0xc9c9c9, 1, true,
			Reagent.of("iron", INDREV.id("iron_dust"), 0xd1d1d1),
			Reagent.of("gold", INDREV.id("gold_dust"), 0xeed73c),
			Reagent.of("copper", INDREV.id("copper_dust"), 0xe7763a),
			Reagent.of("tin", INDREV.id("tin_dust"), 0xb6b9ba),
			Reagent.of("lead", INDREV.id("lead_dust"), 0x313150),
			Reagent.of("zinc", CABF.id("zinc_dust"), 0xaecbb9),
			Reagent.of("nickel", CABF.id("nickel_dust"), 0xd9b669)),

	CHAOTIC("chaos", 0xb200ed, 1, false,
			Reagent.of("silver", INDREV.id("silver_dust"), 0x9fadb4),
			Reagent.of("silicon", AE2.id("silicon"), 0x85837b));

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
		Reagent reagent = getReagentFromHash(Registries.BLOCK.getId(block).getPath()
				.replaceAll(block.getDefaultBlockId().getPath() + "_", ""));

		// Check Content Validity
		if (reagent == null) {
			Cabricality.LOGGER.error("Invalid Reagent " + Registries.BLOCK.getId(block)
					+ "! Valid Reagents:"
					+ Arrays.stream(Reagents.values())
							.map(reagents -> reagents.getReagents().stream()
									.map(value -> "\n" + value.getName().getString())
									.collect(Collectors.joining()))
							.collect(Collectors.joining()));

			throw new EnumConstantNotPresentException(
					Reagents.class,
					Registries.BLOCK.getId(block).toString()
			);
		}
		return reagent;
	}

	@NotNull
	public static Catalyst getCatalystFromBlock(CatalystJarBlock block) {
		Catalyst catalyst = getCatalystFromHash(Registries.BLOCK.getId(block).getPath()
				.replaceAll(block.getDefaultBlockId().getPath() + "_", ""));

		// Check Content Validity
		if (catalyst == null) {
			Cabricality.LOGGER
					.error("Invalid Catalyst" + Registries.BLOCK.getId(block) + "! Valid Catalysts:"
							+ Arrays.stream(Reagents.values())
									.map(value -> "\n" + value.getCatalyst().toString())
									.collect(Collectors.joining()));

			throw new EnumConstantNotPresentException(
					Reagents.class,
					Registries.BLOCK.getId(block).toString()
			);
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
		return Registries.BLOCK.getEntries().stream()
				.filter(entry ->
						entry.getValue() instanceof ReagentJarBlock
								|| includeBlank && entry.getValue() instanceof JarBlock)
				.map(Map.Entry::getValue).collect(Collectors.toList());
	}

	@Nullable
	public static Reagents get(Catalyst catalyst) {
		return Arrays.stream(values())
				.filter(value -> value.getCatalyst().equals(catalyst))
				.findFirst().orElse(null);
	}

	@Nullable
	public static Reagents get(Reagent reagent) {
		return Arrays.stream(values())
				.filter(value -> value.getReagents().contains(reagent))
				.findFirst().orElse(null);
	}

	public static void load() {}
}
