package org.example.desiginpattern.strategy.service;

import org.example.desiginpattern.domain.User;

public interface EmailProvider {
	String getEmail(User user);
}