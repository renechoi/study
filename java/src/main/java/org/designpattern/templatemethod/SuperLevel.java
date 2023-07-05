package org.designpattern.templatemethod;

public class SuperLevel extends PlayerLevel{
	@Override
	public void run() {
		System.out.println("빨리 달립니다.");
		
	}

	@Override
	public void jump() {
		System.out.println("Jump 가능.");
	}

	@Override
	public void turn() {
		System.out.println("Turn 가능.");
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** 슈퍼 레벨입니다. *****");
	}

}