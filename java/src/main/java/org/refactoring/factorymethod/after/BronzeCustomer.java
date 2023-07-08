package org.refactoring.factorymethod.after;

public class BronzeCustomer extends Customer {

	private BronzeCustomer(String customerName) {
		super(customerName);
	}

	public static BronzeCustomer create(String customerName) {
		return new BronzeCustomer(customerName);
	}

	public int calcPrice(int price) {
		return price;
	}

	public String getCustomerGrade() {
		return "BRONZE";
	}

	public int calcBonusPoint(int price) {
		return bonusPoint += (price * 0.01);
	}

}
