package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMap {
	public static void main(String[] args) {

		List<Integer> integers = Arrays.asList(3, 6, -4);
		Stream<Integer> integerStream = integers.stream().map(x -> x * 2);
		List<Integer> collect = integerStream.collect(Collectors.toList());
		System.out.println(collect);

		Stream<String> stringStream = integers.stream().map(x -> "nuumber is " + x);

		User user1 = new User().setId(101).setName("Alice").setVerified(true).setEmailAddress("123@123.com");
		User user2 = new User().setId(102).setName("Bob").setVerified(false).setEmailAddress("123@123.com");
		User user3 = new User().setId(103).setName("Chalie").setVerified(false).setEmailAddress("123@123.com");

		List<String> emailList = Stream.of(user1, user2, user3)
			.map(User::getEmailAddress)
			.toList();

	}
}
