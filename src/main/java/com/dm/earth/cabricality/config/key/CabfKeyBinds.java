package com.dm.earth.cabricality.config.key;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.config.modmenu.CabfConfigScreen;
import com.mojang.blaze3d.platform.InputUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBind;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

public class CabfKeyBinds {
	private static final String GENERAL = Cabricality.genTranslationKey("key", "category", "general");

	public static KeyBind CONFIG_SCREEN = KeyBindingHelper.registerKeyBinding(new KeyBind(
			Cabricality.genTranslationKey("key", "general", "config_screen"),
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_EQUAL, GENERAL
	));

	public static void registerKenBinds() {
		ClientTickEvents.END.register(client -> {
			if (CONFIG_SCREEN.wasPressed())
				client.setScreen(new CabfConfigScreen(client.currentScreen).build());
		});
	}
}
