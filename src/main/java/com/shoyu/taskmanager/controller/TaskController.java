package com.shoyu.taskmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoyu.taskmanager.entity.Task;
import com.shoyu.taskmanager.enums.TaskPriority;
import com.shoyu.taskmanager.enums.TaskStatus;
import com.shoyu.taskmanager.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task task) {

        Task savedTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);

    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {

        List<Task> task = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        Task task = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/search")
        public ResponseEntity<List<Task>> searchTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueBefore
        ) {
            if (status != null) {
                return ResponseEntity.ok(taskService.getTasksByStatus(status));
            } else if (priority != null) {
                return ResponseEntity.ok(taskService.getTasksByPriority(priority));
            } else if (dueBefore != null) {
                return ResponseEntity.ok(taskService.getTasksDueBefore(dueBefore));
            } else {
                return ResponseEntity.badRequest().build();
            }
    }
    
    
}
