package com.example.todoList;
//api layer
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(path = "api/v1/todos")
@CrossOrigin(origins = "http://localhost:3000/")
public class todoListController {

    @Autowired
    private TodoServices todoServices;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoServices.getAllTodos();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo todo = todoServices.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Todo>> addTodos(@RequestBody List<Todo> todos) {
        List<Todo> savedTodos = todoServices.addTodos(todos);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodos);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoServices.addTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") Long id,
                                           @RequestBody Todo todoDetails,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String description,
                                           @RequestParam(required = false) Boolean completed) {
        Todo updateTodo = todoServices.updateTodo(id, todoDetails, name, description, completed);
        return ResponseEntity.ok(updateTodo);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTodo(@PathVariable Long id) {
        todoServices.deleteTodo(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}