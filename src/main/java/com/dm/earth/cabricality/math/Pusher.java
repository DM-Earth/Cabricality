package com.dm.earth.cabricality.math;

import java.util.concurrent.atomic.AtomicBoolean;

public record Pusher(AtomicBoolean ready) {
	public Pusher() {
		this(new AtomicBoolean(false));
	}

	public void push() {
		ready.set(true);
	}

	public boolean pull() {
		return ready.getAndSet(false);
	}
}
