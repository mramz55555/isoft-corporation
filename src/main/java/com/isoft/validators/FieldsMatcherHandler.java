package com.isoft.validators;

import com.isoft.models.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FieldsMatcherHandler implements ConstraintValidator<FieldsMatcher, Object> {
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    private FieldsMatcher matcher;
    private String fieldOne;
    private String fieldTwo;
    private String message;

    @Override
    public void initialize(FieldsMatcher constraintAnnotation) {
        matcher = constraintAnnotation;
        if (!constraintAnnotation.fieldOne().equals(EMAIL) && !constraintAnnotation.fieldOne().equals(PASSWORD))
            throw new IllegalArgumentException("Field name is not correct");
        fieldOne = matcher.fieldOne();
        fieldTwo = matcher.fieldTwo();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
//        BeanWrapper form = new BeanWrapperImpl(value);
        User user = (User) value;
//        if (user.getPassword().startsWith("$2a"))
//            return true;

        return fieldOne.equals(EMAIL) ? Objects.equals(user.getEmail(), user.getConfirmEmail()) :
                Objects.equals(user.getPassword(), user.getConfirmPassword());
    }
}
