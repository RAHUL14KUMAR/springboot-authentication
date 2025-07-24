package com.java.authentication.springbootauthentication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.authentication.springbootauthentication.dto.loginDto;
import com.java.authentication.springbootauthentication.dto.signupDto;
import com.java.authentication.springbootauthentication.services.userService;


@RestController
public class userController {
    private final userService UserServ;

    public userController(userService u1){
        this.UserServ=u1;
    }

    @PostMapping("/signup")
    public signupDto getSignUp(@RequestBody signupDto entity) {
        //TODO: process POST request
        
        signupDto ans= UserServ.signup(entity);
        return ans;
    }

    @PostMapping("/login")
    public signupDto getLogin(@RequestBody loginDto entity) {
        //TODO: process POST request
        
        return UserServ.login(entity);
    }
    
    
}
