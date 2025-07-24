package com.java.authentication.springbootauthentication.services.implementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.authentication.springbootauthentication.dto.loginDto;
import com.java.authentication.springbootauthentication.dto.signupDto;
import com.java.authentication.springbootauthentication.entity.UserEntity;
import com.java.authentication.springbootauthentication.repository.userRepo;
import com.java.authentication.springbootauthentication.services.userService;

@Service
public class userServImpl implements userService{
    private final userRepo  userRepo;
    private final PasswordEncoder passwordEncoder;

    // for login
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    public userServImpl(userRepo userrepo, PasswordEncoder p1){
        this.userRepo=userrepo;
        this.passwordEncoder=p1;
    }

    public signupDto signup(signupDto user){
        String emails=user.getEmail();
        UserEntity userToFind=userRepo.findByEmail(emails);
        UserEntity create=new UserEntity();

        System.out.println("while signup user find res"+ userToFind);

        if (userToFind!=null) {
            throw new BadCredentialsException("user is already exnterd in db with the email you entered");
        }else{
            create.setName(user.getName());
            create.setEmail(user.getEmail());
            create.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepo.save(create);
        }
        return new signupDto(create.getName(),create.getEmail(),create.getPassword());
    }

    public signupDto login(loginDto user){
        UserEntity findUser=userRepo.findByEmail(user.getEmail());

        System.out.println("from login user find result"+ findUser);

        return null;
    }
}
