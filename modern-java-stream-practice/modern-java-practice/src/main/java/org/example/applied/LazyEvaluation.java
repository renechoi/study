package org.example.applied;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEvaluation {
	public static void main(String[] args) {

		if (true || returnFalse()){ // 이때 뒤의 계산은 하지 않는다.
			System.out.println(true);
		}


		if (or(returnTrue(), returnFalse())){
			System.out.println("true");
		}

		if(lazyOr( ()-> returnTrue(), ()-> returnFalse())){
			System.out.println("true");
		}

		Stream<Integer> integerStream = Stream.of(3, -2, 5, 67, 1, 34, -10)
			.filter(x -> x > 0)
			.peek(x -> System.out.println("peeking: " + x))
			.filter(x -> x % 2 == 0);

		System.out.println("before collect ");

		List<Integer> integers = integerStream.collect(Collectors.toList());

		System.out.println("after collect ");
	}


	public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y){
		return x.get() || y.get();
	}

	public static boolean or(boolean x, boolean y){
		return x || y;
	}


	public static boolean returnTrue(){
		System.out.println("returning true");
		return true;
	}

	public static boolean returnFalse(){
		System.out.println("returning false");
		return false;
	}
}
