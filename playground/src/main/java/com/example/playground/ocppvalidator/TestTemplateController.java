package com.example.playground.ocppvalidator;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestTemplateController {
	private final TestTemplateFacade testTemplateFacade;

	@PostMapping("/testTemplate")
	public List<TestScenario> registerTemplates(@RequestBody List<TestTemplate> testTemplates) {
		return testTemplateFacade.registerTemplates(testTemplates);
	}
}
