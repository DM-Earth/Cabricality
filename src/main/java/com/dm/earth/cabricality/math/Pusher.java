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

	public void run(Runnable runnable) {
		if (pull()) runnable.run();
	}

	public void or(boolean or, Runnable runnable) {
		if (or || pull()) runnable.run();
	}

	public void and(boolean and, Runnable runnable) {
		if (and && pull()) runnable.run();
	}

	public Pusher paste() {
		return new Pusher(new AtomicBoolean(ready.get()));
	}
}
