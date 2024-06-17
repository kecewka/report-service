package com.epam.reportservice.controller;

import com.epam.reportservice.feign.CustomErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomErrorDecoder.ForbiddenException.class)
    public ResponseEntity<String> handleForbiddenException(CustomErrorDecoder.ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access is forbidden");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleForbiddenException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
    }
}