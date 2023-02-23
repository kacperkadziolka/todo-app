package com.kkadziolka.todo_app.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id) {
        super("Could not find Todo task with id: " + id);
    }
}
