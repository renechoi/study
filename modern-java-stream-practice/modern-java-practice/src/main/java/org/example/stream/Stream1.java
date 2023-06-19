package org.example.stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream1 {
	public static void main(String[] args) {
		Stream<String> nameStream = Stream.of("Alice", "Bob", "Charlie");
		List<String> collect = nameStream.collect(Collectors.toList());

		String[] cityArray = new String[]{"Seoul", "tokyo", "busan"};
		Stream<String> cityStream = Arrays.stream(cityArray);

		Set<Integer> numberSet = new HashSet<>(Arrays.asList(3, 5, 7));
		Stream<Integer> stream = numberSet.stream();
		List<Integer> numberList = stream.collect(Collectors.toList());
	}
}
