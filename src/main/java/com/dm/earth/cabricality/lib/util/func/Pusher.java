package com.dm.earth.cabricality.lib.util.func;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Legacy class for pushing a flag.
 * Implemented from Equator Utils v1.1.15.
 */
public class Pusher {
	/**
	 * The disposable flag.
	 */
	private final @NotNull AtomicBoolean flag = new AtomicBoolean();

	/**
	 * Creates a new Pusher with a flag.
	 * @param flag	The initial value of the disposable flag.
	 */
	public Pusher(boolean flag) {
		this.flag.set(flag);
	}

	/**
	 * Creates a new Pusher with a flag set to
	 * <code>false</code>.
	 */
	public Pusher() {
		this(false);
	}

	/**
	 * Pushes the flag to <code>true</code>.
	 */
	public void push() {
		flag.set(true);
	}

	/**
	 * Pushes the flag to <code>true</code>, then runs the
	 * action.
	 * @param action	The action to be run after the
	 *                  value is pushed.
	 */
	public void push(@NotNull Runnable action) {
		push();
		action.run();
	}

	/**
	 * Pulls and disposes the flag.
	 * @return	Whether the flag is ready to be pulled.
	 * 			<code>true</code> if any thread has
	 * 			pushed the flag before last pull,
	 * 			otherwise <code>false</code>.
	 */
	public boolean pull() {
		return flag.getAndSet(false);
	}

	/**
	 * Pulls and disposes the flag, then runs the action
	 * if success.
	 * @param action	The action to be run after the
	 *                  value is disposed.
	 * @return 	Whether the flag is ready to be pulled.
	 * 			<code>true</code> if any thread has
	 * 			pushed the flag before last pull,
	 * 			otherwise <code>false</code>.
	 */
	public boolean pull(@NotNull Runnable action) {
		boolean pulled = pull();
		if (pulled) action.run();
		return pulled;
	}

	/**
	 * Pulls and disposes the flag only if the
	 * short-circuit condition is not met. Then runs the
	 * action if success.
	 * @param or		The <strong>OR</strong>
	 *             	   	short-circuit condition.
	 * @param action	The action to be run after the
	 *                  flag is disposed.
	 */
	public void or(boolean or, @NotNull Runnable action) {
		if (or || pull()) action.run();
	}

	/**
	 * Pulls and disposes the flag only if the
	 * short-circuit condition is not met. Then runs the
	 * action.
	 * @param and		The <strong>AND</strong>
	 *             	   	short-circuit condition.
	 * @param action	The action to be run after the
	 *                  flag is disposed.
	 */
	public void and(boolean and, @NotNull Runnable action) {
		if (and && pull()) action.run();
	}

	/**
	 * Pulls and disposes the flag, then runs the success
	 * action if success, otherwise runs the failure
	 * action.
	 * @param success	The action to be run if
	 *                  {@link #pull()} returns
	 *                  <code>true</code>.
	 * @param failure	The action to be run if
	 *                  {@link #pull()} returns
	 *                  <code>false</code>.
	 */
	public void safePull(@NotNull Runnable success, @NotNull Runnable failure) {
		if (!pull(success)) failure.run();
	}

	/**
	 * Accepts another {@link Pusher}'s flag and pulls
	 * and disposes its flag.
	 * @param another	The {@link Pusher} to be accepted.
	 * @return			The old flag value.
	 */
	public boolean accept(@NotNull Pusher another) {
		return flag.getAndSet(another.pull());
	}
}
