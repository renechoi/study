package org.designpattern.state;

public class AdvancedLevel extends PlayerLevel{
	@Override
	public void run() {
		System.out.println("advanced level 달리기.");
		
	}

	@Override
	public void jump() {
		System.out.println("advanced level jump.");
	}

	@Override
	public void turn() {
		System.out.println("advanced level Turn.");
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** advanced level. *****");
	}
}