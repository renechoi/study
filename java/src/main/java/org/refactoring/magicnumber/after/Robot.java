package org.refactoring.magicnumber.after;

public class Robot {

	public static final int COMMAND_WALK = 0;
	public static final int COMMAND_STOP = 1;
	public static final int COMMAND_JUMP = 2;
	public enum Command{
		WALK, STOP, JUMP
	}
	private String name;

	public Robot(String name) {
		this.name = name;
	}

	public void doSomething(int command){
		if (command==COMMAND_WALK){
			System.out.println("walk");
		} else if (command == COMMAND_STOP) {
			System.out.println("stop");
		} else if (command ==COMMAND_JUMP) {
			System.out.println("jump");
		}
	}

	public void doSomething(Command command){
		if (command==Command.WALK){
			System.out.println("walk");
		} else if (command == Command.STOP) {
			System.out.println("stop");
		} else if (command ==Command.JUMP) {
			System.out.println("jump");
		}
	}
}
