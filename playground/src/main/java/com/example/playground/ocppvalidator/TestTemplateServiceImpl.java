package com.example.playground.ocppvalidator;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestTemplateServiceImpl implements TestTemplateService {
	private final TestTemplateRepository testTemplateRepository;

	@Override
	public List<TestTemplate> registerTemplates(List<TestTemplate> testTemplates) {
		return testTemplateRepository.saveAll(testTemplates);
	}

	@Override
	public List<TestTemplate> getAllTemplates() {
		return testTemplateRepository.findAll();
	}

	public TestTemplate getTemplate(Integer sequence) {
		return testTemplateRepository.get(sequence);
	}
}