package org.example.desiginpattern.strategy.service;

import org.example.desiginpattern.domain.User;

public class VerifyYourEmailAddressEmailProvider implements EmailProvider {

	@Override
	public String getEmail(User user) {
		return "'Verify Your Email Address' email for " + user.getName();
	}

}