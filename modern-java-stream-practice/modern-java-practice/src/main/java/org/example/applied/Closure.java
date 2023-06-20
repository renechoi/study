package org.example.applied;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Closure {
	public static void main(String[] args) {
		Supplier<String> supplier = getStringSupplier();
		System.out.println(supplier.get());

		BiFunction<Integer, Integer, Integer> add = (x, y) -> x+ y;


		Function<Integer, Function<Integer, Integer>> curriedAdd = x -> y -> x + y;


		Function<Integer, Integer> addThree = curriedAdd.apply(3);

		int result =addThree.apply(10);

		System.out.println(result);

	}

	public static Supplier<String> getStringSupplier(){
		String hello = "Hello";
		Supplier<String>  supplier = () -> {
			String world = "world";
			return hello + world;
		};
		return supplier;
	}
}
