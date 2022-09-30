package com.isoft.validators.annotations;

import com.isoft.validators.classes.PasswordValidatorHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PasswordValidatorHandler.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
     String message() default "The password must contains at least one lowercase letter,one uppercase letter,one special character and one number";

     String regex() default "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?!\\s+).{8,}$";

     Class<?>[] groups() default {};

     Class<? extends Payload>[] payload() default {};
}
