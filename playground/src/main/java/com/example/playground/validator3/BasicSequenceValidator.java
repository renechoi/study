// package com.example.playground.validator3;
//
// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;
//
// @Component
// @Order(1)
// public class BasicSequenceValidator implements Validator {
//     // ...
//
//     @Override
//     public boolean thenValidate() {
//         if (!supports(testTemplate)) {
//             if (nextValidator != null) {
//                 return nextValidator.given(testTemplate).whenTestedWith(logEvents).thenValidate();
//             }
//             return false;
//         }
//
//         // 로직
//
//         return true;
//     }
//
//     // ...
// }
