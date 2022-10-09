package com.isoft.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Business extends BaseEntity {

    @Size(min = 3, message = "name's length should be at least 3")
    private String name;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.PERSIST)
    private Set<User> customers = new HashSet<>();
}
