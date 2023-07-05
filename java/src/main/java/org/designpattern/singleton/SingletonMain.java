package org.designpattern.singleton;

import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingletonMain {

	@Test
	public static void test(){

		ConnectionPool instance1 = ConnectionPool.getInstance();
		ConnectionPool instance2 = ConnectionPool.getInstance();
		Assertions.assertEquals(instance1, instance2);

		// JDK에서 이미 만들어놓은 싱글톤 예시
		Calendar calendar = Calendar.getInstance();
	}

	public static void main(String[] args) {
		test();
	}
}
