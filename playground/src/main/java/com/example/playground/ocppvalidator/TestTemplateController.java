package com.example.playground.ocppvalidator;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class TestTemplateController {
	private final TestTemplateService testTemplateService;

	@PostMapping("/testTemplate")
	public List<TestTemplate> registerTemplates(@RequestBody List<TestTemplate> testTemplates){
		return testTemplateService.registerTemplates(testTemplates);
	}
}
