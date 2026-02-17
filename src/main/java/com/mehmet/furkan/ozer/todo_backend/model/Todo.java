package com.mehmet.furkan.ozer.todo_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean completed = false;  // Klein geschrieben, nicht Boolean

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // NEU: Beziehung zu User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Constructors
    public Todo() {
        this.createdAt = LocalDateTime.now();
    }

    public Todo(String text) {
        this.text = text;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // NEU: User Getter/Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}