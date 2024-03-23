package com.taoltech.emr.controllers.doctor;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Operation(
        tags = "Doctor",
        summary = "List all doctors",
        security = {@SecurityRequirement(name = "BasicAuth")}
    )
    @GetMapping("")
    public String list() {
        return new String("Welcome back!");
    }
}
