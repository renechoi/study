package org.awt;

import java.awt.*;

public class WindowFrame {
	public static void main(String[] args) {
		Frame f = new Frame("Frame");
		f.setSize(500, 400);
		f.setBackground(Color.white);
		f.setVisible(true);

		Window w = new Window(f) { 		// f 창을 부모로 하는 Window 객체 w를 생성.
			public void paint(Graphics g) {
				g.drawString("Window", 10, 50);
			}
		};

		Rectangle r = new Rectangle(50, 50, 100, 100);		// 크기가 100x100이고 (50, 50) 위치에 있는 Rectangle 객체 r을 생성

		w.setBounds(r);		// w 윈도우의 위치와 크기를 r로 설정
		w.setBackground(Color.yellow);		// w 윈도우의 배경색을 노란색으로 설정
		w.setVisible(true);		// w 윈도우를 표시
		w.toFront(); 		// w 윈도우를 최상위로 가져오기
	}
}
