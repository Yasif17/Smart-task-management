package com.yasif.taskmanager.controller;

import com.yasif.taskmanager.dto.RegisterRequest;
import com.yasif.taskmanager.dto.RegisterResponse;
import com.yasif.taskmanager.dto.TaskRequest;
import com.yasif.taskmanager.dto.TaskResponse;
import com.yasif.taskmanager.service.TaskService;
import com.yasif.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public TaskResponse createTask(@RequestBody TaskRequest request) {

        String email = ((com.yasif.taskmanager.entity.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getEmail();

        return taskService.createTask(request, email);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Page<TaskResponse> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return taskService.getAllTasks(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public TaskResponse getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }



}