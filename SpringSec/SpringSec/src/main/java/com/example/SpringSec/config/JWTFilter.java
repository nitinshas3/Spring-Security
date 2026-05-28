package com.example.SpringSec.config;

import com.example.SpringSec.service.JWTservice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//this is an abstract class which we are extending so we have to impelement a method from it
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTservice jwtservice;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //so like the token will be in string like ( beared  and then token )so we need to cut of that bearer word then extract the token and validate it


        String authheader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authheader!=null && authheader.startsWith("Bearer")){
            token = authheader.substring(7);
            username = jwtservice.extractusername(token);
        }

       // if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null)


    }
}
