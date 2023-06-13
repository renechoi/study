package org.thread;

public class Counter2 {
	private int c = 0;

	public void increment() {
		synchronized (this) {
			c++;
		}
	}

	public void decrement() {
		synchronized (this) {
			c--;
		}
	}

	public int value() {
		return c;
	}
}