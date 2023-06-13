package org.thread;



class MyThread5 implements Runnable {
	Counter c;

	public MyThread5(Counter c) {
		this.c = c;
	}

	public void run() {
		for (int i = 0; i < 100000; i++) {

			c.increment();
		}
	}
}

class MyThread4 implements Runnable {
	Counter c;

	public MyThread4(Counter c) {
		this.c = c;
	}

	public void run() {
		for (int i = 0; i < 100000; i++) {
			c.decrement();
		}
	}
}

public class ThreadInterruptionTest {
	public static void main(String[] args) throws
		InterruptedException {
		Counter c = new Counter();
		Thread t1 = new Thread(new MyThread4(c));
		Thread t2 = new Thread(new MyThread5(c));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("결과 값: " + c.value());
	}
}
