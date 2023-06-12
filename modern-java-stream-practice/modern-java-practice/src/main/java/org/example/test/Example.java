package org.example.test;

import org.example.TriFunction;

public class Example {
    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b + c;

        int result = sum.apply(1, 2, 3);
        System.out.println(result);  // 출력: 6
    }


}
