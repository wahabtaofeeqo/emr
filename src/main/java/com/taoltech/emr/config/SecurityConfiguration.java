/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taoltech.emr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.taoltech.emr.exceptions.CustomEntryPoint;
import com.taoltech.emr.security.TokenAuthentificationFilter;

/**
 *
 * @author taoltech
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Autowired
    CustomEntryPoint authEntryPoint;

    @Autowired
    TokenAuthentificationFilter tokenAuthentificationFilter;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // CORS
        http.cors(c -> c.disable());
        http.csrf(c -> c.disable());
        
        // Access
        http.authorizeHttpRequests(auth -> {

            // public routes
            auth.requestMatchers(
                    "/", "index", "api-docs/**",
                    "swagger-ui/**", "sign-in", "sign-up").permitAll()
                    .anyRequest().authenticated();

            // only Doc routes
        });
        
        http.httpBasic(basic -> basic.authenticationEntryPoint(authEntryPoint));
        
        // http.exceptionHandling(auth -> auth.authenticationEntryPoint(authEntryPoint));
        // http.addFilterBefore(tokenAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);

        //
        return http.build();
    }

   @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
            .username("user")
            .password(encoder().encode("Password"))
            .roles("USER")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(encoder().encode("Password"))
            .roles("USER", "ADMIN")
            .build();

        //
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}
}
