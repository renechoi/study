package com.example.playground.ocppvalidator;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestTemplateFacade {
	private final TestTemplateService testTemplateService;
	private final TestScenarioService testScenarioService;

	public List<TestScenario> registerTemplates(List<TestTemplate> testTemplates) {
		testTemplateService.registerTemplates(testTemplates);
		return testScenarioService.generateScenarios();
	}
}