package com.taoltech.emr.requests;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePhysicianDTO {

    private String name;
    
    private String phone;
     
    @Email(message = "Must be a valid email address")
    private String email;
}
