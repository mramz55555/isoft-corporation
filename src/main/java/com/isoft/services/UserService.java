package com.isoft.services;

import com.isoft.models.User;
import com.isoft.repositories.AddressRepository;
import com.isoft.repositories.RoleRepository;
import com.isoft.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.transaction.Transactional;

@Service
public class UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder encoder;

    public UserService(RoleRepository roleRepository, UserRepository userRepository, AddressRepository addressRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.encoder = encoder;
    }

    @Transactional
    public boolean saveUser(boolean emailChanged, User user, BindingResult result) {
        if (emailExists(emailChanged, user, result))
            return false;
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(roleRepository.findById(2).get());
        User savedUser = userRepository.save(user);
        return savedUser != null && savedUser.getId() > 0;
    }

    @Transactional
    public User save(boolean emailChanged, User user, BindingResult result) {
        if (emailExists(emailChanged, user, result))
            return null;
        return userRepository.save(user);
    }

    private boolean emailExists(boolean emailChanged, User user, BindingResult result) {
        if (emailChanged) {
            if (userRepository.existsByEmail(user.getEmail())) {
                result.addError(new ObjectError("User existence", "This email is not available"));
                return true;
            }
        }
        return false;
    }
}
