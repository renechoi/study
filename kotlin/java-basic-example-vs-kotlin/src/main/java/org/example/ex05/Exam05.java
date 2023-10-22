package org.example.ex05;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author : Rene
 * @since : 2023/10/22
 */
public class Exam05 {

	private Predicate<String> stringPredicate = new Predicate<String>() {
		@Override
		public boolean test(String s) {
			return s.equals("?");
		}
	};

	// 고차함수 -> 함수를 파라미터로 넘기기
	public Exam05(){

		var strList = List.of(
			"1",
			"2",
			"홍길동",
			"ㅇㅇㅇ"
		);

		// predicate라는 functional interface를 람다식으로
		var result = strList.stream().filter(it->{
			return it.equals("?");
		}).toList();


		// 위에서 선언한 stringPredicate 를 넘겨줄 수도 있다.
		var result2 = strList.stream().filter(stringPredicate).toList();


		// 어차피 메서드가 하나이기 때문에 화살표 함수 -> 람다식으로 표현한 것임

		// 코틀린에서는 ?
	}

}
