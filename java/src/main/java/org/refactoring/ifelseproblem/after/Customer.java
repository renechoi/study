package org.refactoring.ifelseproblem.after;

public abstract class Customer {

	private String customerName;
	protected int bonusPoint;

	Customer(String customerName) {
		this.customerName = customerName;
	}

	public abstract String getCustomerGrade();

	public abstract int calcPrice(int price);

	public abstract int calcBonusPoint(int price);

	public String getCustomerName() {
		return customerName;
	}

	public String toString() {
		return customerName + "님의 멤버십 등급은 " + getCustomerGrade() + "입니다.";
	}
}

