package com.example.playground.ocppvalidator.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.core.log.LogMessage;
import org.springframework.stereotype.Service;

import com.example.playground.ocppvalidator.TestScenario;

@Service
public class ValidationServiceImpl implements ValidationService {
	private final List<Validator> validators;

	public ValidationServiceImpl(List<Validator> validators) {
		this.validators = validators;
		setupValidatorChain();
	}

	private void setupValidatorChain() {
		IntStream.range(0, validators.size() - 1)
			.forEach(i -> validators.get(i).setNext(validators.get(i + 1)));
	}

	@Override
	public void validateScenariosWithLogMessages(List<LogMessage> logMessages, List<TestScenario> scenarios) {
		Validator validationChainStart = validators.get(0);
		scenarios.forEach(scenario ->
				logMessages.stream()
					.forEach(logMessage -> validationChainStart
						.shouldHave(scenario)
						.whenTestedWith(logMessage)
						.thenValidate()));
	}
}
