package dm.earth.cabricality.client;

import java.util.Arrays;

import dm.earth.cabricality.Cabricality;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;
import dm.earth.cabricality.client.listener.ColorRegistryListener;
import dm.earth.cabricality.config.key.CabfKeyBinds;
import dm.earth.cabricality.content.alchemist.core.Substrate;
import dm.earth.cabricality.content.core.blocks.CasingBlockEntry;
import dm.earth.cabricality.content.core.blocks.MachineBlockEntry;
import dm.earth.cabricality.content.trading.util.ProfessionDebugHelper;
import dm.earth.cabricality.lib.util.PushUtil;
import dm.earth.cabricality.lib.util.ScreenUtil;
import dm.earth.cabricality.lib.util.SoundUtil;
import dm.earth.cabricality.network.CabfReceiver;
import dm.earth.cabricality.tweak.cutting.WoodCuttingEntry;
import dm.earth.cabricality.tweak.ore_processing.OreProcessingEntry;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;

public class CabricalityClient implements ClientModInitializer {
	public static String ID = Cabricality.ID + "Client";
	@Nullable
	private static RotatingCubeMapRenderer cubeMapRenderer;

	public static void finishLoading() {
		SoundUtil.playSounds(Cabricality.Sounds.FINISH_LOADING);
		GLFW.glfwRequestWindowAttention(MinecraftClient.getInstance().getWindow().getHandle());
	}

	@Override
	public void onInitializeClient() {
		CabfReceiver.registerClient();

		PushUtil.register();
		ScreenUtil.registerEvents();
		CabfKeyBinds.registerKenBinds();
		FluidRendererRegistry.renderFluidInit();

		ColorRegistryListener.load();
		ProfessionDebugHelper.load();

		WoodCuttingEntry.checkAll();
		OreProcessingEntry.checkAll();

		Arrays.stream(Substrate.getJarBlocks(true).toArray(Block[]::new))
						.forEach(block -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout()));

		Arrays.stream(MachineBlockEntry.values())
				.forEach(entry -> BlockRenderLayerMap.INSTANCE.putBlock(entry.getBlock(), entry.getLayer()));
		Arrays.stream(CasingBlockEntry.values())
				.forEach(entry -> BlockRenderLayerMap.INSTANCE.putBlock(entry.getBlock(), entry.getLayer()));

		// TODO: Resource loader api
		ResourceLoader.registerBuiltinResourcePack(Cabricality.id("asset_edits"),
				ResourcePackActivationType.DEFAULT_ENABLED,
				Cabricality.genTranslatableText("pack", "asset_edits"));
		ResourceLoader.registerBuiltinResourcePack(Cabricality.id("quests_lang"),
				ResourcePackActivationType.ALWAYS_ENABLED,
				Cabricality.genTranslatableText("pack", "quests_lang"));
	}

	public static void cubeMapRenderer(RotatingCubeMapRenderer cubeMapRenderer) {
		CabricalityClient.cubeMapRenderer = cubeMapRenderer;
	}

	@Nullable
	public static RotatingCubeMapRenderer cubeMapRenderer() {
		return cubeMapRenderer;
	}
}
