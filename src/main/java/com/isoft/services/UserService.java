package com.isoft.services;

import com.isoft.models.User;
import com.isoft.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean saveUser(User user) {
        User savedUser = repository.save(user);
        return savedUser != null && savedUser.getId() > 0;
    }
}
