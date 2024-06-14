package com.example.todoList;
//repository interface -> DAO layer -> spring data jpa
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoListRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findTodoById(Long id);
    Optional<Todo> findTodoByName(String name);
}
