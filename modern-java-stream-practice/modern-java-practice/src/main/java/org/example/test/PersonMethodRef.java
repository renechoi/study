package org.example.test;

import java.util.function.BiFunction;

public class PersonMethodRef {
	public static void main(String[] args) {
		BiFunction<String, Integer, Person> personGenerator = Person::new;
		Person kim = personGenerator.apply("kim", 3);
		System.out.println("kim = " + kim);
	}
}
