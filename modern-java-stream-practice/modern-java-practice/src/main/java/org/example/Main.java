package org.example;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {
	public static void main(String[] args) {

		Function<Integer, Integer> adder = new Adder();
		Integer apply = adder.apply(5);
		System.out.println("apply = " + apply);


		Function<Integer, Integer> adder2 = x -> x + 10;

		BiFunction<Integer, Integer, Integer> add = (x, y) -> { return x + y;};

	}
}