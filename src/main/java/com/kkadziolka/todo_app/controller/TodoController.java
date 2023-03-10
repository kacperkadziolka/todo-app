package com.kkadziolka.todo_app.controller;

import com.kkadziolka.todo_app.model.Todo;
import com.kkadziolka.todo_app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/alltodo")
    public List<Todo> getAllTodo() {
        return todoService.getAllTodo();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
    }

    @PostMapping("/create")
    public void createTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);
    }

    @PutMapping("/updatestatus/{id}")
    public void updateTodoStatus(@PathVariable("id") Long id) {
        todoService.updateTodoStatus(id);
    }

}
