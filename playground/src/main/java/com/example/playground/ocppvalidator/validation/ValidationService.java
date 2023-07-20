package com.example.playground.ocppvalidator.validation;

import java.util.List;

import org.springframework.core.log.LogMessage;

import com.example.playground.ocppvalidator.TestScenario;

public interface ValidationService {
    void validateScenariosWithLogMessages(List<LogMessage> logMessages, List<TestScenario> scenarios);
}
