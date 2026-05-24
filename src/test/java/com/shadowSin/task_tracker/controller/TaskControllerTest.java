package com.shadowSin.task_tracker.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shadowSin.task_tracker.dto.TaskRequest;
import com.shadowSin.task_tracker.dto.TaskResponse;
import com.shadowSin.task_tracker.exception.TaskNotFoundException;
import com.shadowSin.task_tracker.mapper.TaskMapper;
import com.shadowSin.task_tracker.model.Task;
import com.shadowSin.task_tracker.service.TaskService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TaskControllerTest {

    @Autowired
    WebApplicationContext context;

    @MockitoBean
    TaskService taskService;

    @MockitoBean
    TaskMapper taskMapper;

    MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetTaskHappy() throws Exception {
        mockMvc.perform(get("/tasks")).andExpect(status().isOk());
    }

    @Test
    public void testGetTaskByIdSad() throws Exception {

        when(taskService.getTaskById(1L)).thenThrow(new TaskNotFoundException(1L));
        mockMvc.perform(get("/tasks/1")).andExpect(status().isNotFound());
    }

    @Test
    public void testGetTaskByIdHappy() throws Exception {
        Task dummy = new Task();
        dummy.setId(1L);
        when(taskService.getTaskById(1L)).thenReturn(dummy);
        TaskResponse dummyResponse = new TaskResponse(dummy.getId(),dummy.getTitle(), dummy.getDescription(), dummy.isCompleted());
        when(taskMapper.taskIntoResponse(dummy)).thenReturn(dummyResponse);
        mockMvc.perform(get("/tasks/1")).andExpect(status().isOk());
    }

    @Test
    public void testPostTaskHappy() throws Exception {
        Task dummy = new Task();
        dummy.setId(1L);
        TaskRequest dummyRequest = new TaskRequest(dummy.getTitle(), dummy.getDescription(),  dummy.isCompleted());
        TaskResponse dummyResponse = new TaskResponse(dummy.getId(),dummy.getTitle(), dummy.getDescription(), dummy.isCompleted());

        when(taskMapper.requestIntoTask(dummyRequest)).thenReturn(dummy);
        when(taskService.createTask(dummy)).thenReturn(dummy);
        when(taskMapper.taskIntoResponse(dummy)).thenReturn(dummyResponse);
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"test\",\"description\":\"test\",\"completed\":false}")).andExpect(status().isCreated());

    }

    




}
