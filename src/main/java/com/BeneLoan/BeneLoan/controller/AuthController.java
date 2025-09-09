package com.BeneLoan.BeneLoan.controller;


import com.BeneLoan.BeneLoan.entity.AppUser;
import com.BeneLoan.BeneLoan.repository.UserRepo;
import com.BeneLoan.BeneLoan.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public static class LoginRequest{
        public String username;
        public String password;
    }

    public static class RegisterRequest {
        public String username;
        public String password;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username, request.password)
            );
            String token = jwtUtil.generateToken(request.username);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException authenticationException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "status" , HttpStatus.UNAUTHORIZED.value(),
                            "error" , HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                            "message", "Invalid username or password"
                    ));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (req.username == null || req.username.isBlank()
                || req.password == null || req.password.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("message", "username and password are required"));
        }
        if (userRepo.findByUsername(req.username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "username already exists"));
        }


        AppUser user = new AppUser();
        user.setUsername(req.username.trim());
        user.setPassword(passwordEncoder.encode(req.password)); // store bcrypt
        // Ensure role is set to satisfy NOT NULL constraint
        user.setRole("USER");
        userRepo.save(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }




}
