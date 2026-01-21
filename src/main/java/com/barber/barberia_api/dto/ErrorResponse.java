package com.barber.barberia_api.dto;

import java.time.LocalDateTime;

public class ErrorResponse {


    public LocalDateTime timestamp;
    public int status;
    public String error; //tipo de error
    public String message; //mensaje
    public String path; //ruta que genero el error
    
     //constructors
    public ErrorResponse() {
    }

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public static ErrorResponse of(int status, String error, String message, String path) {
        ErrorResponse resp = new ErrorResponse();
        resp.timestamp = LocalDateTime.now();
        resp.status = status;
        resp.error = error;
        resp.message = message;
        resp.path = path;
        return resp;
    }

    


    
}
