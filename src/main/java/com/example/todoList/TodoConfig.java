package com.example.todoList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TodoConfig {

    @Bean
    CommandLineRunner commandLineRunner(TodoListRepository repository) {
        return args -> {
            Todo one = new Todo(
                    "Mary",
                    "My todo list of this week",
                    true);

            Todo two = new Todo(
                    "Jane",
                    "My todo list of last week",
                    false
            );

            repository.saveAll(
                    List.of(one, two)
            );
        };
    }
}
