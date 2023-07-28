// package com.example.playground.validator4;
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
//     public Validator ifSupport() {
//         if (!supports(testTemplate) && nextValidator != null) {
//             return nextValidator;
//         }
//         return this;
//     }
//
//     // ...
// }
