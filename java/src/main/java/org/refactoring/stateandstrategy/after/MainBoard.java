package org.refactoring.stateandstrategy.after;


public class MainBoard {

	public static void main(String[] args) {

		Player player = new Player();
		player.play(1);
		AdvancedLevel aLevel = AdvancedLevel.getInstance();
		player.upgradeLevel(aLevel);
		player.play(2);
		SuperLevel sLevel = SuperLevel.getInstance();
		player.upgradeLevel(sLevel);
		player.play(3);
		
	}
}
