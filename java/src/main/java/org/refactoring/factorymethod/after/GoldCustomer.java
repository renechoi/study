package org.refactoring.factorymethod.after;

public class GoldCustomer extends Customer {

	private GoldCustomer(String customerName) {
		super(customerName);
	}

	public static GoldCustomer create(String customerName) {
		return new GoldCustomer(customerName);
	}

	@Override
	public String getCustomerGrade() {
		return "GOLD";
	}

	@Override
	public int calcPrice(int price) {
		return price - (int)(price * 0.1);
	}

	@Override
	public int calcBonusPoint(int price) {
		return bonusPoint += (price * 0.1);
	}

}
