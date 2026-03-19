package com.yasif.taskmanager.repository;

import com.yasif.taskmanager.entity.Task;
import com.yasif.taskmanager.entity.enums.Priority;
import com.yasif.taskmanager.entity.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    Page<Task> findByPriority(Priority priority, Pageable pageable);

    Page<Task> findByAssignedToId(Long userId, Pageable pageable);

}
