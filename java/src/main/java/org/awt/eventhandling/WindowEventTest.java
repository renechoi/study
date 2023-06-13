package org.awt.eventhandling;

import java.awt.*;

class MyFrame extends Frame {
	public MyFrame(String title) {
		super(title);
		this.setSize(400, 300);
		this.setVisible(true);
		//이벤트 리스너 등록
		this.addWindowListener(new MyListener( ));
	}

	public void paint(Graphics g) {
		g.drawString("Hello AWT", 150, 150);
	}
}

public class WindowEventTest {
	public static void main(String args[]) {
		MyFrame myFrame = new MyFrame("Hello AWT");
	}
}