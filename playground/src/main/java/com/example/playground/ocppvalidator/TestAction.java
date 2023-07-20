package com.example.playground.ocppvalidator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class TestAction {
	private MessageType type;
	private String message;


	@Getter
	@RequiredArgsConstructor
	public enum MessageType {
		REQUEST, RESPONSE
	}

}