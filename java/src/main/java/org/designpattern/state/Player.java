package org.designpattern.state;


public class Player {

	private PlayerLevel level;
	private int levelNotWithStatePattern;

	public Player() {
		level = new BeginnerLevel();
		level.showLevelMessage();
		levelNotWithStatePattern=1;
	}

	public PlayerLevel getLevel() {
		return level;
	}

	public void upgradeLevel(PlayerLevel level) {
		this.level = level;
		level.showLevelMessage();
		levelNotWithStatePattern++;
	}

	public void play(int count) {
		level.go(count);
	}
}