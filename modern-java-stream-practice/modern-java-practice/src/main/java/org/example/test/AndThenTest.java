package org.example.test;

public class AndThenTest {

	public static void main(String[] args) {

		Function<Integer, Integer> multiplyByTwo = (x) -> x * 2;
		Function<Integer, Integer> addOne = (x) -> x + 1;

		Function<Integer, Integer> multiplyAndAdd = multiplyByTwo.andThen(addOne);

		// 입력 3에 대해 multiplyByTwo 함수를 실행한 후 addOne 함수를 실행한다.
		int result = multiplyAndAdd.apply(3);

		System.out.println(result); // 출력: 7 (3 * 2 + 1)
	}


}
