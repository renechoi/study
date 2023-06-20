package org.example.stream.advanced;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.stream.User;

public class ToMap {
	public static void main(String[] args) {
		Map<Integer, String> map = Stream.of(3, 5, -4, 2, 6)
			.collect(Collectors.toMap(x -> x, x -> "number is " + x));

		System.out.println(map.get(3));



		User user1 = new User().setId(101).setName("Alice").setVerified(true).setEmailAddress("123@123.com");
		User user2 = new User().setId(102).setName("Bob").setVerified(false).setEmailAddress("123@123.com");
		User user3 = new User().setId(103).setName("Chalie").setVerified(false).setEmailAddress("123@123.com");

		List<User> users = Arrays.asList(user1, user2, user3);
		Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
		System.out.println(userMap);
		System.out.println(userMap.get(103));

	}
}
