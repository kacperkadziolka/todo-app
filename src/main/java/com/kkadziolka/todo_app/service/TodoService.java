package com.kkadziolka.todo_app.service;

import com.kkadziolka.todo_app.exception.TodoAlreadyExistsException;
import com.kkadziolka.todo_app.exception.TodoNotFoundException;
import com.kkadziolka.todo_app.model.Todo;
import com.kkadziolka.todo_app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public void createTodo(Todo todo) {
        Optional<Todo> optionalTodo = todoRepository.findByDescription(todo.getDescription());

        if (optionalTodo.isPresent()) {
            throw new TodoAlreadyExistsException(todo.getDescription());
        }
        else {
            todo.setCreatedOn(LocalDate.now());
            todoRepository.save(todo);
        }

        /*
        todoRepository.findByDescription(todo.getDescription()).ifPresentOrElse(optionalTodo -> {
            throw new TodoAlreadyExistsException(todo.getDescription());
        }, () -> {
            todo.setCreatedOn(LocalDate.now());
            todoRepository.save(todo);
        });
        */
    }

    public void updateTodoStatus(Long id) {
        todoRepository.findById(id)
                .map(todo -> {
                    todo.setStatus(true);
                    return todoRepository.save(todo);
                })
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

}
