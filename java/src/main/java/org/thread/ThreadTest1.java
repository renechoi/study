package org.thread;

class MyThread1 extends Thread {
	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.println(getName());
	}
}

public class ThreadTest1 {
	public static void main(String[] args) {
		Thread t1 = new MyThread1();
		t1.start();
		Thread t2 = new MyThread1();
		t2.start();
		System.out.println("main");
	}
}