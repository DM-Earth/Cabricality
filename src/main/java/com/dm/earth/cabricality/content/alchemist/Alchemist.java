package com.dm.earth.cabricality.content.alchemist;

import static com.dm.earth.cabricality.lib.util.debug.CabfDebugger.debug;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.alchemist.core.Catalyst;
import com.dm.earth.cabricality.content.alchemist.core.Reagent;
import com.dm.earth.cabricality.content.alchemist.data.JarData;
import com.dm.earth.cabricality.content.alchemist.laser.LaserCore;
import com.dm.earth.cabricality.content.alchemist.laser.LaserProperties;
import com.dm.earth.cabricality.lib.math.RandomMathUtil;

import net.darktree.led.LED;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.Registry;

public class Alchemist {
	public static final int MAX_REAGENT_JARS = 3;
	public static final Catalyst CHAOTIC_CATALYST = Reagents.CHAOTIC.getCatalyst();

	public static void load() {
		Reagents.load();
		LaserCore.load();
		JarData.load();
	}

	public static void processChaoticRecipe(@NotNull HopperMinecartEntity minecart,
			LaserProperties properties) {
		if (!(minecart.getWorld() instanceof ServerWorld world))
			return;

		HashMap<Integer, Reagent> reagents = new HashMap<>();
		HashMap<Integer, Catalyst> catalysts = new HashMap<>();

		// Scan the minecart for reagents and catalysts
		for (int i = 0; i < 5; i++) {
			ItemStack stack = minecart.getStack(i);
			if (stack.isEmpty())
				continue;
			if (stack.getItem() instanceof BlockItem blockItem) {
				Block block = blockItem.getBlock();
				if (block instanceof ReagentJarBlock jar)
					reagents.put(i, (Reagent) jar.getSubstrate());
				if (block instanceof CatalystJarBlock jar)
					catalysts.put(i, (Catalyst) jar.getSubstrate());
			}
		}

		HashMap<Integer, ItemStack> addSlots = new HashMap<>();
		ArrayList<Reagent> reagentsList = mapToList(reagents);

		boolean success = false;

		// Reagents -> Catalyst
		if (catalysts.size() == 0) {
			debug("Initializing - Reagents -> Catalyst");

			Map<Catalyst, ArrayList<Reagent>> possibleReagentMap = possibleReagentMap(world);
			Catalyst catalystTargetTemp = getMatchedCatalyst(reagentsList, possibleReagentMap);
			debug(reagentsList.toString());

			if (catalystTargetTemp != null) {
				ArrayList<Integer> tempList = new ArrayList<Integer>(reagents.keySet());
				tempList.sort(null);
				addSlots.put(tempList.get(0), new ItemStack(Registry.ITEM
						.get(Cabricality.id("catalyst_jar_" + catalystTargetTemp.hashString()))));
				success = true;
			} else if (reagentsList.size() > 0 && catalysts.isEmpty()) {
				// Reagents -> Dusts
				Catalyst targetCatalyst = Reagents.get(reagentsList.get(0)).getCatalyst();
				boolean canContinue = true;
				for (Reagent reagent : reagentsList)
					if (Reagents.get(reagent).getCatalyst() != targetCatalyst)
						canContinue = false;
				if (canContinue) {
					ArrayList<Reagent> expected = possibleReagentMap.get(targetCatalyst);
					int correct = 0;
					int wrongPos = 0;
					for (Reagent reagent : expected) {
						if (reagentsList.contains(reagent))
							if (reagentsList.indexOf(reagent) == expected.indexOf(reagent))
								correct++;
							else
								wrongPos++;
					}
					ArrayList<Integer> tempList = new ArrayList<Integer>(reagents.keySet());
					tempList.sort(null);
					if (correct > 0)
						addSlots.put(tempList.get(0), new ItemStack(Items.REDSTONE, correct));
					if (wrongPos > 0)
						addSlots.put(tempList.get(correct > 0 ? 1 : 0),
								new ItemStack(LED.SHADE, wrongPos));
					success = true;
				}
			}
		}

		// Catalysts -> Chaotic Catalyst
		if (!success && reagents.isEmpty() && catalysts.size() > 1) {
			debug("Initializing - Catalysts -> Chaotic Catalyst");

			ArrayList<Catalyst> existed = mapToList(catalysts);
			ArrayList<Catalyst> expected = possibleChaoticCatalystList(world);
			ArrayList<Integer> tempList = new ArrayList<Integer>(catalysts.keySet());
			tempList.sort(null);
			if (existed.equals(expected)) {
				addSlots.put(tempList.get(0), new ItemStack(Registry.ITEM
						.get(Cabricality.id("catalyst_jar_" + CHAOTIC_CATALYST.hashString()))));
				success = true;
			} else if (!existed.contains(CHAOTIC_CATALYST)) {
				int correct = 0;
				int wrongPos = 0;
				for (Catalyst catalyst : expected)
					if (existed.contains(catalyst))
						if (existed.indexOf(catalyst) == expected.indexOf(catalyst))
							correct++;
						else
							wrongPos++;
				if (correct > 0)
					addSlots.put(tempList.get(0), new ItemStack(Items.REDSTONE, correct));
				if (wrongPos > 0)
					addSlots.put(tempList.get(correct > 0 ? 1 : 0),
							new ItemStack(LED.SHADE, wrongPos));
				success = true;
			}

			if (success) {
				for (int i : catalysts.keySet())
					minecart.removeStack(i);
			}
		}

		// Chaotic Catalyst + Reagent -> Reagent
		if ((!success) && reagents.size() == 1 && catalysts.size() == 1) {
			boolean canContinue = true;

			for (var catalyst : catalysts.entrySet())
				if (catalyst.getValue() != CHAOTIC_CATALYST) {
					canContinue = false;
					break;
				}

			if (canContinue) {
				debug("Initializing - Chaotic Catalyst + Reagent -> Reagent");

				Reagent reagent = mapToList(reagents).get(0);
				Map<Reagent, Reagent> map = possibleSpecialReagentChaoticMap(world);
				ArrayList<Integer> tempList = new ArrayList<Integer>(reagents.keySet());
				for (Map.Entry<Reagent, Reagent> entry : map.entrySet())
					if (entry.getValue() == reagent) {
						addSlots.put(tempList.get(0), new ItemStack(Registry.ITEM.get(
								Cabricality.id("reagent_jar_" + entry.getKey().hashString()))));
						break;
					}
			}
		}

		for (Integer slot : reagents.keySet())
			minecart.removeStack(slot);
		for (var entry : addSlots.entrySet())
			minecart.setStack(entry.getKey(), entry.getValue());
	}

	private static <T> @NotNull ArrayList<T> mapToList(@NotNull Map<Integer, T> map) {
		return Arrays.stream((new ArrayList<>(map.keySet())).toArray(new Integer[0])).sorted()
				.collect(ArrayList::new, (list, i) -> list.add(map.get(i)), ArrayList::addAll);
	}

	public static Map<Reagent, Reagent> possibleSpecialReagentChaoticMap(ServerWorld world) {
		ArrayList<Reagent> reagents = Arrays.stream(Reagents.values()).filter(Reagents::isLinked)
				.flatMap(reagentsT -> reagentsT.getReagents().stream())
				.collect(Collectors.toCollection(ArrayList::new));
		AtomicInteger i = new AtomicInteger(0);
		return Reagents.CHAOTIC.getReagents().stream().map(reagent -> {
			Random random = new Random(world.getSeed() + i.getAndIncrement());
			return new AbstractMap.SimpleEntry<>(reagent,
					reagents.get(random.nextInt(reagents.size())));
		}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public static @NotNull ArrayList<Catalyst> possibleChaoticCatalystList(ServerWorld world) {
		return RandomMathUtil.randomSelect(
				Arrays.stream(Reagents.values()).filter(Reagents::isLinked)
						.map(Reagents::getCatalyst)
						.collect(Collectors.toCollection(ArrayList::new)),
				MAX_REAGENT_JARS, world.getSeed());
	}

	@Nullable
	private static Catalyst getMatchedCatalyst(ArrayList<Reagent> existed,
			@NotNull Map<Catalyst, ArrayList<Reagent>> map) {
		return map.entrySet().stream().filter(entry -> entry.getValue().equals(existed)).findFirst()
				.map(Map.Entry::getKey).orElse(null);
	}

	public static @NotNull Map<Catalyst, ArrayList<Reagent>> possibleReagentMap(
			ServerWorld world) {
		return Arrays.stream(Reagents.values()).filter(Reagents::isLinked)
				.collect(Collectors.toMap(Reagents::getCatalyst, reagentsT -> {
					List<Reagent> reagents = reagentsT.getReagents();
					return RandomMathUtil.randomSelect(reagents, MAX_REAGENT_JARS, world.getSeed());
				}));
	}
}
