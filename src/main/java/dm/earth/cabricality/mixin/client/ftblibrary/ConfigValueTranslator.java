package dm.earth.cabricality.mixin.client.ftblibrary;

import dev.ftb.mods.ftblibrary.config.ConfigValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ConfigValue.class)
public abstract class ConfigValueTranslator {
	@Inject(
			method = "info(Ljava/lang/String;)Lnet/minecraft/text/Text;",
			at = @At("RETURN"),
			cancellable = true
	)
	private static void redirect(String key, CallbackInfoReturnable<Text> cir) {
		translate(key, "", cir);
	}

	@Inject(
			method = "info(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraft/text/Text;",
			at = @At("RETURN"),
			cancellable = true
	)
	private static void translate(String key, Object value, CallbackInfoReturnable<Text> cir) {
		if (value instanceof String) value = Text.literal((String) value);
		MutableText translated = Text.literal(key);
		switch (key) {
			case "Default" -> translated = Text.translatable("gui.default");
			case "Min" -> translated = Text.translatable("gui.min");
			case "Max" -> translated = Text.translatable("gui.max");
			case "Regex" -> translated = Text.translatable("gui.regex");
			case "List" -> translated = Text.translatable("gui.list");
		}
		cir.setReturnValue((translated.append(Text.translatable("gui.colon"))
				.formatted(Formatting.AQUA))
				.append((Text) value));
	}
}
