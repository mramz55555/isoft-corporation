package com.isoft.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isoft.validators.FieldsMatcher;
import com.isoft.validators.FieldsMatcherHandler;
import com.isoft.validators.PasswordValidator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@FieldsMatcher.List({
        @FieldsMatcher(fieldOne = FieldsMatcherHandler.PASSWORD, fieldTwo = "confirmPassword", message = "Passwords don't match"),
        @FieldsMatcher(fieldOne = FieldsMatcherHandler.EMAIL, fieldTwo = "confirmEmail", message = "emails don't match")
})
@JsonIgnoreProperties({"password", "confirmPassword", "confirmEmail"})
public class User extends BaseInfo {
    @PasswordValidator
    @Length(min = 6, message = "password's length must be at least 6")
    private String password;
    @Transient
    private String confirmPassword;
    @Transient
    private String confirmEmail;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
    private Address address;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = Business.class)
    @JoinColumn(name = "business_id", referencedColumnName = "id", nullable = true)
    private Business business;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "app_id", referencedColumnName = "id"))
    private Set<App> apps = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        return business != null ? business.equals(user.business) : user.business == null;
    }

    @Override
    public int hashCode() {
        int result = 31 * id + (role != null ? role.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (business != null ? business.hashCode() : 0);
        return result;
    }

}
