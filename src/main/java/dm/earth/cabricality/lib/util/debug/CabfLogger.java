package dm.earth.cabricality.lib.util.debug;

import dm.earth.cabricality.Cabricality;
import org.jetbrains.annotations.NotNull;

public class CabfLogger {
	public static void logInfo(@NotNull String message) {
		if (Cabricality.CONFIG.debugInfo())
			Cabricality.LOGGER.info("[" + Cabricality.NAME + "] " + message);
	}

	public static void logWarn(@NotNull String message) {
		if (Cabricality.CONFIG.debugInfo())
			Cabricality.LOGGER.warn("[" + Cabricality.NAME + "] " + message);
	}

	public static void logError(@NotNull String message) {
		if (Cabricality.CONFIG.debugInfo())
			Cabricality.LOGGER.error("[" + Cabricality.NAME + "] " + message);
	}

	public static void logError(@NotNull String message, @NotNull Throwable throwable) {
		if (Cabricality.CONFIG.debugInfo())
			Cabricality.LOGGER.error("[" + Cabricality.NAME + "] " + message, throwable);
	}

	public static void logDebug(@NotNull String message) {
		if (Cabricality.CONFIG.debugInfo())
			Cabricality.LOGGER.debug("[" + Cabricality.NAME + "] " + message);
	}

	public static void logDebug(@NotNull String message, @NotNull Throwable throwable) {
		if (Cabricality.CONFIG.debugInfo())
			Cabricality.LOGGER.debug("[" + Cabricality.NAME + "] " + message, throwable);
	}

	public static void logTrace(@NotNull String message) {
		if (Cabricality.CONFIG.debugInfo())
			Cabricality.LOGGER.trace("[" + Cabricality.NAME + "] " + message);
	}

	public static void logTrace(@NotNull String message, @NotNull Throwable throwable) {
		if (Cabricality.CONFIG.debugInfo())
			Cabricality.LOGGER.trace("[" + Cabricality.NAME + "] " + message, throwable);
	}

	public static void logDebugAndError(@NotNull String message) {
		logDebug(message);
		logError(message);
	}

	public static void logDebugAndError(@NotNull String message, @NotNull Throwable throwable) {
		logDebug(message, throwable);
		logError(message);
	}
}
