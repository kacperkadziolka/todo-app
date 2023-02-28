package com.kkadziolka.todo_app.repository;

import com.kkadziolka.todo_app.model.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository underTestTodoRepository;

    @AfterEach
    void tearDown() {
        underTestTodoRepository.deleteAll();
    }

    @Test
    void itShouldFindTodoTaskByDescription() {
        // given
        Todo todo = new Todo("Homework");
        underTestTodoRepository.save(todo);

        // when
        Optional<Todo> expected = underTestTodoRepository.findByDescription("Homework");

        // then
        assertTrue(expected.isPresent());
        assertEquals(expected.get(), todo);
    }

    @Test
    void itShouldNotFindTodoTaskByDescription() {
        // given
        Todo todo = new Todo("Rubbish");
        underTestTodoRepository.save(todo);

        // when
        Optional<Todo> expected = underTestTodoRepository.findByDescription("Homework");

        // then
        assertTrue(expected.isEmpty());
    }

}