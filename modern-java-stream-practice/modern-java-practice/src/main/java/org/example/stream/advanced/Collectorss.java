package org.example.stream.advanced;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collectorss {
	public static void main(String[] args) {
		Stream.of(3, 5, -3, -2, 1, 0).collect(Collectors.toList());

		Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.mapping(x-> Math.abs(x), Collectors.toList()));
	}
}
