package org.awt;

import java.awt.*;

public class CardLayoutTest extends Frame {

	public static void main(String[] args)
		throws Exception {
		CardLayoutTest f = new CardLayoutTest();
		f.setVisible(true);
		f.rotate();
	}

	CardLayout cardLayout;

	public CardLayoutTest() {
		super("CardLayout");
		setSize(300, 100);
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		add(new Label("안녕하세요.", Label.CENTER)); // "안녕하세요." 레이블 추가
		add(new Label("만나서 반가워요.", Label.CENTER)); // "만나서 반가워요." 레이블 추가
		add(new Label("다음에 또 만나요.", Label.CENTER)); // "다음에 또 만나요." 레이블 추가
	}

	public void rotate() throws Exception {
		cardLayout.first(this); // 첫 번째 카드 보이기
		Thread.sleep(1000);
		while (true) {
			cardLayout.next(this); // 다음 카드 보이기
			Thread.sleep(1000);
		}
	}
}
