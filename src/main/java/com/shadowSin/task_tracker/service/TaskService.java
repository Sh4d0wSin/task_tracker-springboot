package com.shadowSin.task_tracker.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.shadowSin.task_tracker.exception.TaskNotFoundException;
import com.shadowSin.task_tracker.model.Task;
import com.shadowSin.task_tracker.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);

    }

    public Task updateTask(Task task, long id) {
        Task found = getTaskById(id);
        
        found.setTitle(task.getTitle());
        found.setDescription(task.getDescription());
        found.setCompleted(task.isCompleted());

        return taskRepository.save(found);
    }

    public void deleteTask(Long id) {
        Task found = getTaskById(id);
        taskRepository.deleteById(found.getId());

    }


    
}
