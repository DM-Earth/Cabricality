package com.dm.earth.cabricality.math;

import java.util.concurrent.atomic.AtomicBoolean;

public record Pusher(AtomicBoolean ready) {
	public Pusher() {
		this(new AtomicBoolean(false));
	}

	public void push() {
		ready.set(true);
	}

	public void push(Runnable runnable) {
		push();
		runnable.run();
	}

	public boolean pull() {
		return ready.get() && ready.getAndSet(false);
	}

	public void pull(Runnable runnable) {
		pull(false, runnable);
	}

	public void pull(boolean shortCircuit, Runnable runnable) {
		if (shortCircuit || pull()) runnable.run();
	}

	public Pusher paste() {
		return new Pusher(new AtomicBoolean(ready.get()));
	}
}
