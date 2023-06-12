package org.example.test;

import java.util.Arrays;
import java.util.List;

class StringUtils {
    public static int countUpperCase(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }
        return count;
    }

    public static void printString(String str) {
        System.out.println(str);
    }
}

public class MethodReferenceExample {
    public static void main(String[] args) {
        // static 메서드 레퍼런스
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(StringUtils::printString);

        // 인스턴스 메서드 레퍼런스
        String str = "HelloWorld";
        int upperCaseCount = StringUtils.countUpperCase(str);
        System.out.println("Number of uppercase letters: " + upperCaseCount);
    }
}
