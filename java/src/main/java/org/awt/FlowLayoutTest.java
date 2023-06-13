package org.awt;

import java.awt.*;

public class FlowLayoutTest extends Frame {
	public FlowLayoutTest() {
		super("FlowLayout");
		setSize(300, 100);
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));  // 수평간격, 수직 간격

		add(new Button("button1")); // 버튼 "button1" 추가
		add(new Button("button2")); // 버튼 "button2" 추가
		add(new Button("button3")); // 버튼 "button3" 추가
		add(new Button("button4")); // 버튼 "button4" 추가
		add(new Button("button5")); // 버튼 "button5" 추가
		add(new Button("button6")); // 버튼 "button6" 추가
	}

	public static void main(String[] args) {
		Frame f = new FlowLayoutTest();
		f.setVisible(true);
	}
}
