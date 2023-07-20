package com.example.playground.ocppvalidator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestScenarioService {
	private final TestTemplateService testTemplateService;

	public List<TestScenario> generateScenarios() {
		List<TestTemplate> templates = testTemplateService.getAllTemplates();

		return null;
	}
}