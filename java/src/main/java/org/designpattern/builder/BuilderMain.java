package org.designpattern.builder;

import static org.designpattern.builder.NyPizza.Size.*;
import static org.designpattern.builder.Pizza.Topping.*;

public class BuilderMain {
	public static void test(){

		Pizza nyPizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE)
			.addTopping(ONION).build();


		Pizza calzone = new Calzone.Builder().addTopping(HAM).addTopping(PEPPER)
			.sauceInside().build();

		System.out.println(nyPizza);
		System.out.println(calzone);

	}

	public static void main(String[] args) {
		test();
	}

}
