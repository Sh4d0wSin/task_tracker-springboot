package com.shadowSin.task_tracker.exception;


public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task with " + id + " not found");
    }
    
}
