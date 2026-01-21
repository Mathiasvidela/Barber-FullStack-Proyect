package com.barber.barberia_api.exception;

import com.barber.barberia_api.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    //400 - errores de validacion o reglas de negocio -------

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request
    ){

        ErrorResponse body = ErrorResponse.of(
                400,
                "VALIDATION_ERROR",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    


    //500 - cualquier excepcion no manejada ---------------

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ){

        ErrorResponse body = ErrorResponse.of(
                500,
                "INTERNAL_SERVER_ERROR",
                "Ocurrio un error inesperado",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }



    //404 - recurso no encontrado ---------------

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(
        NotFoundException ex,
        HttpServletRequest request
    ){
        return ErrorResponse.of(
            404,
            "NOT_FOUND",
            ex.getMessage(),
            request.getRequestURI()
        );
    }


    //409 - conflicto de datos ---------------

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflict(
        ConflictException ex,
        HttpServletRequest request
    ){
        return ErrorResponse.of(
            409,
            "CONFLICT",
            ex.getMessage(),
            request.getRequestURI()
        );
    } 



}
