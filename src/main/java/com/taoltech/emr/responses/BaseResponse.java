/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taoltech.emr.responses;

import lombok.Data;

/**
 *
 * @author taoltech
 */
@Data
public class BaseResponse {
    
    private boolean status;
    private String message;

    public BaseResponse() {
    }
     
    public BaseResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    } 
}