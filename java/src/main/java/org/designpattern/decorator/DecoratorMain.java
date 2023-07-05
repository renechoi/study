package org.designpattern.decorator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DecoratorMain {
	public static void main(String[] args) throws IOException {
		Coffee kenyaAmericano = new KenyaAmericano();
		kenyaAmericano.brewing();
		System.out.println();

		Coffee kenyaLatte = new Latte(kenyaAmericano);
		kenyaLatte.brewing();
		System.out.println();

		Mocha kenyaMocha = new Mocha(new Latte(new KenyaAmericano()));
		kenyaMocha.brewing();
		System.out.println();

		WhippedCream etiopiaWhippedMocha =
			new WhippedCream(new Mocha(new Latte( new EtiopiaAmericano())));
		etiopiaWhippedMocha.brewing();
		System.out.println();

		Socket socket = new Socket();
		new BufferedReader(new InputStreamReader(socket.getInputStream()));

	}
}
