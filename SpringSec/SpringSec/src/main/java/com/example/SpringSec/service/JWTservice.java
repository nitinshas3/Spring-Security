package com.example.SpringSec.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTservice {

    String secretkey = "";

    public JWTservice(){
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key =  keyGenerator.generateKey();
            secretkey = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generatetoken(String username) {

        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*30))
                .and()
                .signWith(getkey())
                .compact();



    }

    private Key getkey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor((keyBytes));
    }

    public String extractusername(String token) {
        return "";
    }
}
