package com.example.playground.ocppvalidator;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestScenario {
	private List<TestTemplate> templates;

	public String getSequence() {
		return null;
	}
}


