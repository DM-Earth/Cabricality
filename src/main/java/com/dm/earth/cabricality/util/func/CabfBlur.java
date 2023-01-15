package com.dm.earth.cabricality.util.func;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.PushUtil;
import com.google.common.collect.ImmutableList;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import ladysnake.satin.api.managed.uniform.Uniform1f;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class CabfBlur {
	public static final CabfBlur INSTANCE = new CabfBlur();

	private static final List<Class<? extends Screen>> EXCLUDED_SCREENS = new ArrayList<>();
	private final ManagedShaderEffect BLUR = ShaderEffectManager.getInstance().manage(
			Cabricality.id("shaders", "post", "fade_in_blur.json"),
			managedShaderEffect -> managedShaderEffect.setUniformValue("Radius", Cabricality.CONFIG.backgroundBlurRadius));
	private final Uniform1f PROGRESS = BLUR.findUniform1f("Progress");
	private long startTime;

	public void init() {
		EXCLUDED_SCREENS.addAll(ImmutableList.of(ChatScreen.class));

		ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
			if (startTime > 0) {
				PROGRESS.set(getProgress());
				if (Cabricality.CONFIG.backgroundBlur)
					BLUR.render(tickDelta);
			}
		});
	}

	public float getProgress() {
		return Math.min(1, (System.currentTimeMillis() - startTime) / 320.0F);
	}

	@SuppressWarnings("all")
	public void onScreenChange(Screen screen) {
		if (MinecraftClient.getInstance().world != null) {
			if (screen == null || EXCLUDED_SCREENS.contains(screen.getClass())) {
				startTime = -1;
				PushUtil.BLUR_FADE.push();
			} else {
				BLUR.setUniformValue("Radius", Cabricality.CONFIG.backgroundBlurRadius);
				PushUtil.BLUR_FADE.pull(() -> startTime = System.currentTimeMillis());
			}
		}
	}
}
