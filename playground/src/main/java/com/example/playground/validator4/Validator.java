package com.example.playground.validator4;

import com.example.playground.ocppvalidator.TestTemplate;

public interface Validator {
    boolean supports(TestTemplate testTemplate);
    Validator given(TestTemplate testTemplate);
    Validator whenTestedWith(List<LogEvent> logEvents);
    Validator ifSupport();
    boolean thenValidate();
    Validator setNext(Validator nextValidator);
    Validator getNext();
    ResultRecorder getRecorder();
}
