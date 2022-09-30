package com.isoft.validators.annotations;

import com.isoft.validators.classes.FieldsMatcherHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldsMatcherHandler.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsMatcher {
    String message() default "Fields don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldOne();

    String fieldTwo();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsMatcher[] value();
    }
}
