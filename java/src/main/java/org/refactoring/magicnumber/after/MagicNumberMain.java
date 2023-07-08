package org.refactoring.magicnumber.after;

public class MagicNumberMain {
	public static void main(String[] args) {
		Robot robot = new Robot("K");
		robot.doSomething(Robot.COMMAND_JUMP);
		robot.doSomething(Robot.Command.JUMP);
	}
}
