package com.java.authentication.springbootauthentication.services;

import com.java.authentication.springbootauthentication.dto.loginDto;
import com.java.authentication.springbootauthentication.dto.lrespDto;
import com.java.authentication.springbootauthentication.dto.signupDto;

public interface userService {
    signupDto signup(signupDto user);
    lrespDto login(loginDto user);
}
