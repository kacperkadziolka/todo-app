package com.kkadziolka.todo_app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class Exception {

    private String message;

    private HttpStatus httpStatus;

    private ZonedDateTime zonedDateTime;
}
