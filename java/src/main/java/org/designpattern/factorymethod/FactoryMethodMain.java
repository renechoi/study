package org.designpattern.factorymethod;

import org.junit.jupiter.api.Test;

public class FactoryMethodMain {

	@Test
	public static void test(){
		CarFactory factory = new HyundaiFactory();
		Car newCar = factory.createCar("sonata");

		System.out.println(newCar);

		Car myCar = factory.returnMyCar("Tomas");
		Car hisCar = factory.returnMyCar("Tomas");
		System.out.println(myCar == hisCar);
	}

	public static void main(String[] args) {
		test();
	}
}
