package org.example.test;

import java.util.function.BiConsumer;

public class BiConsumerExample {
    public static void main(String[] args) {
        BiConsumer<String, Integer> printValues = (name, age) -> {
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
        };

        printValues.accept("kim", 25);

        BiConsumer<Integer, Integer> performOperation = (a, b) -> {
            int sum = a + b;
            int difference = a - b;
            int product = a * b;

            System.out.println("Sum: " + sum);
            System.out.println("Difference: " + difference);
            System.out.println("Product: " + product);
        };

        performOperation.accept(10, 5);
    }
}
