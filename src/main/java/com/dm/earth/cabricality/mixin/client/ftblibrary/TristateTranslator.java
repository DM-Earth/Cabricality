package com.dm.earth.cabricality.mixin.client.ftblibrary;

import dev.ftb.mods.ftblibrary.config.Tristate;
import net.minecraft.text.Text;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ClientOnly
@Mixin(Tristate.class)
public class TristateTranslator {
	@Inject(method = "lambda$static$1", at = @At("HEAD"), cancellable = true, remap = false)
	private static void translateTristate(Tristate tristate, CallbackInfoReturnable<Text> cir) {
		switch (tristate.name) {
			case "true" -> cir.setReturnValue(Text.translatable("gui.true"));
			case "false" -> cir.setReturnValue(Text.translatable("gui.false"));
			case "default" -> cir.setReturnValue(Text.translatable("gui.default"));
		}
	}
}
