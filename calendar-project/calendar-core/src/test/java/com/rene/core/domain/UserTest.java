package com.rene.core.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.rene.core.domain.entity.Schedule;
import com.rene.core.domain.entity.User;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

	private LocalDateTime now = LocalDateTime.now();
	private User user1 = new User(
		"user1",
		"email@email",
		"password",
		LocalDateTime.now().toLocalDate());


	@Test
	void createDomainFromSchedule() {
		final Schedule taskSchedule = Schedule.task("title", "desc", now, user1);
		final Task task = taskSchedule.toTask();
		assertEquals("user1",
			task.getWriter()
				.getName());

		final Schedule notiSchedule = Schedule.notification("title", now, user1);
		final Notification notification = notiSchedule.toNotification();
		assertEquals("user1",
			notification.getWriter()
				.getName());

		final Schedule eventSchedule = Schedule.event("title", "desc", now, now, user1);
		final Event event = eventSchedule.toEvent();
		assertEquals("user1",
			event.getWriter()
				.getName());
	}
}