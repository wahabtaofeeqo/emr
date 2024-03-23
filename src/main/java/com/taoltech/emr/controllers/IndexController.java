/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taoltech.emr.controllers;

import com.taoltech.emr.responses.BaseResponse;
import com.taoltech.emr.responses.OkResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taoltech
 */
@RestController
public class IndexController {
    
    @Operation(
        tags = "A",
        summary = "System Heath check")
    @GetMapping("")
    private ResponseEntity<?> index() {
        BaseResponse res = new OkResponse("Hello World! Let's get started", null);
        return ResponseEntity.ok().body(res);
    }
}
