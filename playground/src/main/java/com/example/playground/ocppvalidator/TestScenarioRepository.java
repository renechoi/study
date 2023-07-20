package com.example.playground.ocppvalidator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TestScenarioRepository {
    private List<TestScenario> scenarios = new ArrayList<>();

    public TestScenario save(TestScenario testScenario) {
        scenarios.add(testScenario);
        return testScenario;
    }

    public List<TestScenario> findAll() {
        return new ArrayList<>(scenarios);
    }

    public TestScenario getByIndex(Integer index) {
        return scenarios.get(index);
    }
}
