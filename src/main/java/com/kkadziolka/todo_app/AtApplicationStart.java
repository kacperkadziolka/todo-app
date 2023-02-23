package com.kkadziolka.todo_app;

import com.kkadziolka.todo_app.model.Todo;
import com.kkadziolka.todo_app.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AtApplicationStart {

    @Bean
    CommandLineRunner commandLineRunner(TodoRepository todoRepository) {
        return args -> {
            Todo homework = new Todo("Homework");
            Todo garbage = new Todo("Take out the rubbish");
            todoRepository.saveAll(List.of(homework, garbage));
        };
    }

}
