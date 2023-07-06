package org.designpattern.state;

public class BeginnerLevel extends PlayerLevel{

	@Override
	public void run() {
		System.out.println("천천히 달립니다.");
	}

	@Override
	public void jump() {
		System.out.println("초보자 Jump.");
	}

	@Override
	public void turn() {
		System.out.println("초보자 Turn.");
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** 초보자 레벨. *****");
	}

}