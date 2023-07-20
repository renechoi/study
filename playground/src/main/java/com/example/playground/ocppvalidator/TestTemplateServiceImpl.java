package com.example.playground.ocppvalidator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TestTemplateServiceImpl implements TestTemplateService {
	private Map<Integer, TestTemplate> templates = new LinkedHashMap<>();

	@Override
	public List<TestTemplate> registerTemplates(List<TestTemplate> testTemplates) {
		testTemplates.forEach(template -> templates.put(template.getSequence(), template));
		return testTemplates;
	}

	public TestTemplate getTemplate(Integer sequence) {
		return templates.get(sequence);
	}
}
