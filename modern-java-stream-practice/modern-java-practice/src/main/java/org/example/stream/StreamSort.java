package org.example.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSort {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 3, -5, 77, 4, 5, 9, 12, -3, -20);
		System.out.println(numbers.stream().sorted().collect(Collectors.toList()));

		User user1 = new User().setId(101).setName("Kim").setVerified(true).setEmailAddress("123@123.com");
		User user2 = new User().setId(102).setName("Lee").setVerified(false).setEmailAddress("123@123.com");
		User user3 = new User().setId(103).setName("Bob").setVerified(false).setEmailAddress("123@123.com");
		User user4 = new User().setId(103).setName("Jane").setVerified(false).setEmailAddress("123@123.com");
		User user5 = new User().setId(103).setName("Amy").setVerified(false).setEmailAddress("123@123.com");

		List<User> users = Arrays.asList(user1, user2, user3, user4, user5)
			.stream()
			.sorted(Comparator.comparing(User::getName))
			.collect(Collectors.toList());

		System.out.println(users);
	}
}
