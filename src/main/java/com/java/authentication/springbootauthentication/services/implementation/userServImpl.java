package com.java.authentication.springbootauthentication.services.implementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.authentication.springbootauthentication.dto.loginDto;
import com.java.authentication.springbootauthentication.dto.lrespDto;
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


    public userServImpl(userRepo userrepo, PasswordEncoder p1, AuthenticationManager am, JWTService jw){
        this.userRepo=userrepo;
        this.passwordEncoder=p1;
        this.authenticationManager=am;
        this.jwtService=jw;
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

    public lrespDto login(loginDto user){
    System.out.println("Step 1: Finding user");
    UserEntity findUser = userRepo.findByEmail(user.getEmail());

    System.out.println("Step 2: Authenticating");
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
    );

    System.out.println("Step 3: Getting principal");
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    UserEntity user1 = userDetails.getUserEntity();

    System.out.println("Step 4: Creating token");
    String token = jwtService.createToken(user1);

    System.out.println("Step 5: Returning response");
    return new lrespDto(findUser.getName(), findUser.getEmail(), token);
}
}
