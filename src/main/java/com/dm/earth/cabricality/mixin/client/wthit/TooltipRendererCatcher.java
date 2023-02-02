package com.dm.earth.cabricality.mixin.client.wthit;

import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.dm.earth.cabricality.Cabricality;

import mcp.mobius.waila.gui.hud.TooltipRenderer;

@ClientOnly
@Mixin(TooltipRenderer.class)
public class TooltipRendererCatcher {
	@ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lmcp/mobius/waila/util/DisplayUtil;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIII)V"))
	private static void renderTransparentBackground(Args args) {
		if (Cabricality.CONFIG.transparentWthitTooltip) {
			args.set(6, 0x0);
			args.set(7, 0x0);
		}
	}

	@ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lmcp/mobius/waila/util/DisplayUtil;renderRectBorder(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIII)V"))
	private static void renderTransparentBackgroundBorder(Args args) {
		if (Cabricality.CONFIG.transparentWthitTooltip) {
			args.set(6, 0x0);
			args.set(7, 0x0);
		}
	}
}
