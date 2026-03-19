package com.yasif.taskmanager.service;

import com.yasif.taskmanager.dto.TaskRequest;
import com.yasif.taskmanager.dto.TaskResponse;
import org.springframework.data.domain.Page;

public interface TaskService {

    TaskResponse createTask(TaskRequest request, String creatorEmail);

    Page<TaskResponse> getAllTasks(int page, int size);

    TaskResponse getTaskById(Long id);

    void deleteTask(Long id);
}