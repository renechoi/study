package org.refactoring.stateandstrategy.before;

public class MainBoard {

	public static void main(String[] args) {

		Player player = new Player();
		player.play(1);
		player.upgradeLevel();
		player.play(2);
		player.upgradeLevel();
		player.play(3);

	}
}
