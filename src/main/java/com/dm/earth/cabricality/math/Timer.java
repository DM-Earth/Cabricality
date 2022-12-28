package com.dm.earth.cabricality.math;

public record Timer(long origin, long lasting) {
	public Timer(long lasting) {
		this(System.currentTimeMillis(), lasting);
	}

	public long queue() {
		return Math.min(queueElapsed(), lasting);
	}

	public long queueElapsed() {
		return System.currentTimeMillis() - origin;
	}

	public double queueAsPercentage() {
		return (double) queue() / (double) lasting;
	}

	public Timer reset() {
		return new Timer(lasting);
	}

	public boolean expired() {
		return queueElapsed() > lasting;
	}
}
