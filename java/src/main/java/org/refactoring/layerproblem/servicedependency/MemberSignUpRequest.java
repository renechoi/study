package org.refactoring.layerproblem.servicedependency;

import lombok.Getter;

@Getter
public class MemberSignUpRequest {
    private String name;

    public Member toEntity() {
        return new Member(name);
    }
}