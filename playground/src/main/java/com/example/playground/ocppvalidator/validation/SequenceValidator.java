package com.example.playground.ocppvalidator.validation;

import org.springframework.core.log.LogMessage;
import org.springframework.stereotype.Component;

import com.example.playground.ocppvalidator.TestScenario;

@Component
public class SequenceValidator implements Validator {
    private TestScenario scenario;
    private LogMessage logMessage;
    private Validator nextValidator;

    @Override
    public boolean supports(TestScenario scenario) {
        return true;  // Just for the illustration
    }

    @Override
    public Validator shouldHave(TestScenario scenario) {
        this.scenario = scenario;
        return this;
    }

    @Override
    public Validator whenTestedWith(LogMessage logMessage) {
        this.logMessage = logMessage;
        return this;
    }

    @Override
    public void thenValidate() {
        // Implement the sequence validation logic here
        if (nextValidator != null && nextValidator.supports(scenario)) {
            nextValidator.shouldHave(scenario).whenTestedWith(logMessage).thenValidate();
        }
    }

    @Override
    public Validator setNext(Validator nextValidator) {
        this.nextValidator = nextValidator;
        return this;
    }
}
