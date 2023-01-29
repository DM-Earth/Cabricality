package com.dm.earth.cabricality.mixin.client.ftblibrary;

import dev.ftb.mods.ftblibrary.config.BooleanConfig;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// Toxic FTB Library localizations
@ClientOnly
@Mixin(BooleanConfig.class)
public class BooleanConfigTranslator {
	@Redirect(method = "getStringForGUI(Ljava/lang/Boolean;)Lnet/minecraft/text/Text;", at = @At(value = "FIELD", target = "Ldev/ftb/mods/ftblibrary/config/BooleanConfig;TRUE_TEXT:Lnet/minecraft/text/LiteralText;"))
	private LiteralText translateTrue() {
		return new LiteralText(new TranslatableText("gui.true").getString());
	}

	@Redirect(method = "getStringForGUI(Ljava/lang/Boolean;)Lnet/minecraft/text/Text;", at = @At(value = "FIELD", target = "Ldev/ftb/mods/ftblibrary/config/BooleanConfig;FALSE_TEXT:Lnet/minecraft/text/LiteralText;"))
	private LiteralText tsanslateFalse() {
		return new LiteralText(new TranslatableText("gui.false").getString());
	}
}
