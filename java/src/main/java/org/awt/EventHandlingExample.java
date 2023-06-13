package org.awt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventHandlingExample {
	public static void main(String[] args) {
		Frame frame = new Frame("Event Handling Example");
		frame.setSize(300, 200);

		Button button = new Button("Click Me");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Clicked"); // 버튼이 클릭되었을 때 출력되는 메시지
			}
		});
		frame.add(button);

		Checkbox checkbox = new Checkbox("Check Me");
		checkbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (checkbox.getState()) {
					System.out.println("Checkbox Checked"); // 체크박스가 체크되었을 때 출력되는 메시지
				} else {
					System.out.println("Checkbox Unchecked"); // 체크박스가 체크 해제되었을 때 출력되는 메시지
				}
			}
		});
		frame.add(checkbox);

		Label label = new Label("Move Mouse Here");
		label.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse Clicked"); // 마우스가 클릭되었을 때 출력되는 메시지
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse Pressed"); // 마우스 버튼이 눌렸을 때 출력되는 메시지
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse Released"); // 마우스 버튼이 릴리즈(놓아졌을 때) 되었을 때 출력되는 메시지
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Mouse Entered"); // 마우스가 컴포넌트 영역에 진입했을 때 출력되는 메시지
			}

			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("Mouse Exited"); // 마우스가 컴포넌트 영역에서 벗어났을 때 출력되는 메시지
			}
		});
		frame.add(label);

		frame.setVisible(true);
	}
}
