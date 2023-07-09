package org.refactoring.ifelseproblem.after;

public class BronzeCustomer extends Customer {

	BronzeCustomer(String customerName) {
		super(customerName);
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
