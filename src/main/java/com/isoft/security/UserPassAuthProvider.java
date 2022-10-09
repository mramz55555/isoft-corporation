package com.isoft.security;

import com.isoft.models.User;
import com.isoft.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserPassAuthProvider implements AuthenticationProvider {
    public static String currEmail;
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserPassAuthProvider(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = currEmail = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = repository.getByEmail(email);

        if (user != null && user.getId() > 0 && encoder.matches(password, user.getPassword()))
            return new UsernamePasswordAuthenticationToken(user.getName(), password, Collections.singleton(user.getRole()));
//            throw new BadCredentialsException("Credentials are not valid");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}
