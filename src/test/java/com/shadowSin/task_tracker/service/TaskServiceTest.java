package com.shadowSin.task_tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shadowSin.task_tracker.model.Task;
import com.shadowSin.task_tracker.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;


    @Test
    public void testGetAllTasks() {
        Task dummy1 = new Task();
        List<Task> tasks = List.of(dummy1);


        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> foundTasks = taskService.getAllTasks();


        assertEquals(foundTasks, tasks);




    }
    
}
