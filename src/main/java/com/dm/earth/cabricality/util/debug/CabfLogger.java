package com.dm.earth.cabricality.util.debug;

import static com.dm.earth.cabricality.Cabricality.CONFIG;
import static com.dm.earth.cabricality.Cabricality.LOGGER;
import static com.dm.earth.cabricality.Cabricality.NAME;
import org.jetbrains.annotations.NotNull;

public class CabfLogger {
	public static void logInfo(@NotNull String message) {
		if (CONFIG.debugInfo)
			LOGGER.info("[" + NAME + "] " + message);
	}

	public static void logWarn(@NotNull String message) {
		if (CONFIG.debugInfo)
			LOGGER.warn("[" + NAME + "] " + message);
	}

	public static void logError(@NotNull String message) {
		if (CONFIG.debugInfo)
			LOGGER.error("[" + NAME + "] " + message);
	}

	public static void logError(@NotNull String message, @NotNull Throwable throwable) {
		if (CONFIG.debugInfo)
			LOGGER.error("[" + NAME + "] " + message, throwable);
	}

	public static void logDebug(@NotNull String message) {
		if (CONFIG.debugInfo)
			LOGGER.debug("[" + NAME + "] " + message);
	}

	public static void logDebug(@NotNull String message, @NotNull Throwable throwable) {
		if (CONFIG.debugInfo)
			LOGGER.debug("[" + NAME + "] " + message, throwable);
	}

	public static void logTrace(@NotNull String message) {
		if (CONFIG.debugInfo)
			LOGGER.trace("[" + NAME + "] " + message);
	}

	public static void logTrace(@NotNull String message, @NotNull Throwable throwable) {
		if (CONFIG.debugInfo)
			LOGGER.trace("[" + NAME + "] " + message, throwable);
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
