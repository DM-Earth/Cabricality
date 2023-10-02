package dm.earth.cabricality.content.math;

import java.util.ArrayList;
import java.util.Objects;

import dm.earth.cabricality.content.entries.CabfItems;
import dm.earth.cabricality.content.entries.CabfRecipeSerializers;
import dm.earth.cabricality.content.math.core.Calculable;
import dm.earth.cabricality.content.math.item.NumberItem;
import com.simibubi.create.content.kinetics.crafter.MechanicalCraftingInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingCategory;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CalculationRecipe extends SpecialCraftingRecipe {
	public CalculationRecipe(Identifier id, CraftingCategory category) {
		super(id, category);
	}

	@Override
	public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
		ItemStack nan = new ItemStack(CabfItems.NAN);
		try {
			ArrayList<ItemStack> stacks = new ArrayList<>();
			for (int i = 0; i < inventory.getWidth(); i++)
				stacks.add(inventory.getStack(i));
			double num = Calculable
					.calculate(stacks.stream().map(stack -> (Calculable) stack.getItem()).toList());
			if (num % 1 == 0) {
				inventory.clear();
				Item item = NumberItem.getNumberItem((int) num);
				return item != null ? new ItemStack(Objects.requireNonNull(NumberItem.getNumberItem((int) num))) : nan;
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
	public boolean matches(RecipeInputInventory inventory, World world) {
		if (!(inventory instanceof MechanicalCraftingInventory inv))
			return false;
		ArrayList<ItemStack> stacks = new ArrayList<>();
		for (int i = 0; i < inv.getWidth(); i++)
			stacks.add(inv.getStack(i));
		return stacks.stream().allMatch(stack -> stack.getItem() instanceof Calculable);
	}
}
