package org.example.stream.advanced;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.example.stream.User;

public class Maxandmin {
	public static void main(String[] args) {
		Optional<Integer> max1 = Stream.of(5, 3, 6, 2, 1).max((x, y) -> x - y);
		Optional<Integer> max2 = Stream.of(5, 3, 6, 2, 1).max(Comparator.comparingInt(x -> x));
		Optional<Integer> max3 = Stream.of(5, 3, 6, 2, 1).max(Integer::compareTo);

		User user1 = new User().setId(101).setName("Alice").setVerified(true).setEmailAddress("123@123.com");
		User user2 = new User().setId(102).setName("Bob").setVerified(false).setEmailAddress("123@123.com");
		User user3 = new User().setId(103).setName("Chalie").setVerified(false).setEmailAddress("123@123.com");

		User user = Stream.of(user1, user2, user3).min(Comparator.comparing(User::getName)).get();
		System.out.println(user);

		long count = Stream.of(1, -4, 5, -3, 6).filter(x -> x > 0).count();
		System.out.println(count);

		// 최근 24시간 이후 가입한 유저들 중에서 verified되지 않은 유저 찾기
		LocalDateTime now = LocalDateTime.now();
		user1.setCreatedAt(now.minusDays(2));
		user2.setCreatedAt(now.minusHours(10));
		user3.setCreatedAt(now.minusHours(27));

		Stream.of(user1,user2,user3)
			.filter(u -> user.getCreatedAt().isAfter(now.minusDays(1)))
			.filter(u ->!user.isVerified())
			.count();
	}


}
