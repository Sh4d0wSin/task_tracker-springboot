package com.shadowSin.task_tracker.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shadowSin.task_tracker.model.Task;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

  

    @Test
    public void saveTaskReturnsIdNotNull() {
        Task dummy = new Task();
        dummy.setTitle("idc");
        dummy.setDescription("no");
        dummy.setCompleted(false);
        Task saved = taskRepository.save(dummy);

        assertNotNull(saved.getId());
    }
    
}
