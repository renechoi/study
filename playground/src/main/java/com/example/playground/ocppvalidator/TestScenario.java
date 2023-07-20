package com.example.playground.ocppvalidator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.playground.ocppvalidator.TestTemplate;
import com.example.playground.ocppvalidator.TestTemplateService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestScenario {
	private List<TestTemplate> templates;


}


