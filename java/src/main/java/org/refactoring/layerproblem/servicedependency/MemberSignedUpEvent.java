package org.refactoring.layerproblem.servicedependency;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberSignedUpEvent {

    private final Member member;
}