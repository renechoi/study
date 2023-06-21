package org.example.desiginpattern.template.service;

import org.example.desiginpattern.domain.User;

public class UserService extends AbstractUserService{
	@Override
	protected boolean validateUser(User user) {
		System.out.println("validating user " + user.getName());
		return user.getName() != null && user.getEmailAddress().isPresent();
	}

	@Override
	protected void writeToDB(User user) {
		System.out.println("write user");
	}
}
