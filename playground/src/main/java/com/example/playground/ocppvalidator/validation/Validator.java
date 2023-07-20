package com.example.playground.ocppvalidator.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.core.log.LogMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.playground.ocppvalidator.TestScenario;

public interface Validator {
    boolean supports(TestScenario scenario);
    Validator shouldHave(TestScenario scenario);
    Validator whenTestedWith(LogMessage logMessage);
    void thenValidate();
    Validator setNext(Validator nextValidator);
}
