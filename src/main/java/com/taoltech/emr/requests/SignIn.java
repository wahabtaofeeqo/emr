package com.taoltech.emr.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignIn {
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Phone number is required")
    private String password;
}
