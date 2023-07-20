package com.example.playground.ocppvalidator;

import java.util.List;

public interface TestService {
	TestScenario registerTemplates(List<TestTemplate> testTemplates);

	List<TestTemplate> getAllTemplates();

}