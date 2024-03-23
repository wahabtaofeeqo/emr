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
public class OkResponse extends BaseResponse {
    
    private Object data;
    
    public OkResponse(String message, Object data) {
        super(true, message);
        this.data = data;
    }
    
    public OkResponse(boolean status, String message, Object data) {
        super(status, message);
        this.data = data;
    }
}