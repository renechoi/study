// package org.refactoring.layerproblem.servicedependency;
//
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.event.TransactionalEventListener;
//
// import lombok.RequiredArgsConstructor;
//
// @Component
// @RequiredArgsConstructor
// public class MemberEventHandler {
//
//     private final EmailSenderService emailSenderService;
//
//     @Async
//     @TransactionalEventListener
//     public void memberSignedUpEventListener(MemberSignedUpEvent dto){
//         emailSenderService.sendSignUpEmail(dto.getMember());
//     }
// }