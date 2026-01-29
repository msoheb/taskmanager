package com.shoyu.taskmanager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shoyu.taskmanager.entity.Task;
import com.shoyu.taskmanager.enums.TaskPriority;
import com.shoyu.taskmanager.enums.TaskStatus;
import com.shoyu.taskmanager.exception.InvalidTaskException;
import com.shoyu.taskmanager.exception.TaskNotFoundException;
import com.shoyu.taskmanager.repository.TaskRepository;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        if (task.getDueDate() != null && task.getDueDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTaskException("Due Date should not be a past date");
        } 

        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));

        if (updatedTask.getDueDate() != null && updatedTask.getDueDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTaskException("Due Date should not be a past date");
        }
        
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with the ID: " + id));

        taskRepository.delete(task);
            
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> getTasksDueBefore(LocalDateTime date) {
        return taskRepository.findByDueDateBefore(date);
    }
}
