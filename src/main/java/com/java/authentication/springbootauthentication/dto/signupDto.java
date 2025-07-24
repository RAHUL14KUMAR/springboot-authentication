package com.java.authentication.springbootauthentication.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class signupDto {

    @Column(nullable=false,unique=true)
    private String email;
    private String password;
    private String name;
}
