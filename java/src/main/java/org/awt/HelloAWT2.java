package org.awt;

import java.awt.*;

class MyFrame extends Frame {
	public MyFrame(String title) {
		super(title);
		this.setSize(400, 300);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawString("Hello AWT", 150, 150);
	}
}

public class HelloAWT2 {
	public static void main(String args[]) {
		MyFrame myFrame = new MyFrame("Hello AWT");
	}
}