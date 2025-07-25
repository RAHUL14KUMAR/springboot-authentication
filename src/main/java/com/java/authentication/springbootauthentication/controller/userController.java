package com.java.authentication.springbootauthentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.authentication.springbootauthentication.dto.loginDto;
import com.java.authentication.springbootauthentication.dto.lrespDto;
import com.java.authentication.springbootauthentication.dto.signupDto;
import com.java.authentication.springbootauthentication.services.userService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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
    public ResponseEntity<lrespDto> logIn(
            @RequestBody loginDto loginDto,
            HttpServletRequest request,
            HttpServletResponse response){
                lrespDto token = UserServ.login(loginDto);
                System.out.println(token);

                Cookie cookie = new Cookie("token", token.getToken());
                cookie.setHttpOnly(true); // it makes sure that this cookie cannot be accessed by any other it can only be fund with the help of your Http methods
        // no other attacker can be access our website
        // Prevents JavaScript access to the cookie
                response.addCookie(cookie); // Http only cookies can be passed from backend to frontend only


                return ResponseEntity.ok(token);
    }
    
    
}
