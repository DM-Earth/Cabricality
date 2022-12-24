package com.dm.earth.cabricality.client;

import com.dm.earth.cabricality.util.ModDownloader;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.listener.ColorRegistryListener;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.core.blocks.CasingBlockEntry;
import com.dm.earth.cabricality.content.core.blocks.MachineBlockEntry;
import com.dm.earth.cabricality.content.trading.util.ProfessionDebugHelper;
import com.dm.earth.cabricality.tweak.cutting.WoodCuttingEntry;
import com.dm.earth.cabricality.tweak.ore_processing.OreProcessingEntry;
import com.dm.earth.cabricality.util.ModChecker;

import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import java.util.Arrays;

public class CabricalityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		FluidRendererRegistry.renderFluidInit();
		ColorRegistryListener.load();
		ProfessionDebugHelper.load();

		WoodCuttingEntry.checkAll();
		OreProcessingEntry.checkAll();

		BlockRenderLayerMap.put(RenderLayer.getCutout(), Reagents.getJarBlocks(true).toArray(new Block[0]));
		for (MachineBlockEntry entry : MachineBlockEntry.values())
			BlockRenderLayerMap.put(entry.getLayer(), entry.getBlock());

		for (CasingBlockEntry entry : CasingBlockEntry.values())
			BlockRenderLayerMap.put(entry.getLayer(), entry.getBlock());

		ResourceLoader.registerBuiltinResourcePack(Cabricality.id("asset_edits"),
				ResourcePackActivationType.DEFAULT_ENABLED, Cabricality.genTranslatableText("pack", "asset_edits"));
		ResourceLoader.registerBuiltinResourcePack(Cabricality.id("quests_lang"),
				ResourcePackActivationType.ALWAYS_ENABLED, Cabricality.genTranslatableText("pack", "quests_lang"));
	}
}
