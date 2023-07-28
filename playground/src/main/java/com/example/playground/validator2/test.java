// package com.example.playground.validator2;
//
// import java.util.List;
//
// import com.example.playground.ocppvalidator.TestTemplate;
//
// /**
//  * @author : Rene
//  * @since : 2023/07/27
//  */
// public class test {
// 	@Override
// 	public void validateTemplateWithLogEvents(List<LogEvent> logEvents, List<TestTemplate> templates) {
// 		templates.forEach(template -> {
// 			Validator validator = findSupportedValidator(template);
// 			if (validator != null) {
// 				validator.given(template).whenTestedWith(logEvents).thenValidate();
// 			}
// 		});
// 	}
//
// 	private Validator findSupportedValidator(TestTemplate template) {
// 		for (Validator validator : validators) {
// 			if (validator.supports(template)) {
// 				return validator;
// 			}
// 		}
// 		return null;
// 	}
//
// }
