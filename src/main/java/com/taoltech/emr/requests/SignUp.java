package com.taoltech.emr.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * SignUp
 */
@Data
public class SignUp {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Must be a valid email address")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    private String phone;
}