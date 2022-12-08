package com.dm.earth.cabricality.content.core.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Vanishable;

public class SawItem extends ToolItem implements Vanishable {

    public SawItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean hasRecipeRemainder() {
        return true;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack ret = stack.copy();
        ret.setDamage(ret.getDamage() + 1);
        return ret.getDamage() >= stack.getMaxDamage() ? ItemStack.EMPTY : ret;
    }

}
