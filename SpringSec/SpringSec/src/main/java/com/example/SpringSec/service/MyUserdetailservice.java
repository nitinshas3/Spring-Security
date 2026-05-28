package com.example.SpringSec.service;

import com.example.SpringSec.model.UserPrincipal;
import com.example.SpringSec.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserdetailservice implements UserDetailsService {

    @Autowired
    private Userrepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userrepo.findByUsername(username);

        if(user == null){
            System.out.println("user not found");
            throw  new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}
