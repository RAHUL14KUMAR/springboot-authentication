package com.java.authentication.springbootauthentication.services.implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.authentication.springbootauthentication.entity.UserEntity;
import com.java.authentication.springbootauthentication.repository.userRepo;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final userRepo userRepo;

    public CustomUserDetailsService(userRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Ensure findByEmail returns Optional<UserEntity> or handle null if it doesn't
        UserEntity userEntity = userRepo.findByEmail(email);
        if (userEntity == null) {
        throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(userEntity);

        // CRUCIAL: userEntity.getPassword() MUST return the HASHED password.
        // If it's returning the plain text password, authentication will fail (but not StackOverflow).
        // return new org.springframework.security.core.userdetails.User(
        //         userEntity.getEmail(),
        //         userEntity.getPassword(), // This is the hashed password from the DB
        //         new ArrayList<>() // Authorities/Roles, adjust as needed
        // );
    }
}
