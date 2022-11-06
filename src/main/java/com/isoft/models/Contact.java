package com.isoft.models;

import com.isoft.services.ContactService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Table(name = "contact_message")
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "Contact.findAllByStatusEquals, findAllByNameEquals ", query = "select * from `contact_message` `c`  where `c`.`status`= :status",
                resultClass = Contact.class),
        @NamedNativeQuery(name = "Contact.deleteById", query = "delete from `contact_message` where `status` = ?1 and `id` = ?2")
})
public class Contact extends BaseInfo {
    @NotBlank(message = "subject should not be blank")
    @Size(max = 100, message = " subject must have at last 100 character")
    private String subject;
    //    @NotBlank(message = "message should not be blank")
    @Size(min = 20, max = 5000, message = " message must have at least 20 character")
    private String message;
    private String status = ContactService.STATUS_OPEN;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
