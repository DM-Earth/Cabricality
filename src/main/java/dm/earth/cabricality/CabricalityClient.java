package dm.earth.cabricality;

import java.util.Arrays;

import dm.earth.cabricality.client.FluidRendererRegistry;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
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

@ClientOnly
public class CabricalityClient implements ClientModInitializer {
	public static String ID = Cabricality.ID + "Client";
	@Nullable
	private static RotatingCubeMapRenderer cubeMapRenderer;

	public static void finishLoading() {
		SoundUtil.playSounds(Cabricality.Sounds.FINISH_LOADING);
		GLFW.glfwRequestWindowAttention(MinecraftClient.getInstance().getWindow().getHandle());
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		CabfReceiver.registerClient();

		PushUtil.register();
		ScreenUtil.registerEvents();
		CabfKeyBinds.registerKenBinds();
		FluidRendererRegistry.renderFluidInit();

		ColorRegistryListener.load();
		ProfessionDebugHelper.load();

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

	public static void cubeMapRenderer(RotatingCubeMapRenderer cubeMapRenderer) {
		CabricalityClient.cubeMapRenderer = cubeMapRenderer;
	}

	@Nullable
	public static RotatingCubeMapRenderer cubeMapRenderer() {
		return cubeMapRenderer;
	}
}
