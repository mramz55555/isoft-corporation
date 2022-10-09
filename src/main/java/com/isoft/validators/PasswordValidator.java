package com.isoft.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PasswordValidatorHandler.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "The password must contains at least one lowercase letter,one uppercase letter,one special character, one number and most has at least 8 characters";

     String regex() default "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?!\\s+).{8,}$";

     Class<?>[] groups() default {};

     Class<? extends Payload>[] payload() default {};
}
