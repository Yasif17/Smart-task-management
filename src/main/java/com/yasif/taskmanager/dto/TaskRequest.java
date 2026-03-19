package com.yasif.taskmanager.dto;

import com.yasif.taskmanager.entity.enums.Priority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private Priority priority;
    private LocalDateTime dueDate;
    private Long assignedToId;
}