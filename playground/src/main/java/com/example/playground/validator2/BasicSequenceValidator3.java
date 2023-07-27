package com.example.playground.validator2;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.playground.ocppvalidator.TestAction;
import com.example.playground.ocppvalidator.TestTemplate;

@Component
@Order(1)
public class BasicSequenceValidator3 implements Validator {

    private TestTemplate testTemplate;
    private List<LogEvent> logEvents;
    private Validator nextValidator;
    private ResultRecorder resultRecorder;

    public BasicSequenceValidator3(@Qualifier("basicSequenceResultRecorder") ResultRecorder resultRecorder) {
        this.resultRecorder = resultRecorder;
    }

    @Override
    public boolean supports(TestTemplate testTemplate) {
        return testTemplate instanceof BasicSequenceTestTemplate;
    }

    @Override
    public boolean supports(TestTemplate testTemplate) {
        if (testTemplate instanceof BasicSequenceTestTemplate) {
            return true;
        }

        if (nextValidator != null) {
            return nextValidator.supports(testTemplate);
        }

        return false;
    }




    @Override
    public Validator given(TestTemplate testTemplate) {
        this.testTemplate = testTemplate;
        return this;
    }

    @Override
    public Validator whenTestedWith(List<LogEvent> logEvents) {
        this.logEvents = logEvents;
        return this;
    }

    @Override
    public void thenValidate() {
        TestAction currentAction = testTemplate.getInitialAction();
        for (int i = 0; i < logEvents.size(); i += 2) {
            LogEvent firstEvent = logEvents.get(i);
            LogEvent secondEvent = (i + 1 < logEvents.size()) ? logEvents.get(i + 1) : null;

            if (validateFirstEvent(currentAction, firstEvent)) {
                validateSecondEvent(currentAction, secondEvent);
            }
        }
        callNext();
    }

    @Override
    public void thenValidate() {
        if (this.supports(testTemplate)) {
            // 로직 구현

            callNext();
        } else if (nextValidator != null) {
            // 다음으로 넘김
            nextValidator.given(testTemplate).whenTestedWith(logEvents).thenValidate();
        }
    }



    private boolean validateFirstEvent(TestAction currentAction, LogEvent firstEvent) {
        if (currentAction.notMatches(firstEvent)) {
            resultRecorder.recordFailure();
            return false;
        }
        return true;
    }

    private void validateSecondEvent(TestAction currentAction, LogEvent secondEvent) {
        boolean isMatched = secondEvent != null && testTemplate.nextActions(currentAction).stream().anyMatch(action -> action.matches(secondEvent));
        resultRecorder.records(isMatched);
    }


    private void callNext() {
        Stream.iterate(nextValidator, Objects::nonNull, Validator::getNext)
            .filter(validator -> validator.supports(testTemplate))
            .findFirst()
            .ifPresent(validator -> validator.given(testTemplate).whenTestedWith(logEvents).thenValidate());
    }


    @Override
    public Validator setNext(Validator nextValidator) {
        this.nextValidator = nextValidator;
        return this;
    }

    @Override
    public Validator getNext() {
        return nextValidator;
    }

    @Override
    public ResultRecorder getRecorder() {
        return resultRecorder;
    }
}