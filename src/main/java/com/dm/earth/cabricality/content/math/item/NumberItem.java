package com.dm.earth.cabricality.content.math.item;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.items.GlintedItem;
import com.dm.earth.cabricality.content.fluids.NumberFluid;
import com.dm.earth.cabricality.content.math.core.CalculationNumber;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class NumberItem extends GlintedItem implements CalculationNumber {

	public NumberItem(Settings settings) {
		super(settings);
	}

	@Override
	public int getNumber() {
		return Integer.parseInt(Registries.ITEM.getId(this).getPath()
				.replaceAll("number_", "").replaceAll("x", "-"));
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
		return Registries.ITEM.containsId(id) ? (@Nullable NumberItem) Registries.ITEM.get(id) : null;
	}

	@Nullable
	public NumberFluid getFluid() {
		Identifier id = Registries.ITEM.getId(this);
		return Registries.FLUID.containsId(id) ? (NumberFluid) Registries.FLUID.get(id) : null;
	}

}
