/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taoltech.emr.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author taoltech
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ErrResponse extends BaseResponse {
    
    private Object error;
    
    public ErrResponse(String message) {
        super(false, message);
    }
    
    public ErrResponse(String message, Object error) {
        super(false, message);
        this.error = error;
    }
      
    public ErrResponse(boolean status, String message, Object error) {
        super(status, message);
        this.error = error;
    }
}
