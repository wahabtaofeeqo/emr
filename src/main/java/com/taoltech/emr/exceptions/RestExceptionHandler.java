/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taoltech.emr.exceptions;

import com.taoltech.emr.responses.ErrResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author taoltech
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        // Errors
        List<String> errors = ex.getBindingResult()
            .getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
    
        ErrResponse response = new ErrResponse("Validation Error", errors);
        
        //
        return ResponseEntity.status(status).body(response);
    }
    
    @ExceptionHandler({AccessDeniedException.class })
    public ResponseEntity<?> handleAccessDeniedException(Exception ex, WebRequest request) {
        ErrResponse response = new ErrResponse("Access denied");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalHander(Exception ex, WebRequest request) {
        ErrResponse response = new ErrResponse(ex.getMessage(), ex.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
    }
}
