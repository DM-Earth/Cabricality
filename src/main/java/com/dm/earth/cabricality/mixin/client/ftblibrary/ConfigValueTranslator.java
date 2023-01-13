package com.dm.earth.cabricality.mixin.client.ftblibrary;

import dev.ftb.mods.ftblibrary.config.ConfigValue;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ClientOnly
@Mixin(ConfigValue.class)
public abstract class ConfigValueTranslator {
	@Inject(method = "info(Ljava/lang/String;)Lnet/minecraft/text/Text;", at = @At("RETURN"), cancellable = true)
	private static void redirect(String key, CallbackInfoReturnable<Text> cir) {
		translate(key, "", cir);
	}

	@Inject(method = "info(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraft/text/Text;", at = @At("RETURN"), cancellable = true)
	private static void translate(String key, Object value, CallbackInfoReturnable<Text> cir) {
		if (value instanceof String) value = new LiteralText((String) value);
		MutableText translated = new LiteralText(key);
		switch (key) {
			case "Default" -> translated = new TranslatableText("gui.default");
			case "Min" -> translated = new TranslatableText("gui.min");
			case "Max" -> translated = new TranslatableText("gui.max");
			case "Regex" -> translated = new TranslatableText("gui.regex");
			case "List" -> translated = new TranslatableText("gui.list");
		}
		cir.setReturnValue((translated.append(new TranslatableText("gui.colon")).formatted(Formatting.AQUA)).shallowCopy().append((Text) value));
	}
}
