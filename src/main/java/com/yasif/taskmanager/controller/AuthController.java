package com.yasif.taskmanager.controller;

import com.yasif.taskmanager.dto.LoginRequest;
import com.yasif.taskmanager.dto.RegisterRequest;
import com.yasif.taskmanager.dto.RegisterResponse;
import com.yasif.taskmanager.security.TokenBlacklist;
import com.yasif.taskmanager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final TokenBlacklist tokenBlacklist;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest  registerRequest) {
        return userService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklist.add(token);
        }

        return "Logged out successfully";
    }

}
