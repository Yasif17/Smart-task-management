package com.yasif.taskmanager.dto;

import com.yasif.taskmanager.entity.enums.Priority;
import com.yasif.taskmanager.entity.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDateTime dueDate;
    private String assignedTo;
    private String createdBy;
}