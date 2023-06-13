package org.awt;

import java.awt.*;

public class PulldownMenu {
	public static void main(String args[]) {
		Frame f = new Frame("Pulldown Menu");

		MenuBar mb = new MenuBar();
		Menu m = new Menu("Menu1");
		m.add(new MenuItem("MenuItem1")); // "MenuItem1"을 추가

		Menu sm = new Menu("SubMenu1"); // "SubMenu1"을 생성
		sm.add(new MenuItem("SubMenuItem1")); // "SubMenu1"에 "SubMenuItem1"을 추가
		sm.add(new MenuItem("SubMenuItem2")); // "SubMenu1"에 "SubMenuItem2"를 추가
		m.add(sm); // "Menu1"에 "SubMenu1"을 추가

		m.add(new MenuItem("MenuItem2")); // "Menu1"에 "MenuItem2"를 추가

		mb.add(m); // MenuBar에 "Menu1"을 추가
		f.setMenuBar(mb); // Frame에 MenuBar를 설정

		f.setSize(200, 200); // Frame의 크기를 200x200으로 설정
		f.setBackground(Color.white); // Frame의 배경색을 흰색으로 설정
		f.setVisible(true); // Frame을 표시
	}
}
