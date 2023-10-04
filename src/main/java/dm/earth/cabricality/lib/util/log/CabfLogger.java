package dm.earth.cabricality.lib.util.log;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.config.CabfConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CabfLogger {
	private static final Logger LOGGER = LoggerFactory.getLogger(Cabricality.ID);
	private static final String PREFIX = "[" + Cabricality.NAME + "] ";

	public static void message(@NotNull String message) {
		LOGGER.info(PREFIX + message);
	}

	public static void info(@NotNull String message) {
		if (CabfConfig.debugInfo())
			LOGGER.info(PREFIX + message);
	}

	public static void warn(@NotNull String message) {
		if (CabfConfig.debugInfo())
			LOGGER.warn(PREFIX + message);
	}

	public static void error(@NotNull String message) {
		if (CabfConfig.debugInfo())
			LOGGER.error(PREFIX + message);
	}

	public static void error(@NotNull String message, @NotNull Throwable throwable) {
		if (CabfConfig.debugInfo())
			LOGGER.error(PREFIX + message, throwable);
	}

	public static void debug(@NotNull String message) {
		if (CabfConfig.debugInfo())
			LOGGER.debug(PREFIX + message);
	}

	public static void debug(@NotNull String message, @NotNull Throwable throwable) {
		if (CabfConfig.debugInfo())
			LOGGER.debug(PREFIX + message, throwable);
	}

	public static void trace(@NotNull String message) {
		if (CabfConfig.debugInfo())
			LOGGER.trace(PREFIX + message);
	}

	public static void trace(@NotNull String message, @NotNull Throwable throwable) {
		if (CabfConfig.debugInfo())
			LOGGER.trace(PREFIX + message, throwable);
	}

	public static void debugAndError(@NotNull String message) {
		debug(message);
		error(message);
	}

	public static void debugAndError(@NotNull String message, @NotNull Throwable throwable) {
		debug(message, throwable);
		error(message);
	}
}
