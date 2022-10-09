package com.isoft.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@Entity
public class Address extends BaseEntity {
    private String addressOne;
    private String addressTwo;
    @NotBlank(message = "City should not be blank")
    private String city;
    @NotBlank(message = "State should not be blank")
    private String state;
    @NotBlank(message = "zip code should not be blank")
    @Pattern(regexp = "^\\d{5}$", message = "Zip code must be a 5 digits number")
    private String zipCode;
}
