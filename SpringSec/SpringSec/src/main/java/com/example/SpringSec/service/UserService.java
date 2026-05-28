package com.example.SpringSec.service;

import com.example.SpringSec.model.Users;
import com.example.SpringSec.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    Userrepo userrepo;

    @Autowired
    JWTservice jwTservice;

    @Autowired
    AuthenticationManager authmanager;

    public Users register(Users users){
        return userrepo.save(users);

    }

    public String login(Users users) {
        Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));

        if(authentication.isAuthenticated()) {
            jwTservice.generatetoken(users.getUsername());
            return "success";
        }
        return "failed";
    }
}
