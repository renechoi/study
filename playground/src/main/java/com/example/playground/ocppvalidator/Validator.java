package com.example.playground.ocppvalidator;

import java.util.List;

import org.springframework.core.log.LogMessage;

public class Validator {
    private TestScenario scenario;
    private List<LogMessage> logMessages;

    public Validator shouldHave(TestScenario scenario) {
        this.scenario = scenario;
        return this;
    }

    public Validator whenTestedWith(List<LogMessage> logMessages) {
        this.logMessages = logMessages;
        return this;
    }

    public void thenValidate() {

    }
}
