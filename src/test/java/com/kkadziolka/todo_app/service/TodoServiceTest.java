package com.kkadziolka.todo_app.service;

import com.kkadziolka.todo_app.exception.TodoAlreadyExistsException;
import com.kkadziolka.todo_app.exception.TodoNotFoundException;
import com.kkadziolka.todo_app.model.Todo;
import com.kkadziolka.todo_app.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService underTestTodoService;

    @Test
    void getAllTodoShouldReturnEmptyList() {
        // when
        List<Todo> allTodo = underTestTodoService.getAllTodo();

        // then
        assertEquals(allTodo.size(), 0);
        verify(todoRepository).findAll();
    }

    @Test
    void getAllTodoShouldReturnListOfTwo() {
        // when
        when(todoRepository.findAll()).thenReturn(List.of(new Todo(), new Todo()));
        List<Todo> allTodo = underTestTodoService.getAllTodo();

        // then
        assertEquals(allTodo.size(), 2);
        verify(todoRepository).findAll();
    }

    @Test
    void itShouldDeleteTodo() {
        // given
        Todo todo = new Todo("Homework");
        todo.setId(1L);
        given(todoRepository.findById(anyLong())).willReturn(Optional.of(todo));

        // when
        underTestTodoService.deleteTodo(todo.getId());

        // then
        ArgumentCaptor<Long> todoArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(todoRepository).deleteById(todoArgumentCaptor.capture());
        Long deletedTodoId = todoArgumentCaptor.getValue();
        assertEquals(1L, deletedTodoId);
    }

    @Test
    void itShouldThrowExceptionWhenDeletingNotExistsTodo() {
        // given
        given(todoRepository.findById(anyLong())).willReturn(Optional.empty());

        // when
        // then
        Throwable exception = assertThrows(TodoNotFoundException.class,
                () -> underTestTodoService.deleteTodo(1L));

        assertEquals("Could not find Todo task with id: " + 1L, exception.getMessage());

        verify(todoRepository, never()).deleteById(any());
    }

    @Test
    void itShouldCreateTodo() {
        // given
        Todo todo = new Todo("Homework");

        // when
        underTestTodoService.createTodo(todo);

        // then
        ArgumentCaptor<Todo> todoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepository).save(todoArgumentCaptor.capture());
        Todo capturedTodo = todoArgumentCaptor.getValue();
        assertEquals(capturedTodo, todo);
    }

    @Test
    void itShouldThrowExceptionWhenCreationExistingTodoTask() {
        // given
        Todo todo = new Todo("Homework");
        given(todoRepository.findByDescription(todo.getDescription())).willReturn(Optional.of(todo));

        // when
        // then
        Throwable exception = assertThrows(TodoAlreadyExistsException.class,
                () -> underTestTodoService.createTodo(todo));

        assertEquals("There is already following todo task: " + todo.getDescription().toLowerCase() + " exists in the list",
                exception.getMessage());

        verify(todoRepository, never()).save(any());
    }

    @Test
    void itShouldUpdateTodoStatus() {
        // given
        Todo todo = new Todo("Homework");
        todo.setId(1L);
        given(todoRepository.findById(anyLong())).willReturn(Optional.of(todo));

        // when
        underTestTodoService.updateTodoStatus(todo.getId());

        // then
        ArgumentCaptor<Todo> todoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepository).save(todoArgumentCaptor.capture());
        Todo capturedTodo = todoArgumentCaptor.getValue();
        assertTrue(capturedTodo.isStatus());
    }

    @Test
    void itShouldThrowExceptionWhenUpdateNotExistingTodoTask() {
        // given
        given(todoRepository.findById(anyLong())).willReturn(Optional.empty());

        // when
        // then
        Throwable exception = assertThrows(TodoNotFoundException.class,
                () -> underTestTodoService.updateTodoStatus(1L));

        assertEquals("Could not find Todo task with id: " + 1L, exception.getMessage());

        verify(todoRepository, never()).save(any());
    }


}