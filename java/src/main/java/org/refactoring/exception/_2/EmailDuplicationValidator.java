package org.refactoring.exception._2;

import java.text.MessageFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.refactoring.exception._2.customannotation.EmailUnique;
import org.refactoring.exception._2.member.MemberRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailDuplicationValidator implements ConstraintValidator<EmailUnique, String> {

	private final MemberRepository memberRepository;

	@Override
	public void initialize(EmailUnique emailUnique) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext cxt) {
		boolean isExistEmail = memberRepository.existsByEmail(email);

		if (isExistEmail) {
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate(
					MessageFormat.format("Email {0} already exists!", email))
				.addConstraintViolation();
		}
		return !isExistEmail;
	}
}