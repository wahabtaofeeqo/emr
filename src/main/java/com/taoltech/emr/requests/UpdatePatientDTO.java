package com.taoltech.emr.requests;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePatientDTO {

    private String name;
    
    @Email(message = "Must be a valid email address")
    private String email;
    
    private String phone;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dob;

    private String gender;
    
    private String address;
}
