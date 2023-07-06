package org.designpattern.state;

public class SuperLevel extends PlayerLevel{
	@Override
	public void run() {
		System.out.println("super level run.");
		
	}

	@Override
	public void jump() {
		System.out.println("super level jump.");
	}

	@Override
	public void turn() {
		System.out.println("super level turn.");
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** super level. *****");
	}

}