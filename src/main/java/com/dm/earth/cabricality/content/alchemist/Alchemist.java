package com.dm.earth.cabricality.content.alchemist;

import static com.dm.earth.cabricality.util.CabfDebugger.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.alchemist.data.JarData;
import com.dm.earth.cabricality.content.alchemist.laser.LaserCore;
import com.dm.earth.cabricality.content.alchemist.laser.LaserProperties;
import com.dm.earth.cabricality.content.alchemist.substrate.Catalyst;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;
import com.dm.earth.cabricality.util.RandomMathUtil;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.darktree.led.LED;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.registry.Registry;

public class Alchemist {
	public static final int MAX_REAGENT_JARS = 3;
	public static final Catalyst CHAOTIC_CATALYST = Reagents.CHAOTIC.getCatalyst();

	public static void load() {
		Reagents.load();
		LaserCore.load();
		JarData.load();
	}

	public static void processChaoticRecipe(@NotNull HopperMinecartEntity minecart, LaserProperties properties) {
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
		if ((!success) && catalysts.size() == 0) {
			debug("Init Reagents -> Catalyst");

			Map<Catalyst, ArrayList<Reagent>> possibleReagentMap = possibleReagentMap(world);
			Catalyst catalystTargetTemp = getMatchedCatalyst(reagentsList, possibleReagentMap);
			debug(reagentsList.toString());

			if (catalystTargetTemp != null) {
				ArrayList<Integer> tempList = new ArrayList<Integer>(reagents.keySet());
				tempList.sort(null);
				addSlots.put(tempList.get(0),
						new ItemStack(
								Registry.ITEM.get(Cabricality.id("catalyst_jar_" + catalystTargetTemp.hashString()))));
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
						addSlots.put(tempList.get(correct > 0 ? 1 : 0), new ItemStack(LED.SHADE, wrongPos));
					success = true;
				}
			}
		}

		// Catalysts -> Chaotic Catalyst
		if (!success && reagents.isEmpty() && catalysts.size() > 1) {
			debug("Init Catalysts -> Chaotic Catalyst");

			ArrayList<Catalyst> existed = mapToList(catalysts);
			ArrayList<Catalyst> expected = possibleChaoticCatalystList(world);
			ArrayList<Integer> tempList = new ArrayList<Integer>(catalysts.keySet());
			tempList.sort(null);
			if (existed.equals(expected)) {
				addSlots.put(tempList.get(0), new ItemStack(
						Registry.ITEM.get(Cabricality.id("catalyst_jar_" + CHAOTIC_CATALYST.hashString()))));
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
					addSlots.put(tempList.get(correct > 0 ? 1 : 0), new ItemStack(LED.SHADE, wrongPos));
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
				if (catalyst.getValue() != CHAOTIC_CATALYST)
					canContinue = false;

			if (canContinue) {
				debug("Init Chaotic Catalyst + Reagent -> Reagent");

				Reagent reagent = mapToList(reagents).get(0);
				Map<Reagent, Reagent> map = possibleSpecialReagentChaoticMap(world);
				ArrayList<Integer> tempList = new ArrayList<Integer>(reagents.keySet());
				for (Map.Entry<Reagent, Reagent> entry : map.entrySet())
					if (entry.getValue() == reagent) {
						addSlots.put(tempList.get(0),
								new ItemStack(
										Registry.ITEM
												.get(Cabricality.id("reagent_jar_" + entry.getKey().hashString()))));
						success = true;
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
		ArrayList<T> list = new ArrayList<>();
		Integer[] ints = (new ArrayList<>(map.keySet())).toArray(new Integer[0]);
		Arrays.sort(ints);
		for (int i : ints)
			list.add(map.get(i));
		return list;
	}

	private static Map<Reagent, Reagent> possibleSpecialReagentChaoticMap(ServerWorld world) {
		HashMap<Reagent, Reagent> map = new HashMap<>();
		ArrayList<Reagent> reagents = new ArrayList<>();
		for (Reagents reagentsT : Reagents.values())
			if (reagentsT.isLinked())
				reagents.addAll(reagentsT.getReagents());

		int i = 0;
		for (Reagent reagent : Reagents.CHAOTIC.getReagents()) {
			i++;
			Random random = new Random(world.getSeed() + i);
			map.put(reagent, reagents.get(random.nextInt(reagents.size())));
		}
		return map;
	}

	private static @NotNull ArrayList<Catalyst> possibleChaoticCatalystList(ServerWorld world) {
		ArrayList<Catalyst> list = new ArrayList<>();
		for (Reagents reagents : Reagents.values())
			if (reagents.isLinked())
				list.add(reagents.getCatalyst());
		return RandomMathUtil.randomSelect(list, MAX_REAGENT_JARS, world.getSeed());
	}

	@Nullable
	private static Catalyst getMatchedCatalyst(ArrayList<Reagent> existed,
			@NotNull Map<Catalyst, ArrayList<Reagent>> map) {
		for (var entry : map.entrySet())
			if (entry.getValue().equals(existed))
				return entry.getKey();
		return null;
	}

	private static @NotNull Map<Catalyst, ArrayList<Reagent>> possibleReagentMap(ServerWorld world) {
		HashMap<Catalyst, ArrayList<Reagent>> map = new HashMap<>();
		for (Reagents reagentsEntry : Reagents.values()) {
			if (!reagentsEntry.isLinked())
				continue;
			map.put(reagentsEntry.getCatalyst(),
					RandomMathUtil.randomSelect(reagentsEntry.getReagents(), MAX_REAGENT_JARS, world.getSeed()));
		}
		return map;
	}

	public static class AlchemistInformationCommand implements Command<ServerCommandSource> {

		@Override
		public int run(@NotNull CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
			ServerWorld world = context.getSource().getWorld();
			Map<Catalyst, ArrayList<Reagent>> reagentMap = possibleReagentMap(world);
			ArrayList<String> output = new ArrayList<>();
			for (var entry : reagentMap.entrySet())
				output.add(entry.getKey().toString() + " -> " + entry.getValue().toString());

			output.add(CHAOTIC_CATALYST.toString() + " -> " + possibleChaoticCatalystList(world).toString());

			for (var entry : possibleSpecialReagentChaoticMap(world).entrySet())
				output.add(entry.getKey().toString() + " -> (Chaotic) " + entry.getValue().toString());

			for (String string : output)
				context.getSource().sendFeedback(Text.of(string), false);

			return SINGLE_SUCCESS;
		}
	}
}
