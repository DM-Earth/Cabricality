package com.dm.earth.cabricality.mixin.client;

import com.simibubi.create.AllBlocks;
import net.krlite.equator.geometry.Node;
import net.krlite.equator.math.EasingFunctions;
import net.krlite.equator.render.Equator;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Quaternion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashOverlay.class)
public class SplashOverlayMixin {
	@Inject(method = "render", at = @At("TAIL"))
	private void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		Quaternion quaternion = new Quaternion((float) EasingFunctions.sin(), 0.1F, (float) EasingFunctions.cos(), 4F);

		new Equator.BlockModel(AllBlocks.LARGE_COGWHEEL.getDefaultState())
				.render(Node.topScreen().toVec3d(), quaternion);
	}
}
