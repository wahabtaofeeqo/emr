package com.taoltech.emr.requests;

import com.taoltech.emr.models.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhysicianDTO {

    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Must be a valid email address")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    private String phone;
    
    @NotBlank(message = "Type is required")
    private String type;
     
    @NotBlank(message = "Password is required")
    private String password;

    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        
        //
        return user;
    }
}
