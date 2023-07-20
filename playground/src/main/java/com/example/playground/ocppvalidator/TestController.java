package com.example.playground.ocppvalidator;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	private final TestFacade testFacade;

	@PostMapping("/testTemplate")
	public TestScenario registerTemplates(@RequestBody List<TestTemplate> testTemplates) {
		return testFacade.registerTemplates(testTemplates);
	}
}
