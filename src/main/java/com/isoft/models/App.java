package com.isoft.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class App extends BaseEntity {

    @NotBlank(message = "name must not be blank")
    private String name;
    @NotBlank(message = "cost must not be blank")
    private String cost;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "apps")
    private Set<User> customers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof App)) return false;

        App app = (App) o;

        if (id != app.id) return false;
        return name.equals(app.name);
    }

    @Override
    public int hashCode() {
        return 31 * id + name.hashCode();
    }
}
