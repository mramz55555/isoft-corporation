package com.isoft.models;

import com.isoft.validators.annotations.FieldsMatcher;
import com.isoft.validators.annotations.PasswordValidator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@FieldsMatcher.List({
        @FieldsMatcher(fieldOne = "password", fieldTwo = "confirmPassword", message = "Passwords don't  match"),
        @FieldsMatcher(fieldOne = "email", fieldTwo = "confirmEmail", message = "emails don't  match")
})
public class User extends BaseInfo {
    @PasswordValidator
    @Length(min = 6, message = "password's length must be at least 6")
    private String password;
    @Transient
    private String confirmPassword;
    @Transient
    private String confirmEmail;
}
