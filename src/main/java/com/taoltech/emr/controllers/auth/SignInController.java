package com.taoltech.emr.controllers.auth;

import com.taoltech.emr.requests.SignIn;
import com.taoltech.emr.responses.OkResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("sign-in")
public class SignInController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Operation(
            tags = "Auth",
            summary = "Authenticate user")
    @PostMapping("")
    public ResponseEntity<?> login(@Valid @RequestBody SignIn dto) {
        Authentication authenticationRequest =
			UsernamePasswordAuthenticationToken.unauthenticated(dto.getUsername(), dto.getPassword());

		Authentication authenticationResponse =
			this.authenticationManager.authenticate(authenticationRequest);

        UserDetails userDetails = (UserDetails) authenticationResponse.getPrincipal();
        Map<String, Object> data = new HashMap<>();
        data.put("user", userDetails);

        //
        return ResponseEntity.ok().body(new OkResponse("Login successfully", data));
    }
 
}
