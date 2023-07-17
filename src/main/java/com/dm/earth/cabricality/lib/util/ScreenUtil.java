package com.dm.earth.cabricality.lib.util;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.GameModeSelectionScreen;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import slimeknights.mantle.client.screen.book.BookScreen;

@ClientOnly
public class ScreenUtil {
	public static final Class<?>[] UNEXTENDED_SCREENS = {
			ChatScreen.class, GameModeSelectionScreen.class, BookScreen.class
	};

	@Nullable
	private static Screen nextScreen = null;

	public static void openScreenInWorld(@Nullable Screen screen) {
		openScreenInWorld(screen, true);
	}

	public static void openScreenInWorld(@Nullable Screen screen, boolean replaceCurrent) {
		if (replaceCurrent || nextScreen == null)
			nextScreen = screen;
	}

	public static void registerEvents() {
		ClientTickEvents.START.register(client -> {
			if (client.world != null && client.currentScreen == null && nextScreen != null) {
				client.setScreen(nextScreen);
				nextScreen = null;
			}
		});
	}

	public static boolean isUnextendedScreen(@Nullable Class<?> clazz) {
		if (clazz == null) return false;
		for (Class<?> unextendedScreen : UNEXTENDED_SCREENS) {
			if (unextendedScreen.isAssignableFrom(clazz)) return true;
		}
		return false;
	}
}
