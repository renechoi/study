package org.example.ex02;

import org.example.Exam01;

/**
 * @author : Rene
 * @since : 2023/10/22
 */
public class Exam02 {

	private int a;

	public Exam02(){
		var b = 20; // <- 자바도 타입추론이 가능하다. 대신 자바에는 불변 변수 val은 없음 -> null도 안됨. 반드시 타입이 들어가야함

		int c = 30;

		Integer d = 20;

		Integer e = new Integer(null);

		callFunction(a);
		callFunction(b);
		callFunction(c);
		callFunction(d);
		callFunction(e);  // -> null로 들어오면서 에러 발생
	}

	public void callFunction(Integer i ){
		System.out.println("i = " + i);


		var temp = (i == null) ? "null 입니다" : i;
		System.out.println("temp = " + temp);
	}

}
