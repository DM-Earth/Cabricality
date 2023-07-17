package com.dm.earth.cabricality.mixin.malum;

import ca.rttv.malum.rite.EldritchInfernalRite;
import net.minecraft.recipe.SmeltingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EldritchInfernalRite.class)
public class EldritchInfernalRiteFixer {
	@Inject(method = "lambda$onTick$0", at = @At("HEAD"), cancellable = true)
	private static void fixEldritchInfernalRite(int x$0, CallbackInfoReturnable<SmeltingRecipe[]> cir) {
		cir.setReturnValue(new SmeltingRecipe[x$0 + 1]);
	}
}
