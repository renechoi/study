package org.example.ex06;

import lombok.Getter;

/**
 * @author : Rene
 * @since : 2023/10/22
 */
public class Exam06 {
	public Exam06(){
		var dog = new Dog("퍼피");
		dog.eat();
		dog.bark();
	}
	public static void main(String[] args) {
		new Exam06();
	}
}

@Getter
abstract class Animal implements Bark{
	private String name;
	public Animal(String name){
		this.name =name;
	}

	public void eat(){
		System.out.println("name = " + name);
	}
}


class Dog extends Animal{

	public Dog(){
		super("");
	}

	public Dog(String name){
		super(name);
	}

	@Override
	public void bark() {
		System.out.println(this.getName() + "bark");
	}
}

interface Bark{
	void bark();
}