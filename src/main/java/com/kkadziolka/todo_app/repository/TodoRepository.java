package com.kkadziolka.todo_app.repository;

import com.kkadziolka.todo_app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByDescription(String description);

}
