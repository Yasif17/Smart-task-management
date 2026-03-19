package com.yasif.taskmanager.service.impl;

import com.yasif.taskmanager.dto.TaskRequest;
import com.yasif.taskmanager.dto.TaskResponse;
import com.yasif.taskmanager.entity.Task;
import com.yasif.taskmanager.entity.User;
import com.yasif.taskmanager.repository.TaskRepository;
import com.yasif.taskmanager.repository.UserRepository;
import com.yasif.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponse createTask(TaskRequest request, String creatorEmail) {

        User creator = userRepository.findByEmail(creatorEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User assignedUser = userRepository.findById(request.getAssignedToId())
                .orElseThrow(() -> new RuntimeException("Assigned user not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .createdBy(creator)
                .assignedTo(assignedUser)
                .build();

        Task saved = taskRepository.save(task);

        return mapToResponse(saved);
    }

    @Override
    public Page<TaskResponse> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return taskRepository.findAll(pageable).map(this::mapToResponse);
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return mapToResponse(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .assignedTo(task.getAssignedTo().getEmail())
                .createdBy(task.getCreatedBy().getEmail())
                .build();
    }
}