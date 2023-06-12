package org.example.methodref;

public class Sedan extends Car{
	public Sedan(String name, String brand) {
		super(name, brand);
	}

	@Override
	public void drive() {
		System.out.println("Sedan.drive");
	}
}
