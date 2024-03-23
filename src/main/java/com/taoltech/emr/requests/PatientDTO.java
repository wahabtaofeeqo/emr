package com.taoltech.emr.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.taoltech.emr.models.User;

@Data
public class PatientDTO {
        
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Must be a valid email address")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    private String phone;

    @Past(message = "DOB must be in the past")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dob;

    @NotBlank(message = "Email is required")
    private String gender;
    
    @NotBlank(message = "Phone number is required")
    private String address;

    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(phone);
        
        //
        return user;
    }
}
