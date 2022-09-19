package com.example.march2022spring.controllers;

import com.example.march2022spring.models.dto.NameSizeErrorDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public NameSizeErrorDTO sizeNameException(MethodArgumentNotValidException e) {
        System.out.println(e.getFieldError().getDefaultMessage());
        return new NameSizeErrorDTO(400, e.getFieldError().getDefaultMessage(), e.getFieldError().getField());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String UserWithThisIdDoesnotExist(EmptyResultDataAccessException e) {
        return e.getMessage();
    }

}
