package org.thread.runningex1;

class MyThread2 implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.println(Thread.currentThread().getName());
	}
}

public class ThreadTest2 {
	public static void main(String[] args) {
		Thread t1 = new Thread(new MyThread2(), "thd0");
		t1.start();
		Thread t2 = new Thread(new MyThread2(), "thd1");
		t2.start();
		System.out.println("main");
	}
}
 