package com.java.authentication.springbootauthentication.services.implementation;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.java.authentication.springbootauthentication.entity.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    private static final String jwtSecreteKey = "ddgdbydjsmsjjsmhdgdndjsksjbdddjdkddk";
    
    //generate a secreate key method
    private SecretKey generateSecreteKey(){
        return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));
    }

    //create token method
    public String createToken(UserEntity user) {
        return Jwts.builder()         //to build
                .subject(user.getId().toString())  // here we get the id which is in UserEntity class and Id is Long so we use toString()
                .claim("email",user.getEmail())   //in claim we have to add user details
                // .claim("roles", Set.of("ADMIN","USER"))  // if you define any role then you can use it like this
                // .issuedAt(new Date())       // every token has a issueAt date
                .expiration(new Date(System.currentTimeMillis()+1000*60))   // also has an expiration time, such as expiring 1 minute after creation
                .signWith(generateSecreteKey())     //signwith the key which is generated
                .compact();      // all of above need to compact

    }
}
