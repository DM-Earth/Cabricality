package com.dm.earth.cabricality.client;

import java.util.Arrays;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.listener.ColorRegistryListener;
import com.dm.earth.cabricality.config.key.CabfKeyBinds;
import com.dm.earth.cabricality.content.alchemist.core.Substrate;
import com.dm.earth.cabricality.content.core.blocks.CasingBlockEntry;
import com.dm.earth.cabricality.content.core.blocks.MachineBlockEntry;
import com.dm.earth.cabricality.content.trading.util.ProfessionDebugHelper;
import com.dm.earth.cabricality.tweak.cutting.WoodCuttingEntry;
import com.dm.earth.cabricality.tweak.ore_processing.OreProcessingEntry;
import com.dm.earth.cabricality.util.PushUtil;
import com.dm.earth.cabricality.util.ScreenUtil;
import com.dm.earth.cabricality.util.SoundUtil;
import com.dm.earth.cabricality.util.func.CabfBlur;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;

@ClientOnly
public class CabricalityClient implements ClientModInitializer {
	private static long initTime = -1;

	public static void finishLoading() {
		if (initTime != -1) {
			Cabricality.LOGGER.info("Cabricality has initialized in "
					+ (System.currentTimeMillis() - initTime) + "ms! ⚙️");
			initTime = -1;
		}
		SoundUtil.playSound(Cabricality.Sounds.FINISH_LOADING);
		GLFW.glfwRequestWindowAttention(MinecraftClient.getInstance().getWindow().getHandle());
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		initTime = System.currentTimeMillis();

		PushUtil.register();
		ScreenUtil.registerEvents();
		CabfKeyBinds.registerKenBinds();
		FluidRendererRegistry.renderFluidInit();

		ColorRegistryListener.load();
		ProfessionDebugHelper.load();

		CabfBlur.INSTANCE.init();

		WoodCuttingEntry.checkAll();
		OreProcessingEntry.checkAll();

		BlockRenderLayerMap.put(RenderLayer.getCutout(),
				Substrate.getJarBlocks(true).toArray(new Block[0]));

		Arrays.stream(MachineBlockEntry.values())
				.forEach(entry -> BlockRenderLayerMap.put(entry.getLayer(), entry.getBlock()));
		Arrays.stream(CasingBlockEntry.values())
				.forEach(entry -> BlockRenderLayerMap.put(entry.getLayer(), entry.getBlock()));

		ResourceLoader.registerBuiltinResourcePack(Cabricality.id("asset_edits"),
				ResourcePackActivationType.DEFAULT_ENABLED,
				Cabricality.genTranslatableText("pack", "asset_edits"));
		ResourceLoader.registerBuiltinResourcePack(Cabricality.id("quests_lang"),
				ResourcePackActivationType.ALWAYS_ENABLED,
				Cabricality.genTranslatableText("pack", "quests_lang"));
	}
}
