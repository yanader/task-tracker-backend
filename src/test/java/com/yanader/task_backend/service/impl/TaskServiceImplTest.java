package com.yanader.task_backend.service.impl;

import com.yanader.task_backend.model.Status;
import com.yanader.task_backend.model.Task;
import com.yanader.task_backend.model.dtos.TaskDTO;
import com.yanader.task_backend.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TaskServiceImplTest {

    @Mock
    TaskRepository mockTaskRepository;

    @InjectMocks
    TaskServiceImpl service;

    @Test
    void shouldReturnTaskWithDescription() {
        LocalDateTime dueDateTime = LocalDateTime.of(2025, 12, 1, 17, 0, 0);
        Task task = new Task("Emails","Clean up inbox and catch up on emails", Status.CREATED, dueDateTime);

        when(mockTaskRepository.save(ArgumentMatchers.<Task>any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Task returnedTask = service.addTask(new TaskDTO("Emails", "Clean up inbox and catch up on emails", Status.CREATED, dueDateTime));

        assertEquals("Emails", returnedTask.getTitle());
        assertEquals("Clean up inbox and catch up on emails", returnedTask.getDescription());
        assertEquals(Status.CREATED, returnedTask.getStatus());
        assertEquals(LocalDateTime.of(2025, 12, 1, 17, 0, 0), returnedTask.getDueDateTime());
    }

    @Test
    void shouldReturnTaskWithoutDescription() {
        LocalDateTime dueDateTime = LocalDateTime.of(2025, 12, 1, 17, 0, 0);
        Task task = new Task("Write Report", Status.CREATED, dueDateTime);

        when(mockTaskRepository.save(ArgumentMatchers.<Task>any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Task returnedTask = service.addTask(new TaskDTO("Write Report", Status.CREATED, dueDateTime));

        assertEquals("Write Report", returnedTask.getTitle());
        assertNull(returnedTask.getDescription());
        assertEquals(Status.CREATED, returnedTask.getStatus());
        assertEquals(LocalDateTime.of(2025, 12, 1, 17, 0, 0), returnedTask.getDueDateTime());
    }

    @Test
    void shouldRejectTaskWithMissingData() {
        LocalDateTime dueDateTime = LocalDateTime.of(2025, 12, 1, 17, 0, 0);
        TaskDTO taskMissingTitle = new TaskDTO(null, Status.CREATED, dueDateTime);
        TaskDTO taskMissingStatus = new TaskDTO("Emails", null, dueDateTime);
        TaskDTO taskMissingDueDateTime = new TaskDTO("Emails", Status.CREATED, null);

        assertNull(service.addTask(taskMissingTitle));
        assertNull(service.addTask(taskMissingStatus));
        assertNull(service.addTask(taskMissingDueDateTime));
    }
}