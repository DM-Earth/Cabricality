package dm.earth.cabricality.client;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.fluids.core.IFluid;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;

import static dm.earth.cabricality.content.entries.CabfFluids.COKE;
import static dm.earth.cabricality.content.entries.CabfFluids.FINE_SAND;
import static dm.earth.cabricality.content.entries.CabfFluids.MATRIX;
import static dm.earth.cabricality.content.entries.CabfFluids.MOLTEN_CALORITE;
import static dm.earth.cabricality.content.entries.CabfFluids.MOLTEN_CALORITE_FLOWING;
import static dm.earth.cabricality.content.entries.CabfFluids.MOLTEN_DESH;
import static dm.earth.cabricality.content.entries.CabfFluids.MOLTEN_DESH_FLOWING;
import static dm.earth.cabricality.content.entries.CabfFluids.MOLTEN_OSTRUM;
import static dm.earth.cabricality.content.entries.CabfFluids.MOLTEN_OSTRUM_FLOWING;
import static dm.earth.cabricality.content.entries.CabfFluids.NUMBERS;
import static dm.earth.cabricality.content.entries.CabfFluids.POWERED_WATER;
import static dm.earth.cabricality.content.entries.CabfFluids.POWERED_WATER_FLOWING;
import static dm.earth.cabricality.content.entries.CabfFluids.RAW_LOGIC;
import static dm.earth.cabricality.content.entries.CabfFluids.REDSTONE;
import static dm.earth.cabricality.content.entries.CabfFluids.RESIN;
import static dm.earth.cabricality.content.entries.CabfFluids.SKY_STONE;
import static dm.earth.cabricality.content.entries.CabfFluids.WASTE;

public class FluidRendererRegistry {
	public static void register(String name, String texture, Fluid still, Fluid flowing, boolean flow) {
		int color = FluidColorRegistry.get(name);
		Identifier stillId = Cabricality.id("fluid/" + texture + "/" + texture + "_still");
		Identifier flowingId = Cabricality.id("fluid/" + texture + "/" + texture + "_flowing");

		/*
		ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE)
				.register((atlasTexture, registry) -> {
					registry.register(stillId);
					registry.register(flowingId);
				});

		 */

		Sprite[] fluidSprites = {null, null};
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public @NotNull Identifier getFabricId() {
				return Cabricality.id(name + "_fluid_renderer_reloader");
			}

			@Override
			public void reload(ResourceManager manager) {
				final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance()
						.getSpriteAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
				fluidSprites[0] = atlas.apply(stillId);
				fluidSprites[1] = atlas.apply(flowingId);
			}
		});

		FluidRenderHandler handler = new FluidRenderHandler() {
			@Override
			public Sprite[] getFluidSprites(@Nullable BlockRenderView view, @Nullable BlockPos pos, FluidState state) {
				return fluidSprites;
			}

			@Override
			public int getFluidColor(@Nullable BlockRenderView view, @Nullable BlockPos pos, FluidState state) {
				return color < 0 ? FluidRenderHandler.super.getFluidColor(view, pos, state) : color;
			}
		};

		FluidRenderHandlerRegistry.INSTANCE.register(still, flowing, handler);
		if (flow)
			BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), still, flowing);
		else
			BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), still);
	}

	public static void register(String name, Fluid still, Fluid flowing, boolean flow) {
		register(name, name, still, flowing, flow);
	}

	public static void renderFluidInit() {
		renderFluids(RESIN, REDSTONE, WASTE, SKY_STONE, COKE, FINE_SAND, MATRIX, RAW_LOGIC);
		renderFluids(POWERED_WATER, POWERED_WATER_FLOWING);
		renderFluids(MOLTEN_DESH, MOLTEN_DESH_FLOWING, MOLTEN_OSTRUM, MOLTEN_OSTRUM_FLOWING,
				MOLTEN_CALORITE, MOLTEN_CALORITE_FLOWING);
		renderFluids(NUMBERS);
	}

	private static void renderFluid(Fluid fluid) {
		if (fluid.isSource(null)) {
			IFluid iFluid = (IFluid) fluid;
			iFluid.setupRendering();
		}
	}

	private static void renderFluids(Fluid... fluids) {
		for (Fluid fluid : fluids)
			renderFluid(fluid);
	}

	private static void renderFluids(List<Fluid> fluids) {
		for (Fluid fluid : fluids)
			renderFluid(fluid);
	}
}
