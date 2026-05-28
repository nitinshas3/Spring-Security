package com.example.SpringSec.service;

import com.example.SpringSec.model.Users;
import com.example.SpringSec.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    Userrepo userrepo;

    public Users register(Users users){
        return userrepo.save(users);

    }
}
