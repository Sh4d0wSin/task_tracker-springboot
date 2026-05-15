package com.shadowSin.task_tracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shadowSin.task_tracker.dto.TaskRequest;
import com.shadowSin.task_tracker.dto.TaskResponse;
import com.shadowSin.task_tracker.mapper.TaskMapper;
import com.shadowSin.task_tracker.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks().stream().map(taskMapper::taskIntoResponse).collect(Collectors.toList()));

    }

   
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskMapper.taskIntoResponse(taskService.getTaskById(id)));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> postTask(@Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(201).body(taskMapper.taskIntoResponse(taskService.createTask(taskMapper.requestIntoTask(taskRequest))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok().body(taskMapper.taskIntoResponse(taskService.updateTask(taskMapper.requestIntoTask(taskRequest), id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    
}
