/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taoltech.emr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author taoltech
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            
            // public routes
            auth.requestMatchers(
                    "/", "index",
                    "sign-in", "sign-up").permitAll();
            
            // only Doc routes
        });
        
        return http.build();
    }
    
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails userDetails = User
                .withUsername("taofeeqo")
                .roles("ROLE_USER")
                .password("password").build();
        
        return new InMemoryUserDetailsManager(userDetails);
    }
}
