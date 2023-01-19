package com.example.actuatorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponse> productNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(new ExceptionResponse("Product Not Found"), HttpStatus.NOT_FOUND);
    }
}
