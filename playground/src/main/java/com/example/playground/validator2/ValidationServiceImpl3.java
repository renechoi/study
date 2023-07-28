// package com.example.playground.validator2;
//
// import java.util.List;
// import java.util.stream.IntStream;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import com.example.playground.ocppvalidator.TestTemplate;
// import com.example.playground.ocppvalidator.validation.ValidationService;
//
//
// @Service
// public class ValidationServiceImpl3 implements ValidationService {
//
//
//     private final List<Validator> validators;
//
//     @Autowired
//     public ValidationServiceImpl3(List<Validator> validators) {
//         this.validators = validators;
//         setupValidatorChain();
//     }
//
//     private void setupValidatorChain() {
//         IntStream.range(0, validators.size() - 1)
//                 .forEach(i -> validators.get(i).setNext(validators.get(i + 1)));
//     }
//
//     @Override
//     public String validate(List<LogEvent> logEvents, List<TestTemplate> templates) {
//         validateTemplateWithLogEvents(logEvents, templates);
//
//         StringBuilder stringBuilder = new StringBuilder();
//
//         validators.forEach(validator -> stringBuilder.append(validator.getRecorder().getSummary()).append("\n"));
//         System.out.println(stringBuilder);
//         return stringBuilder.toString();
//     }
//
//
//     // @Override
//     // public void validateTemplateWithLogEvents(List<LogEvent> logEvents, List<TestTemplate> templates) {
//     //     templates.forEach(template -> {
//     //         if (validators.get(0).supports(template)) {
//     //             validators.get(0).given(template).whenTestedWith(logEvents).thenValidate();
//     //         }
//     //     });
//     // }
//
//     @Override
//     public void validateTemplateWithLogEvents(List<LogEvent> logEvents, List<TestTemplate> templates) {
//         templates.forEach(template -> validators.get(0).given(template).whenTestedWith(logEvents).thenValidate());
//     }
//
//
//
//
// }
