package com.shoyu.taskmanager.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoyu.taskmanager.entity.Task;
import com.shoyu.taskmanager.enums.TaskPriority;
import com.shoyu.taskmanager.enums.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByPriority(TaskPriority priority);
    List<Task> findByDueDateBefore(LocalDateTime date);
}
