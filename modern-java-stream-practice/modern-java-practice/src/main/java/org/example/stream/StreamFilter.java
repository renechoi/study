package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFilter {
	public static void main(String[] args) {
		Stream<Integer> numberStream = Stream.of(3, -5, 7, 10, -3);
		Stream<Integer> integerStream = numberStream.filter(x -> x > 0);


		User user1 = new User().setId(101).setName("Alice").setVerified(true).setEmailAddress("123@123.com");
		User user2 = new User().setId(102).setName("Bob").setVerified(false).setEmailAddress("123@123.com");
		User user3 = new User().setId(103).setName("Chalie").setVerified(false).setEmailAddress("123@123.com");

		List<User> users = Arrays.asList(user1, user2, user3);
		List<User> verifiedUsers = users.stream().filter(User::isVerified).collect(Collectors.toList());
	}
}
