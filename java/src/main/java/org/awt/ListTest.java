package org.awt;

import java.awt.*;

public class ListTest {
	public static void main(String[] args) {
		Frame f = new Frame("List");
		List l = new List();
		l.add("Red");
		l.add("Green");
		l.add("Blue");
		l.add("Yello");
		l.add("Cyan");
		f.add(l);
		f.setSize(200, 100);
		f.setVisible(true);
	}
}