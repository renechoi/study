package org.example.desiginpattern.template.service;

import org.example.desiginpattern.domain.User;

public class InternalUserService extends AbstractUserService{
	@Override
	protected boolean validateUser(User user) {
		System.out.println("조금 다른 검증 방식");
		return user.isVerified();
	}

	@Override
	protected void writeToDB(User user) {
		System.out.println("조금 다르게 쓰는 방식");
	}
}
