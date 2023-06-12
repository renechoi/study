package org.example.methodref;

public class Suv extends Car{
	public Suv(String name, String brand) {
		super(name, brand);
	}

	@Override
	public void drive() {
		System.out.println("Suv.drive");
	}
}
