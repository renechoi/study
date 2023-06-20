package org.example.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPipeline {
	public static void main(String[] args) {

		User user1 = new User().setId(101).setName("Alice").setVerified(true).setEmailAddress("123@123.com");
		User user2 = new User().setId(102).setName("Bob").setVerified(false).setEmailAddress("123@123.com");
		User user3 = new User().setId(103).setName("Chalie").setVerified(false).setEmailAddress("123@123.com");

		Stream.of(user3, user2, user1)
			.filter(User::isVerified)
			.map(User::getEmailAddress)
			.collect(Collectors.toList());
	}
}
