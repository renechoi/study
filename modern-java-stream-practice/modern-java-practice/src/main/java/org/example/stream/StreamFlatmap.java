package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFlatmap {
	public static void main(String[] args) {
		String[][] cities = new String[][]{
			{"Seoul", "Busan"},
			{"San Francisco", "New York"},
			{"Madrid", "Barcelona"}
		};
		Stream<String[]> stream = Arrays.stream(cities);

		Stream<Stream<String>> streamStream = stream.map(x -> Arrays.stream(x));

		List<Stream<String>> collect = streamStream.collect(Collectors.toList());

		Stream<String[]> cityStream = Arrays.stream(cities);
		Stream<String> stringStream = cityStream.flatMap(x -> Arrays.stream(x));
		List<String> cityList = stringStream.collect(Collectors.toList());

	}
}
