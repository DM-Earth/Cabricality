package com.dm.earth.cabricality.util.debug;

import org.apache.logging.log4j.Level;

public class InternalLogger implements me.shedaniel.rei.impl.common.InternalLogger {
	@Override
	public void throwException(Throwable throwable) {
		throw new RuntimeException(throwable);
	}

	@Override
	public void log(Level level, String message) {

	}

	@Override
	public void log(Level level, String message, Throwable throwable) {

	}
}
