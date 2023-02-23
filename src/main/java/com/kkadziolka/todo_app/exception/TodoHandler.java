package com.kkadziolka.todo_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class TodoHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<Object> todoNotFoundHandler(TodoNotFoundException ex) {
        Exception todoException = new Exception(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("UTC+1")));
        return new ResponseEntity<>(todoException, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TodoAlreadyExistsException.class)
    public ResponseEntity<Object> todoAlreadyExistsHandler(TodoAlreadyExistsException ex) {
        Exception todoException = new Exception(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("UTC+1")));
        return new ResponseEntity<>(todoException, HttpStatus.BAD_REQUEST);
    }

}
