package org.example.test.methodref;

import java.util.function.Supplier;

class Person {
    private String name;
    
    public Person(String name) {
        this.name = name;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class MethodReferenceExample {
    public static void main(String[] args) {
        // 생성자 메서드 레퍼런스
        Supplier<Person> personSupplier = Person::new;
        
        // Person 객체 생성
        Person person = personSupplier.get();
        person.setName("Alice");
        
        System.out.println("Name: " + person.getName());
    }
}
