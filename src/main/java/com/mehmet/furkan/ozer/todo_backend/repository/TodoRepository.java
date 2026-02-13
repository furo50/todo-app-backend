package com.mehmet.furkan.ozer.todo_backend.repository;

import com.mehmet.furkan.ozer.todo_backend.model.Todo;
import com.mehmet.furkan.ozer.todo_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUser(User user);

    List<Todo> findByUserId(Long userId);

    Optional<Todo> findByIdAndUser(Long id, User user);
}