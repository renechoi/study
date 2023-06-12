package org.example.test;

import java.util.function.Supplier;

public class SupplierTest {
	public static void main(String[] args) {
	Supplier<String> myStringSupplier = () -> {return "hello world!";};
		myStringSupplier.get();


		Supplier<Double> myRandomDoubleSupplier = () -> Math.random();
		System.out.println("myRandomDoubleSupplier.get() = " + myRandomDoubleSupplier.get());


		printRandomDoubles(myRandomDoubleSupplier, 5);
	}

	public static void printRandomDoubles(Supplier<Double> randomSupplier, int count){
		for (int i=0; i<count; i++){
			System.out.println("randomSupplier = " + randomSupplier.get());
		}
	}
}
