package com.example.playground.ocppvalidator;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestTemplate {

	private Integer sequence;

	private List<TestAction> actions;

}



