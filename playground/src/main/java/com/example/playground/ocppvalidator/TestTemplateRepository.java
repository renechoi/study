package com.example.playground.ocppvalidator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public class TestTemplateRepository {
    private Map<Integer, TestTemplate> templates = new LinkedHashMap<>();

    public List<TestTemplate> saveAll(List<TestTemplate> testTemplates) {
        testTemplates.forEach(template -> templates.put(template.getSequence(), template));
        return testTemplates;
    }

    public TestTemplate get(Integer sequence) {
        return templates.get(sequence);
    }

    public List<TestTemplate> findAll() {
        return new ArrayList<>(templates.values());
    }
}
