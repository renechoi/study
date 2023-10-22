package org.example.ex03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Rene
 * @since : 2023/10/22
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class User {
	private String name;
	private int age;
}

public class Exam03 {

	public Exam03() {
		var userList = new ArrayList<User>();
		userList.add(new User("1", 10));
		userList.add(new User("2", 10));
		userList.add(new User("3", 10));

		var list = Arrays.asList(
			new User("4" , 20),
			new User("5" , 20),
			new User("6" , 20)
		);

		userList.forEach(System.out::println);

		for (int i =0; i< userList.size(); i++){
			var dto = userList.get(i);
			System.out.println("i = " + i);
		}


		// 자바에서도 immutable을 제공 -> but 가변 메서드가 사용되기 때문에 -> 에러 발생
		var immutable = Collections.unmodifiableCollection(userList);
		immutable.add(new User("5", 60));

	}

}
