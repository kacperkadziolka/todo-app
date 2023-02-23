package com.kkadziolka.todo_app.exception;

public class TodoAlreadyExistsException extends RuntimeException {

    public TodoAlreadyExistsException(String description) {
        super("There is already following todo task: " + description.toLowerCase() + " exists in the list");
    }
}
