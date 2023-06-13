package org.awt;

import java.awt.*;

public class CheckboxTest {
	public static void main(String[] args) {
		Frame f = new Frame("Checkbox");
		f.setLayout(new FlowLayout());
		f.add(new Checkbox("Whiskey")); // "Whiskey" 체크박스 추가
		f.add(new Checkbox("Beer")); // "Beer" 체크박스 추가

		CheckboxGroup group = new CheckboxGroup();
		f.add(new Checkbox("Yes", false, group)); // "Yes" 라디오버튼 추가, 기본 선택값: false
		f.add(new Checkbox("No", true, group)); // "No" 라디오버튼 추가, 기본 선택값: true
		f.setSize(300, 80);
		f.setVisible(true);
	}
}
