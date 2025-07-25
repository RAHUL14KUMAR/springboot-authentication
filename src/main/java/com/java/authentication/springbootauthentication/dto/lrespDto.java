package com.java.authentication.springbootauthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class lrespDto {
    private String name;

    private String email;

    private String token;
}
