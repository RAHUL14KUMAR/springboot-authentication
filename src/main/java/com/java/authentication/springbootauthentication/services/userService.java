package com.java.authentication.springbootauthentication.services;

import com.java.authentication.springbootauthentication.dto.loginDto;
import com.java.authentication.springbootauthentication.dto.signupDto;

public interface userService {
    signupDto signup(signupDto user);
    signupDto login(loginDto user);
}
