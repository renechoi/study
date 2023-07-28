// package com.example.playground.validator4;
//
// import java.util.List;
//
// import org.springframework.stereotype.Service;
//
// import com.example.playground.ocppvalidator.TestTemplate;
// import com.example.playground.ocppvalidator.validation.ValidationService;
//
// @Service
// public class ValidationServiceImpl implements ValidationService {
//     // ...
//
//     @Override
//     public void validateTemplateWithLogEvents(List<LogEvent> logEvents, List<TestTemplate> templates) {
//         for (TestTemplate template : templates) {
//             boolean validationSucceeded = validators.get(0)
//                 .given(template)
//                 .whenTestedWith(logEvents)
//                 .ifSupport()
//                 .thenValidate();
//             if (!validationSucceeded) {
//                 System.out.println("Validation failed for template: " + template);
//             }
//         }
//     }
// }
