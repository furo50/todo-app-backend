package com.mehmet.furkan.ozer.todo_backend.controller;

import com.mehmet.furkan.ozer.todo_backend.dto.AuthResponse;
import com.mehmet.furkan.ozer.todo_backend.dto.LoginRequest;
import com.mehmet.furkan.ozer.todo_backend.dto.SignupRequest;
import com.mehmet.furkan.ozer.todo_backend.model.User;
import com.mehmet.furkan.ozer.todo_backend.repository.UserRepository;
import com.mehmet.furkan.ozer.todo_backend.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        // Check if username already exists
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username ist bereits vergeben!");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email ist bereits vergeben!");
        }

        // Create new user
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("User erfolgreich registriert!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new AuthResponse(jwt, loginRequest.getUsername()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ungültige Anmeldedaten!");
        }
    }
}