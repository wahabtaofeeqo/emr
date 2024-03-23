package com.taoltech.emr.exceptions;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taoltech.emr.responses.ErrResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
     
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrResponse res = new ErrResponse("Unauthorized");

        OutputStream os = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(os, res);

        //
        os.flush();
    }
}
