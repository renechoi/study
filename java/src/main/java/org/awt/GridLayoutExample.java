package org.awt;

import java.awt.*;

public class GridLayoutExample {
	public static void main(String[] args) {
		// Frame 생성
		Frame frame = new Frame("Calculator");
		frame.setSize(300, 300);

		// GridLayout을 사용하여 컨테이너의 레이아웃 설정
		GridLayout layout = new GridLayout(4, 3, 5, 5); // 4행 3열의 그리드 레이아웃, 간격 설정
		frame.setLayout(layout);

		// 레이블(Label) 생성하여 컨테이너에 추가
		frame.add(new Label("7", Label.CENTER));
		frame.add(new Label("8", Label.CENTER));
		frame.add(new Label("9", Label.CENTER));
		frame.add(new Label("4", Label.CENTER));
		frame.add(new Label("5", Label.CENTER));
		frame.add(new Label("6", Label.CENTER));
		frame.add(new Label("1", Label.CENTER));
		frame.add(new Label("2", Label.CENTER));
		frame.add(new Label("3", Label.CENTER));
		frame.add(new Label("0", Label.CENTER));
		frame.add(new Label(".", Label.CENTER));
		frame.add(new Label("=", Label.CENTER));

		// Frame을 화면에 표시
		frame.setVisible(true);
	}
}
