package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.client.CabricalityClient;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.screen.MissingModScreen;
import com.dm.earth.cabricality.lib.util.mod.CabfModDeps;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.PlainTextButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

@Mixin(TitleScreen.class)
public class TitleScreenWidgets extends Screen {
	@Shadow
	@Final
	private RotatingCubeMapRenderer backgroundRenderer;

	protected TitleScreenWidgets(Text title) {
		super(title);
	}

	// Redirects the title screen to the missing mod screen if mods are missing
	@Inject(method = "init", at = @At("TAIL"))
	private void init(CallbackInfo ci) {
		if (!CabfModDeps.isAllLoaded()) {
			Text warning = (CabfModDeps.getAllMissing().size() == 1
					? Cabricality.genTranslatableText("screen", "title_screen", "warning_missing_mod")
					: new TranslatableText(
							Cabricality.genTranslationKey("screen", "title_screen", "warning_missing_mod_plural"),
							CabfModDeps.getAllMissing().size()
			)).formatted(Formatting.RED);
			this.addDrawableChild(
					new PlainTextButtonWidget(
							this.width / 2 - this.textRenderer.getWidth(warning) / 2, 2,
							this.textRenderer.getWidth(warning), 10, warning,
							buttonWidget -> {
								if (this.client != null)
									this.client.setScreen(new MissingModScreen(CabfModDeps.getAllMissing(),
											this.client.currentScreen));
							}, this.textRenderer));
		}
	}

	// Sets title to Cabricality
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V", ordinal = 1))
	private void renderCabricalityTitle(int layer, Identifier identifier) {
		RenderSystem.setShaderTexture(layer, Cabricality.Textures.CABRICALITY_TITLE.identifier());
	}

	// Sets subtitle to Minecraft
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V", ordinal = 2))
	private void renderMinecraftSubtitle(int layer, Identifier identifier) {
		RenderSystem.setShaderTexture(layer, Cabricality.Textures.MINECRAFT_SUBTITLE.identifier());
	}

	@Inject(method = "render", at = @At("RETURN"))
	private void updateCubeMapRenderer(MatrixStack matrixStack, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		CabricalityClient.cubeMapRenderer(backgroundRenderer);
	}
}
