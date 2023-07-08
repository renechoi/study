package org.refactoring.magicnumber.before;

public class Robot {
	private String name;

	public Robot(String name) {
		this.name = name;
	}

	public void doSomething(int command){
		if (command==0){
			System.out.println("walk");
		} else if (command == 1) {
			System.out.println("stop");
		} else if (command ==2) {
			System.out.println("jump");
		}
	}
}
