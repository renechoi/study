package org.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -2, 0, -5);

        Predicate<Integer> isEven = number -> number % 2 == 0;

        // numbers 리스트에서 짝수만 필터링하여 출력
        System.out.println("Even numbers:");
        numbers.stream()
               .filter(isEven)
               .forEach(System.out::println);

        // 양수를 판별하는 Predicate
        Predicate<Integer> isPositive = number -> number > 0;

        // numbers 리스트에서 양수만 필터링하여 출력
        System.out.println("Positive numbers: " + filter(numbers, isPositive));
        System.out.println("Non-Positive numbers: " + filter(numbers, isPositive.negate()));

    }

    public static <T> List<T> filter(List<T> inputs, Predicate<T> predicate){
        List<T> output = new ArrayList<>();
        for (T input : inputs) {
            if (predicate.test(input)){
                output.add(input);
            }
        }
        return output;
    }
}
