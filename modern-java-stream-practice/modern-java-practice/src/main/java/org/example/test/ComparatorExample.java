package org.example.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 20));

        // 이름을 기준으로 오름차순으로 정렬하는 Comparator
        Comparator<Person> nameComparator = (person1, person2) -> person1.getName().compareTo(person2.getName());
        Comparator<Person> nameComparator2 = Comparator.comparing(Person::getName);

        // 나이를 기준으로 내림차순으로 정렬하는 Comparator
        Comparator<Person> ageComparator = (person1, person2) -> person2.getAge() - person1.getAge();
        Comparator<Person> ageComparator2 = Comparator.comparingInt(Person::getAge).reversed();

        // 이름을 기준으로 오름차순으로 정렬
        people.sort(nameComparator);
        System.out.println("Sorted by name (ascending):");
        for (Person person : people) {
            System.out.println(person.getName() + " - " + person.getAge());
        }

        // 나이를 기준으로 내림차순으로 정렬
        people.sort(ageComparator);
        System.out.println("Sorted by age (descending):");
        for (Person person : people) {
            System.out.println(person.getName() + " - " + person.getAge());
        }
    }
}
