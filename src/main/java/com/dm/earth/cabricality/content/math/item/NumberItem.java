package com.dm.earth.cabricality.content.math.item;

import org.jetbrains.annotations.Nullable;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.items.GlintedItem;
import com.dm.earth.cabricality.content.fluids.NumberFluid;
import com.dm.earth.cabricality.content.math.core.CalculationNumber;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NumberItem extends GlintedItem implements CalculationNumber {

	public NumberItem(Settings settings) {
		super(settings);
	}

	@Override
	public int getNumber() {
		return Integer.valueOf(
				Registry.ITEM.getId(this).getPath().replaceAll("number_", "").replaceAll("x", "-"));
	}

	@Override
	public String getTranslationKey() {
		return "" + getNumber();
	}

	public static String getNumberItemName(int number) {
		return "number_" + (number < 0 ? "x" + Math.abs(number) : number);
	}

	@Nullable
	public static NumberItem getNumberItem(int number) {
		Identifier id = Cabricality.id(getNumberItemName(number));
		return Registry.ITEM.containsId(id) ? (@Nullable NumberItem) Registry.ITEM.get(id) : null;
	}

	@Nullable
	public NumberFluid getFluid() {
		Identifier id = Registry.ITEM.getId(this);
		return Registry.FLUID.containsId(id) ? (NumberFluid) Registry.FLUID.get(id) : null;
	}

}
