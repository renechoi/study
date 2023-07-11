package org.refactoring.layerproblem.servicedependency;

import org.refactoring.exception._2.member.MemberRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    private final MemberRepository memberRepository;
    private final CouponIssueService couponIssueService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void signUp(final MemberSignUpRequest dto) {
        final Member member = memberRepository.save(dto.toEntity());
        eventPublisher.publishEvent(new MemberSignedUpEvent(member));
        couponIssueService.issueSignUpCoupon(member.getId());
    }
}