package org.designpattern.factorymethod;

public abstract class CarFactory {

	
	public abstract Car createCar(String name);

	public abstract Car returnMyCar(String name);

	public void numbering(){
		System.out.println("numbering");
	}

	public void washing(){
		System.out.println("washing");
	}
}