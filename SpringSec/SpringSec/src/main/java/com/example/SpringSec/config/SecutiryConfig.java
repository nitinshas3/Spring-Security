package com.example.SpringSec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {

    @Autowired
    JWTFilter jwtfilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer -> customizer.disable()); // this will disable the csrf

        http.authorizeHttpRequests(request -> request
                .requestMatchers("register","login")
                .permitAll()
                .anyRequest().authenticated()); // all request must be authenticated

        http.formLogin(Customizer.withDefaults());//is what enables form‑based login in Spring Security.
        /* What it does Spring Security auto‑generates a default login page (HTML form) at /login.*/

        http.httpBasic(Customizer.withDefaults());
        // Enables HTTP Basic Authentication
        // Credentials sent via Authorization header
        // form is used to send from browser , this is for testing like send through postman etc

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //makes session stateless like for every request there has to be new authentication



        http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);//this comes under jwt

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails u1 = User
                .withDefaultPasswordEncoder()
                .username("nitin")
                .password("nitin123")
                .roles("USER")
                .build();

        UserDetails u2 = User
                .withDefaultPasswordEncoder()
                .username("shastri")
                .password("shastri123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(u1,u2);
    }

    @Autowired
    UserDetailsService userdetailservice; // if not implemented the interface it will give default one

    @Bean // in spring 6 this has to be done in spring 5 this was not required only implementing was enough , authetication provider automatically invoked the implemented class

    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        provider.setUserDetailsService(userdetailservice);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
