package org.example.desiginpattern.builder;

import java.time.LocalDateTime;

import org.example.desiginpattern.domain.User;

public class BuilderEx {
	public static void main(String[] args) {
		User user = User.builder(1, "name").build();

		User.builder(2,"name2").withVerified(false).withEmailAddress("abc@emial.com").build();


		User.builder(3, "name3").with(builder -> {
			builder.emailAddress = "123@email.com";
			builder.isVerified = true;
			builder.createdAt = LocalDateTime.now();
		}).build();
	}
}
