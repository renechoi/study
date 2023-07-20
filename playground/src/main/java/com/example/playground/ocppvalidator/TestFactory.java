package com.example.playground.ocppvalidator;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TestFactory {
    private final TestTemplateRepository templateRepository;
    private final TestScenarioRepository scenarioRepository;

    public TestFactory(TestTemplateRepository templateRepository, TestScenarioRepository scenarioRepository) {
        this.templateRepository = templateRepository;
        this.scenarioRepository = scenarioRepository;
    }

    public TestScenario createScenarioWithTemplates(List<TestTemplate> testTemplates) {
        List<TestTemplate> savedTemplates = templateRepository.saveAll(testTemplates);
        TestScenario testScenario = TestScenario.builder().templates(savedTemplates).build();
        return scenarioRepository.save(testScenario);
    }

    public List<TestTemplate> getAllTemplates() {
        return templateRepository.findAll();
    }

    public TestTemplate getTemplate(Integer sequence) {
        return templateRepository.get(sequence);
    }

    public List<TestScenario> getAllScenarios() {
        return scenarioRepository.findAll();
    }

    public TestScenario getScenarioByIndex(Integer index) {
        return scenarioRepository.getByIndex(index);
    }
}
