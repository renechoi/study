package org.refactoring.factorymethod.after;

public class Main {

	public static void main(String[] args) {

		Customer bronzeCustomer = BronzeCustomer.create("Tomas");
		Customer silverCustomer = SilverCustomer.create("Alice");
		Customer goldCustomer = GoldCustomer.create("Edward");

		int price = 10000;

		System.out.println(bronzeCustomer);
		System.out.println(bronzeCustomer.getCustomerName() + ": price :" + bronzeCustomer.calcPrice(price)
			+ ": point :" + bronzeCustomer.calcBonusPoint(price));
		System.out.println(silverCustomer);
		System.out.println(silverCustomer.getCustomerName() + ": price :" + silverCustomer.calcPrice(price)
			+ ": point :" + silverCustomer.calcBonusPoint(price));

		System.out.println(goldCustomer);
		System.out.println(goldCustomer.getCustomerName() + ": price :" + goldCustomer.calcPrice(price)
			+ ": point :" + goldCustomer.calcBonusPoint(price));

	}
}
