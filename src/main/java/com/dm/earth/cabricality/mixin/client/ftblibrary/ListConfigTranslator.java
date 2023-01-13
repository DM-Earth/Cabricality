package com.dm.earth.cabricality.mixin.client.ftblibrary;

import dev.ftb.mods.ftblibrary.config.ListConfig;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@ClientOnly
@Mixin(ListConfig.class)
public class ListConfigTranslator {
	@Mutable
	@Shadow(remap = false)
	@Final
	public static final LiteralText EMPTY_LIST = new LiteralText("[   ]");
}
