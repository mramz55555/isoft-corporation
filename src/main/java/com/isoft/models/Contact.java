package com.isoft.models;

import com.isoft.services.ContactService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@Table(name = "contact_message")
@Entity
public class Contact extends BaseInfo {

    @Size(min = 10, max = 100, message = " subject must have at least 10 character")
    private String subject;
    //    @NotBlank(message = "message should not be blank")
    @Size(min = 20, max = 5000, message = " message must have at least 20 character")
    private String message;
    private String status = ContactService.STATUS_OPEN;
}
