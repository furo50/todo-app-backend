package com.mehmet.furkan.ozer.todo_backend.controller;

import com.mehmet.furkan.ozer.todo_backend.model.Todo;
import com.mehmet.furkan.ozer.todo_backend.model.User;
import com.mehmet.furkan.ozer.todo_backend.repository.TodoRepository;
import com.mehmet.furkan.ozer.todo_backend.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Hilfsmethode: Aktuellen User holen
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userDetailsService.getUserByUsername(username);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        User currentUser = getCurrentUser();
        return todoRepository.findByUser(currentUser);
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        User currentUser = getCurrentUser();
        todo.setUser(currentUser);
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        User currentUser = getCurrentUser();

        return todoRepository.findByIdAndUser(id, currentUser)
                .map(todo -> {
                    todo.setText(todoDetails.getText());
                    todo.setCompleted(todoDetails.getCompleted());
                    return ResponseEntity.ok(todoRepository.save(todo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        User currentUser = getCurrentUser();

        return todoRepository.findByIdAndUser(id, currentUser)
                .map(todo -> {
                    todoRepository.delete(todo);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}