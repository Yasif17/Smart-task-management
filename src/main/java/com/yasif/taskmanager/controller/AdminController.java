package com.yasif.taskmanager.controller;

import com.yasif.taskmanager.dto.RegisterRequest;
import com.yasif.taskmanager.dto.RegisterResponse;
import com.yasif.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;


    @PostMapping("/create-user")
    @PreAuthorize("hasRole('ADMIN')")
    public RegisterResponse createUser(@RequestBody RegisterRequest request) {
        return userService.createUserByAdmin(request);
    }

}
