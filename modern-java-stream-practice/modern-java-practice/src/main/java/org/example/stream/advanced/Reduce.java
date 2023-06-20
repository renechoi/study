package org.example.stream.advanced;

import java.util.Arrays;
import java.util.List;

public class Reduce {
	public static void main(String[] args) {
		List<Integer> integers = Arrays.asList(1, 4, -2, -5, 3);
		int sum = integers.stream().reduce((x, y) -> x + y).get();
		System.out.println(sum);

		int min = integers.stream().reduce((x, y) ->
			(x > y ? x : y)
		).get();

		List<String> numbers = Arrays.asList("3", "2", "5", "-4", "1");
		int sum2 = numbers.stream()
			.reduce(0, (number, str) -> number + Integer.parseInt(str), (num1, num2) -> num2 + num2);

	}
}
