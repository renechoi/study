package org.refactoring.stateandstrategy.after;

public class SuperLevel extends PlayerLevel {

	private static SuperLevel instance;

	public static SuperLevel getInstance() {
		return instance == null ? new SuperLevel() : instance;
	}

	private SuperLevel() {
	}

	@Override
	public void run() {
		System.out.println("엄청 빨리 달립니다.");

	}

	@Override
	public void jump() {
		System.out.println("엄청 높게 jump합니다.");
	}

	@Override
	public void turn() {
		System.out.println("한 바퀴 돕니다.");
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** 고급자 레벨 입니다. *****");
	}

}
