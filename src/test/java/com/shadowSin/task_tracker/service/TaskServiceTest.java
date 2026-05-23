package com.shadowSin.task_tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shadowSin.task_tracker.exception.TaskNotFoundException;
import com.shadowSin.task_tracker.model.Task;
import com.shadowSin.task_tracker.repository.TaskRepository;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;


    @Test //happy case
    public void testGetAllTasksHappy() {
        Task dummy1 = new Task();
        List<Task> tasks = List.of(dummy1);


        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> foundTasks = taskService.getAllTasks();


        assertEquals(foundTasks, tasks);

    }

    @Test //sad case
    public void testGetTaskByIdSad() {

        when(taskRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L));
    }

    
    @Test //happy case
    public void testGetTaskByIdHappy() {

        Task dummy1 = new Task();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(dummy1));


        assertEquals(dummy1, taskService.getTaskById(1L));
    }


    @Test //sad case
    public void testDeleteTaskSad() {

        when(taskRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(1L));
    }


    @Test //happy case
    public void testDeleteTaskHappy() {

        Task dummy1 = new Task();

        dummy1.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(dummy1));

        taskService.deleteTask(1L);

        verify(taskRepository).deleteById(1L);




        
    }


    @Test //happy case
    public void testUpdateTaskByIdHappy() {

        Task dummy1 = new Task();
        Task updatedTask= new Task();

        updatedTask.setCompleted(true);
        updatedTask.setDescription("updated");
        updatedTask.setTitle("Updated");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(dummy1));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

  

        assertEquals(updatedTask, taskService.updateTask(updatedTask,1L));
    }



    @Test //sad case
    public void testUpdateTaskSad() {
        Task task = new Task();
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(task,1L));
    }


    @Test //happy case
    public void testCreateTask() {
        Task task = new Task();
        when(taskRepository.save(any(Task.class))).thenReturn(task);


        assertEquals(task, taskService.createTask(task));
    }







    
    
}
