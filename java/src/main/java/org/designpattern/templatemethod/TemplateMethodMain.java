package org.designpattern.templatemethod;

public class TemplateMethodMain {

	public static void test(){
		Player player = new Player();
		player.play(1);
		player.play(2);
		SuperLevel sLevel = new SuperLevel();
		player.upgradeLevel(sLevel);
		player.play(3);
	}
	public static void main(String[] args) {
		test();
	}
}
