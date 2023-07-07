package org.designpattern.proxy;

public class ProxyMain {
	public static void main(String[] args) {
		Printable print = new PrinterProxy("Alice");
		System.out.println("현재의 이름은" + print.getPrinterName() + "입니다.");
		print.setPrinterName("Bob");
		System.out.println("현재의 이름은" + print.getPrinterName() + "입니다.");
		print.print("Hello, world.");

		print.print("test");
		print.setPrinterName("Tomas");
		System.out.println("현재의 이름은" + print.getPrinterName() + "입니다.");
	}
}
