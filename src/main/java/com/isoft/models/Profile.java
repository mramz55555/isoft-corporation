package com.isoft.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Component
public class Profile {
    @NotBlank(message = "mobile number should not be blank")
    @Pattern(regexp = "^$|09[0-9]{9}", message = "Mobile number is not valid (just Iran's format is valid)")
    @NotBlank(message = "mobile number should not be blank")
    @Pattern(regexp = "^$|09[0-9]{9}", message = "Mobile number is not valid (just Iran's format is valid)")
    protected String mobileNum;
    @Size(min = 3, message = "name's length should be at least 3")
    private String name;
    @NotBlank(message = "email should not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;
    @Valid
    private Address address;
}
