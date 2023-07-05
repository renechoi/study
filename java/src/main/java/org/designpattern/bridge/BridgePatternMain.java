package org.designpattern.bridge;

import org.junit.jupiter.api.Test;

public class BridgePatternMain {

	@Test
	public static void test(){

		Queue<String> arrayQueue = new Queue<>(new ArrayImpl<>());

		arrayQueue.enQueue("aaa");
		arrayQueue.enQueue("bbb");
		arrayQueue.enQueue("ccc");

		System.out.println(arrayQueue.deQueue());
		System.out.println(arrayQueue.deQueue());
		System.out.println(arrayQueue.deQueue());
		System.out.println("=========================");

		Queue<String> linkedQueue = new Queue<>(new LinkedListImpl<>());
		linkedQueue.enQueue("aaa");
		linkedQueue.enQueue("bbb");
		linkedQueue.enQueue("ccc");

		System.out.println(linkedQueue.deQueue());
		System.out.println(linkedQueue.deQueue());
		System.out.println(linkedQueue.deQueue());
		System.out.println("=========================");

		Stack<String> arrayStack = new Stack<>(new ArrayImpl<>());
		arrayStack.push("aaa");
		arrayStack.push("bbb");
		arrayStack.push("ccc");

		System.out.println(arrayStack.pop());
		System.out.println(arrayStack.pop());
		System.out.println(arrayStack.pop());
		System.out.println("=========================");

		Stack<String> linkedStack = new Stack<>(new LinkedListImpl<>());
		linkedStack.push("aaa");
		linkedStack.push("bbb");
		linkedStack.push("ccc");

		System.out.println(linkedStack.pop());
		System.out.println(linkedStack.pop());
		System.out.println(linkedStack.pop());
		System.out.println("=========================");
	}
	public static void main(String[] args) {
		test();
	}
}
