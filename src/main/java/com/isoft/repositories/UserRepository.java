package com.isoft.repositories;

import com.isoft.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsByEmail(String email);
    User getByEmail(String email);

    User getByRoleName(String role);
}
