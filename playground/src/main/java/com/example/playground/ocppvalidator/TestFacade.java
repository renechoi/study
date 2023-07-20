package com.example.playground.ocppvalidator;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestFacade {
	private final TestService testService;

	public TestScenario registerTemplates(List<TestTemplate> testTemplates) {
		return testService.registerTemplates(testTemplates);
	}
}