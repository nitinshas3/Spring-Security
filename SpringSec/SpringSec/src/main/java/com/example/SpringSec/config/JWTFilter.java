package com.example.SpringSec.config;

//THIS IS USED FOR REQUEST AFTER LOGGING IN , THIS IS FOR VALIDATING THE JWT TOKEN , JWTSERVICE IS FOR GENERATING TOKEN


//USUALLY THIS HAS TO BE IN A SEPARATE FOLDER CALLED FILTERS
import com.example.SpringSec.service.JWTservice;
import com.example.SpringSec.service.MyUserdetailservice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.core.ApplicationContext;
import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import java.io.IOException;

//this is an abstract class which we are extending so we have to impelement a method from it,

@Component
public class    JWTFilter extends OncePerRequestFilter {

    @Autowired
    MyUserdetailservice myUserdetailservice;



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

        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = myUserdetailservice.loadUserByUsername(username);

            if(jwtservice.validatetoken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

    filterChain.doFilter(request,response);
    }
}
