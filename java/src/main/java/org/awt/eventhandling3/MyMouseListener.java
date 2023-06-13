package org.awt.eventhandling3;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyMouseListener extends MouseAdapter {
	public void mouseClicked(MouseEvent ev) {
		Point p = ev.getPoint();
		String btn = switch (ev.getButton()) {
			case MouseEvent.BUTTON1 -> "Left Button";
			case MouseEvent.BUTTON2 -> "Middle Button";
			case MouseEvent.BUTTON3 -> "Right Button";
			default -> null;
		};
		System.out.println("Mouse " + btn + " clicked : " + p);
	}
}