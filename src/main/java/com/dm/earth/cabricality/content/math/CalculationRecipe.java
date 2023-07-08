package com.dm.earth.cabricality.content.math;

import java.util.ArrayList;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.content.entries.CabfRecipeSerializers;
import com.dm.earth.cabricality.content.math.core.Calculable;
import com.dm.earth.cabricality.content.math.item.NumberItem;
import com.simibubi.create.content.kinetics.crafter.MechanicalCraftingInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CalculationRecipe extends SpecialCraftingRecipe {

	public CalculationRecipe(Identifier id) {
		super(id);
	}

	@Override
	public ItemStack craft(CraftingInventory inv) {
		ItemStack nan = new ItemStack(CabfItems.NAN);
		try {
			ArrayList<ItemStack> stacks = new ArrayList<>();
			for (int i = 0; i < inv.getWidth(); i++)
				stacks.add(inv.getStack(i));
			double num = Calculable
					.calculate(stacks.stream().map(stack -> (Calculable) stack.getItem()).toList());
			if (num % 1 == 0) {
				inv.clear();
				Item item = NumberItem.getNumberItem((int) num);
				return item != null ? new ItemStack(NumberItem.getNumberItem((int) num)) : nan;
			}
			return nan;
		} catch (Exception e) {
			return nan;
		}
	}

	@Override
	public boolean fits(int width, int height) {
		return height == 1;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return CabfRecipeSerializers.CALCULATION;
	}

	@Override
	public boolean matches(CraftingInventory inventory, World world) {
		if (!(inventory instanceof MechanicalCraftingInventory inv))
			return false;
		ArrayList<ItemStack> stacks = new ArrayList<>();
		for (int i = 0; i < inv.getWidth(); i++)
			stacks.add(inv.getStack(i));
		return stacks.stream().allMatch(stack -> stack.getItem() instanceof Calculable);
	}

}
