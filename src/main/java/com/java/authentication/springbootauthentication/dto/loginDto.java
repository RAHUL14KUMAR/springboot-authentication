package com.java.authentication.springbootauthentication.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class loginDto {
    private String password;

    @Column(nullable=false,unique=true)
    private String email;
}
