package com.example.beck.web.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.beck.exception.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            EntityNotFoundException.class,
            InvalidPropertyException.class,
            SuchEntityExistsException.class,
            UndeletebleEntityException.class
    })
    public ResponseEntity handle(BaseException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}