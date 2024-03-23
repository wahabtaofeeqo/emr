package com.taoltech.emr.controllers.auth;

import com.taoltech.emr.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taoltech.emr.requests.SignUp;
import com.taoltech.emr.responses.BaseResponse;
import com.taoltech.emr.responses.OkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("sign-up")
public class SignUpController {
    
    @Operation(
            tags = "User",
            summary = "Create a new User account")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Operation succeeded", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = User.class)) }),
        
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
          content = @Content), 
        
        @ApiResponse(responseCode = "404", description = "Book not found", 
            content = @Content) 
    })
    @PostMapping("")
    public ResponseEntity<BaseResponse> createAccount(@Valid @RequestBody SignUp dto) {
      // float error = 100 / 0;
      BaseResponse res = new OkResponse("Account created", dto);
      return ResponseEntity.ok().body(res);
    }
   
}
