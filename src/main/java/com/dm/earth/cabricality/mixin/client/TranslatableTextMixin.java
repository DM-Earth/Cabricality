package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.util.ModChecker;
import net.minecraft.item.BlockItem;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TranslatableText.class)
public class TranslatableTextMixin {

	@ModifyVariable(method = "<init>(Ljava/lang/String;)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private static String injected(String key) {
		// Mod Check
		if (!ModChecker.isFullLoaded()
				&& !key.startsWith(CabricalityClient.genTranslationKey("util", ""))
				&& !key.equals("menu.quit")
		) {
			return ModChecker.missingModCount() <= 1
					? CabricalityClient.genTranslationKey("util", "missing_mod")
					: CabricalityClient.genTranslationKey("util", "missing_mods");
		}

		if (key.startsWith(CabricalityClient.genTranslationKey("item", "trade_card_")))
			return CabricalityClient.genTranslationKey("item", "trade_card");
		if (key.startsWith(CabricalityClient.genTranslationKey("item", "profession_card_")))
			return CabricalityClient.genTranslationKey("item", "profession_card");

		// BlockItem transform
		if (key.startsWith("item.")) {
			String keyTemp = key.replaceFirst("item.", "").replaceFirst(".", ":");
			Identifier id = new Identifier(keyTemp);
			if (Registry.ITEM.containsId(id)) if (Registry.ITEM.get(id) instanceof BlockItem blockItem) {
				return "block." + key.replaceFirst("item.", "");
			}
		}

		return key;
	}
}
