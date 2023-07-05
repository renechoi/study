package org.designpattern.templatemethod;

public class BeginnerLevel extends PlayerLevel{

	@Override
	public void run() {
		System.out.println("천천히 달립니다");
		
	}

	@Override
	public void jump() {
		System.out.println("Jump 하지 못함.");
	}

	@Override
	public void turn() {
		System.out.println("Turn 하지 못함.");
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** 초보자 레벨입니다. *****");
	}

}