package com.example.todoList;
//service layer -> business logic
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoServices {

    private final TodoListRepository todoListRepository;

    @Autowired
    public TodoServices(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<Todo> getAllTodos() {
        return todoListRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return  todoListRepository.findTodoById(id).orElseThrow(
                () -> new IllegalStateException("Todo list doesn't find this id: " + id));
    }

    public Todo addTodo(Todo todo) {
        return todoListRepository.save(todo);
    }

    public List<Todo> addTodos(List<Todo> todos) {
        return todoListRepository.saveAll(todos);
    }

    @Transactional
    public Todo updateTodo(Long id, Todo todoDetails, String name, String description, Boolean completed) {
        Todo todo = todoListRepository.findById(id).orElseThrow(
                () -> new ResolutionException("Todo list does not exist with this id: " + id));

        if (name != null && name.length() > 0 && !Objects.equals(todo.getName(), name)) {
            Optional<Todo> todoOptional = todoListRepository.findTodoByName(name);
            if (todoOptional.isPresent()) {
                throw new IllegalStateException("Name taken");
            }
            todo.setName(name);
        }
        if (todoDetails != null) {
            todo.setDescription(todoDetails.getDescription());
            todo.setCompleted(todoDetails.isCompleted());
        }
        else if (completed != null) {
            todo.setCompleted(completed);
        }
        else if (description != null) {
            todo.setDescription(description);
        }
        return todoListRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        Todo todo = todoListRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Todo not found with that id: " + id));

        todoListRepository.delete(todo);
    }
}
