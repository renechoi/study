package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDistinct {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(3, -5, 4, -5, 2,3 );
		Stream<Integer> distinct = numbers.stream().distinct();

		System.out.println(distinct.collect(Collectors.toList()));
	}
}
