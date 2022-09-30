package com.isoft.validators.classes;

import com.isoft.validators.annotations.FieldsMatcher;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FieldsMatcherHandler implements ConstraintValidator<FieldsMatcher, Object> {
    private FieldsMatcher matcher;
    private String fieldOne;
    private String fieldTwo;
    private String message;

    @Override
    public void initialize(FieldsMatcher constraintAnnotation) {
        matcher = constraintAnnotation;
        this.fieldOne = matcher.fieldOne();
        this.fieldTwo = matcher.fieldTwo();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper form = new BeanWrapperImpl(value);
        return Objects.equals(form.getPropertyValue(fieldOne), form.getPropertyValue(fieldTwo));
    }
}
