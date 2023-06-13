package org.awt;

import java.awt.*;

public class BorderLayoutTest extends Frame {
	public BorderLayoutTest() {
		super("BorderLayout");
		setSize(300, 300);
		setLayout(new BorderLayout(10, 20));

		Label l_east = new Label("EAST", Label.CENTER);
		l_east.setBackground(Color.red);

		Label l_south = new Label("SOUTH", Label.CENTER);
		l_south.setBackground(Color.green);

		Label l_west = new Label("WEST", Label.CENTER);
		l_west.setBackground(Color.blue);

		Label l_north = new Label("NORTH", Label.CENTER);
		l_north.setBackground(Color.yellow);

		Label l_center = new Label("CENTER", Label.CENTER);
		l_center.setBackground(Color.gray);

		add(l_east, BorderLayout.EAST); // 동쪽에 l_east 레이블 추가
		add(l_south, BorderLayout.SOUTH); // 남쪽에 l_south 레이블 추가
		add(l_west, BorderLayout.WEST); // 서쪽에 l_west 레이블 추가
		add(l_north, BorderLayout.NORTH); // 북쪽에 l_north 레이블 추가
		add(l_center, BorderLayout.CENTER); // 가운데에 l_center 레이블 추가
	}

	public static void main(String[] args) {
		Frame f = new BorderLayoutTest();
		f.setVisible(true);
	}
}
