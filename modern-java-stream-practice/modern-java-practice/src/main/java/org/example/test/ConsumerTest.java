package org.example.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
	public static void main(String[] args) {
		Consumer<String> myStringConsumer = (String string) -> System.out.println("string = " + string);

		myStringConsumer.accept("hello");

		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

		Consumer<Integer> myIntegerProcessor = (Integer x) -> System.out.println("process integer: " + x);
		Consumer<Integer> myIntegerProcessor2 = (Integer x) -> System.out.println("process in different way: " + x);
	}

	public static void process(List<Integer> inputs, Consumer<Integer> processor){
		for (Integer input : inputs) {
			processor.accept(input);
		}
	}


	public static <T> void process2(List<T> inputs, Consumer<T> processor){
		for (T input : inputs) {
			processor.accept(input);
		}
	}

}
