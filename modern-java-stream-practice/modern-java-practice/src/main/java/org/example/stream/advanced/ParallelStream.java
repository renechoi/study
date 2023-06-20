package org.example.stream.advanced;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.example.stream.User;

public class ParallelStream {
	public static void main(String[] args) {

		User user1 = new User()
			.setId(101)
			.setName("Alice")
			.setEmailAddress("alice@fastcampus.co.kr")
			.setFriendUserIds(Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214));
		User user2 = new User()
			.setId(102)
			.setName("Bob")
			.setEmailAddress("bob@fastcampus.co.kr")
			.setFriendUserIds(Arrays.asList(204, 205, 206));
		User user3 = new User()
			.setId(103)
			.setName("Charlie")
			.setEmailAddress("charlie@fastcampus.co.kr")
			.setFriendUserIds(Arrays.asList(204, 205, 207, 218));
		User user4 = new User()
			.setId(104)
			.setName("Kim")
			.setEmailAddress("123@fastcampus.co.kr")
			.setFriendUserIds(Arrays.asList(123, 515, 34, 1));
		User user5 = new User()
			.setId(105)
			.setName("Lee")
			.setEmailAddress("345@fastcampus.co.kr")
			.setFriendUserIds(Arrays.asList(43, 50, 45, 15));
		List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

		EmailService emailService = new EmailService();

		long startTime = System.currentTimeMillis();
		users.stream().filter(user -> !user.isVerified())
			.forEach(emailService::sendVerifyYourEmailEmail);
		long endTime = System.currentTimeMillis();

		System.out.println("순차처리: " + (endTime-startTime) + "ms"); // 5 ~ 9ms


		startTime = System.currentTimeMillis();
		users.stream().parallel().filter(user -> !user.isVerified())
			.forEach(emailService::sendVerifyYourEmailEmail);
		endTime = System.currentTimeMillis();
		System.out.println("병렬처리: " + (endTime-startTime) + "ms"); // 3 ~ 6ms


		users.parallelStream().map(user->{
			System.out.println("대문자" + user.getId());
			user.setName(user.getName().toUpperCase());
		return user;
		})
			.map(user -> {
					System.out.println("set " + user.getId());
					user1.setVerified(true);
					return user;
				})
			.collect(Collectors.toList());
	}
}
