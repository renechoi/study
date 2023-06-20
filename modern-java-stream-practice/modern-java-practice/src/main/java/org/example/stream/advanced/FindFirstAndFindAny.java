package org.example.stream.advanced;

import java.util.stream.Stream;

public class FindFirstAndFindAny {
	public static void main(String[] args) {
		Stream.of(3,2,-5,6,-7).filter(x-> x< 0).findAny();

		Stream.of(-1,-4,6,2,1).filter(x->x>0).findFirst();
	}
}
