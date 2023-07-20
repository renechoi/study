package com.example.playground.ocppvalidator;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestFactory testFactory;

    @Override
    public TestScenario registerTemplates(List<TestTemplate> testTemplates) {
        return testFactory.createScenarioWithTemplates(testTemplates);
    }

    @Override
    public List<TestTemplate> getAllTemplates() {
        return testFactory.getAllTemplates();
    }

    public TestTemplate getTemplate(Integer sequence) {
        return testFactory.getTemplate(sequence);
    }
}
