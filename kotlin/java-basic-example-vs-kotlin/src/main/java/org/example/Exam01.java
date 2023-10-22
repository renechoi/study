package org.example;

/**
 * @author : Rene
 * @since : 2023/10/22
 */
public class Exam01 {

	public Exam01(){
		String name = "홍길동";

		String format ="사용자의 이름은 : %s";
		String result = String.format(format, name);
		System.out.println(result);

		int age = 10;
		Integer _age = 20;

		double d = 10d;
		Double _d = 20.0;

		float f = 20f;
		Float _f = 20f;

		long l = 10L;
		Long _l = 10L;



	}
}
