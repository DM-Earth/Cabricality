package com.dm.earth.cabricality.client;

import static com.dm.earth.cabricality.Cabricality.ID;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.listener.ColorRegistryListener;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.threads.blocks.MachineBlockEntry;
import com.dm.earth.cabricality.content.trading.util.ProfessionDebugHelper;
import com.dm.earth.cabricality.tweak.cutting.WoodCuttingEntry;
import com.dm.earth.cabricality.tweak.ore_processing.OreProcessingEntry;
import com.dm.earth.cabricality.util.ModChecker;

import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.TranslatableText;

public class CabricalityClient implements ClientModInitializer {

	public static String genTranslationKey(String type, String... path) {
		return type + "." + ID + Arrays.stream(path).map(p -> "." + p).collect(Collectors.joining());
	}

	public static TranslatableText genTranslatableText(String type, String... path) {
		return new TranslatableText(genTranslationKey(type, path));
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		ModChecker.check();
		FluidRendererRegistry.renderFluidInit();
		ColorRegistryListener.load();
		ProfessionDebugHelper.load();

		WoodCuttingEntry.checkAll();
		OreProcessingEntry.checkAll();

		BlockRenderLayerMap.put(RenderLayer.getCutout(), Reagents.getJarBlocks(true).toArray(new Block[0]));
		for (MachineBlockEntry entry : MachineBlockEntry.values())
			BlockRenderLayerMap.put(entry.getLayer(), entry.getBlock());

		ResourceLoader.registerBuiltinResourcePack(Cabricality.id("asset_edits"),
				ResourcePackActivationType.DEFAULT_ENABLED, genTranslatableText("pack", "asset_edits"));
		ResourceLoader.registerBuiltinResourcePack(Cabricality.id("quests_lang"),
				ResourcePackActivationType.ALWAYS_ENABLED, genTranslatableText("pack", "quests_lang"));
	}
}
