package com.isoft.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidatorHandler implements ConstraintValidator<PasswordValidator, String> {
    private PasswordValidator validator;
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        validator = constraintAnnotation;
        pattern = Pattern.compile(validator.regex());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        matcher = pattern.matcher(value);
        Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?!\\s+).{8,}$");
        return matcher.matches();
    }
}