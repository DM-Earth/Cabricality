package com.dm.earth.cabricality.mixin.client.ftblibrary;

import dev.ftb.mods.ftblibrary.config.BooleanConfig;
import net.krlite.equator.util.IdentifierBuilder;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// Toxic FTB Library localizations
@Mixin(BooleanConfig.class)
public class BooleanConfigTranslator {
	@Redirect(method = "getStringForGUI(Ljava/lang/Boolean;)Lnet/minecraft/text/Text;", at = @At(value = "FIELD", target = "Ldev/ftb/mods/ftblibrary/config/BooleanConfig;TRUE_TEXT:Lnet/minecraft/text/LiteralText;"))
	private LiteralText trueText() {
		return new LiteralText(IdentifierBuilder.localization("gui", "true").getString());
	}

	@Redirect(method = "getStringForGUI(Ljava/lang/Boolean;)Lnet/minecraft/text/Text;", at = @At(value = "FIELD", target = "Ldev/ftb/mods/ftblibrary/config/BooleanConfig;FALSE_TEXT:Lnet/minecraft/text/LiteralText;"))
	private LiteralText falseText() {
		return new LiteralText(IdentifierBuilder.localization("gui", "false").getString());
	}
}
