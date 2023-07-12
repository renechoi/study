package org.refactoring.exception._2.customannotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import org.refactoring.exception._2.order.OrderSheetFormValidator;

@Documented
@Constraint(validatedBy = OrderSheetFormValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderSheetForm {

    String message() default "Order sheet form is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}