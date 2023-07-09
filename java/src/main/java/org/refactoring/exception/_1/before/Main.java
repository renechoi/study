package org.refactoring.exception._1.before;

public class Main {

	public static void main(String[] args) {

		Password test = new Password();
		String password = null;
		test.setPassword(password);
		
		password = "abcd";
		test.setPassword(password);
		
		password = "abcde";
		test.setPassword(password);
		
		password = "abcde#1";
		test.setPassword(password);
	}
}
