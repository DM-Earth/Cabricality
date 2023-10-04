package dm.earth.cabricality.mixin.client;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.Mod;
import dm.earth.cabricality.client.CabricalityClient;
import dm.earth.cabricality.client.screen.MissingModScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.PlainTextButtonWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
		if (!Mod.Dependency.isAllLoaded()) {
			Text warning = ((MutableText) (Mod.Dependency.getAllMissing().size() == 1
					? Cabricality.genTranslatableText("screen", "title_screen", "warning_missing_mod")
					: Text.translatable(
							Cabricality.genTranslationKey("screen", "title_screen", "warning_missing_mod_plural"),
							Mod.Dependency.getAllMissing().size()
			))).formatted(Formatting.RED);

			this.addDrawableChild(
					new PlainTextButtonWidget(
							this.width / 2 - this.textRenderer.getWidth(warning) / 2, 2,
							this.textRenderer.getWidth(warning), 10, warning,
							buttonWidget -> {
								if (this.client != null)
									this.client.setScreen(new MissingModScreen(Mod.Dependency.getAllMissing(),
											this.client.currentScreen));
							}, this.textRenderer));
		}
	}

	@Inject(method = "render", at = @At("RETURN"))
	private void updateCubeMapRenderer(GuiGraphics graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		CabricalityClient.cubeMapRenderer(backgroundRenderer);
	}
}
