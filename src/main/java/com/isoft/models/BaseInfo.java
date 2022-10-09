package com.isoft.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@MappedSuperclass
public class BaseInfo extends BaseEntity {

    @Size(min = 3, message = "name's length should be at least 3")
    protected String name;

    @NotBlank(message = "email should not be blank")
    @Email(message = "Please provide a valid email address")
    protected String email;

    @NotBlank(message = "mobile number should not be blank")
    @Pattern(regexp = "^$|09[0-9]{9}", message = "Mobile number is not valid (just Iran's format is valid)")
    protected String mobileNum;


}
