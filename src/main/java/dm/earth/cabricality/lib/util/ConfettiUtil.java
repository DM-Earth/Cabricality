package dm.earth.cabricality.lib.util;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.util.log.CabfLogger;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class ConfettiUtil {
	private static long initTime = -1;

	public static void startLoading() {
		CabfLogger.info("📦 Initializing " + Cabricality.NAME + "...");
		initTime = System.currentTimeMillis();
	}

	public static void finishLoading() {
		if (initTime != -1) {
			double millis = System.currentTimeMillis() - initTime;

			CabfLogger.info("⚙️ " + Cabricality.NAME + " has initialized in "
					+ (millis >= 1000 ? (millis / 1000.0 + "s") : (millis + "ms")) + "!");

			initTime = -1;
		}

		if (
				MinecraftClient.getInstance().world != null
						&& MinecraftClient.getInstance().world.isClient()
		) {
			SoundUtil.playSounds(Cabricality.Sounds.FINISH_LOADING);
			GLFW.glfwRequestWindowAttention(MinecraftClient.getInstance().getWindow().getHandle());
		}
	}
}
