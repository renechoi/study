package org.example.stream.advanced;

import java.util.stream.Stream;

public class Allmatchanymatch {
	public static void main(String[] args) {
		boolean allMatch = Stream.of(3, -4, 1, 0, -5, 3, 10).allMatch(x -> x > 0);

		boolean anyMatch = Stream.of(1, 3, -4, 2, 3, 6, 1).anyMatch(x -> x > 0);
	}
}
