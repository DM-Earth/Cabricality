package dm.earth.cabricality.content.entries;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.fluids.MoltenMetalFluid;
import dm.earth.cabricality.content.fluids.NumberFluid;
import dm.earth.cabricality.content.fluids.PoweredWater;
import dm.earth.cabricality.content.fluids.core.BaseFluid;
import dm.earth.cabricality.content.fluids.core.IFluid;
import dm.earth.cabricality.lib.resource.assets.gen.fluid.FluidBlockStatesGenerator;
import dm.earth.cabricality.lib.resource.assets.gen.fluid.FluidModelGenerator;
import com.dm.earth.tags_binder.api.LoadTagsCallback;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static dm.earth.cabricality.ModEntry.C;

public class CabfFluids implements LoadTagsCallback<Fluid> {
	// Fluids
	public static final List<Fluid> NUMBERS = getNumberFluids();
	public static final Fluid RESIN = new BaseFluid("resin");
	public static final Fluid REDSTONE = new BaseFluid("redstone");
	public static final Fluid WASTE = new BaseFluid("waste");
	public static final Fluid SKY_STONE = new BaseFluid("sky_stone");
	public static final Fluid COKE = new BaseFluid("coke");
	public static final Fluid FINE_SAND = new BaseFluid("fine_sand");
	public static final Fluid MATRIX = new BaseFluid("matrix") {
		@Override
		public boolean hasBucketItem() {
			return false;
		}
	};
	public static final Fluid RAW_LOGIC = new BaseFluid("raw_logic") {
		@Override
		public boolean hasBucketItem() {
			return false;
		}
	};

	// Flowable Fluids
	public static final FlowableFluid POWERED_WATER = new PoweredWater.Still();
	public static final FlowableFluid POWERED_WATER_FLOWING = new PoweredWater.Flowing();
	public static final FlowableFluid MOLTEN_DESH = new MoltenMetalFluid.Still("desh", 0xc88448);
	public static final FlowableFluid MOLTEN_DESH_FLOWING =
			new MoltenMetalFluid.Flowing("desh", 0xc88448);
	public static final FlowableFluid MOLTEN_OSTRUM =
			new MoltenMetalFluid.Still("ostrum", 0x6c4c59);
	public static final FlowableFluid MOLTEN_OSTRUM_FLOWING =
			new MoltenMetalFluid.Flowing("ostrum", 0x6c4c59);
	public static final FlowableFluid MOLTEN_CALORITE =
			new MoltenMetalFluid.Still("calorite", 0x931d3b);
	public static final FlowableFluid MOLTEN_CALORITE_FLOWING =
			new MoltenMetalFluid.Flowing("calorite", 0x931d3b);

	public static void register() {
		registerIFluids(RESIN, REDSTONE, WASTE, SKY_STONE, COKE, FINE_SAND, MATRIX, RAW_LOGIC);
		registerIFluids(POWERED_WATER, POWERED_WATER_FLOWING);
		registerIFluids(MOLTEN_DESH, MOLTEN_DESH_FLOWING, MOLTEN_OSTRUM, MOLTEN_OSTRUM_FLOWING,
				MOLTEN_CALORITE, MOLTEN_CALORITE_FLOWING);
		registerIFluids(NUMBERS);

		LoadTagsCallback.FLUID.register(new CabfFluids());
	}

	private static List<Fluid> getNumberFluids() {
		List<Integer> colors = List.of(0xCBE827, 0xAEE827, 0x68E827, 0x27E86E, 0x27E8B1, 0x27DEE8,
				0x27B5E8, 0x2798E8, 0x2778E8, 0x2748E8);
		ArrayList<Fluid> numbers = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			numbers.add(new NumberFluid(i).color(colors.get(i)));
		return numbers;
	}

	private static void registerFluid(Identifier id, Identifier stillId, Fluid fluid) {
		Registry.register(Registries.FLUID, id, fluid);
		if (!fluid.isSource(null)) CabfBlocks.registerFluidBlock(stillId, (FlowableFluid) fluid);
	}

	private static void registerIFluid(Fluid fluid) {
		IFluid iFluid = (IFluid) fluid;
		registerFluid(iFluid.getId(), ((IFluid) iFluid.getTypical()).getId(), fluid);
		iFluid.registerBucketItem(Registries.ITEM);
		/*
		if (fluid.isSource(null)) {
			Cabricality.RRPs.CLIENT_RESOURCES.addBlockState(
					iFluid.getId(),
					FluidBlockStatesGenerator.simple(iFluid.getName())
			);
			Cabricality.RRPs.CLIENT_RESOURCES.addBlockState(
					Cabricality.id(iFluid.getName() + "_flowing"),
					FluidBlockStatesGenerator.simple(iFluid.getName())
			);
			Cabricality.RRPs.CLIENT_RESOURCES.addModel(
					Cabricality.id("block/fluid/" + iFluid.getName()),
					FluidModelGenerator.simple(iFluid.getTextureName() + "_still",
							iFluid.getTextureName())
			);
		}

		 */
	}

	private static void registerIFluids(Fluid... fluids) {
		for (Fluid fluid : fluids)
			registerIFluid(fluid);
	}

	private static void registerIFluids(List<Fluid> fluids) {
		for (Fluid fluid : fluids)
			registerIFluid(fluid);
	}

	@Override
	public void onTagsLoad(TagHandler<Fluid> handler) {
		handler.register(C.id("molten_desh"), MOLTEN_DESH);
		handler.register(C.id("molten_ostrum"), MOLTEN_OSTRUM);
		handler.register(C.id("molten_calorite"), MOLTEN_CALORITE);
	}
}
