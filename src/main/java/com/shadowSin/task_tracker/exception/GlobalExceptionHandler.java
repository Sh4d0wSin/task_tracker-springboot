package com.shadowSin.task_tracker.exception;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException exc) {

        String msg = exc.getMessage();




        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    
    
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        List<FieldError> fieldErrors = exc.getBindingResult().getFieldErrors();
        Map<String, String> mapErrors = fieldErrors.stream().collect(Collectors.toMap( FieldError::getField, FieldError::getDefaultMessage));


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapErrors);

       


    }


}