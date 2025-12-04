package com.yanader.task_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yanader.task_backend.model.Status;
import com.yanader.task_backend.model.Task;
import com.yanader.task_backend.model.dtos.TaskDTO;
import com.yanader.task_backend.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    TaskServiceImpl mockService;

    @InjectMocks
    TaskController controller;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void postTaskPostsSuccessfullyWithFullBody() throws Exception {
        LocalDateTime dueDate = LocalDateTime.of(2025, 12, 1, 17, 00);
        TaskDTO taskToPost = new TaskDTO("Emails", "Catch up on emails", Status.CREATED, dueDate);
        Task addedTask = new Task("Emails", "Catch up on emails", Status.CREATED, dueDate);

        when(mockService.addTask(any(TaskDTO.class))).thenReturn(addedTask);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/task")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(taskToPost)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Emails"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Catch up on emails"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("CREATED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDateTime").value("2025-12-01T17:00:00"));
    }

    @Test
    void postTaskPostsSuccessfullyWithMissingDescription() throws Exception {
        LocalDateTime dueDate = LocalDateTime.of(2025, 12, 1, 17, 00);
        TaskDTO taskToPost = new TaskDTO("Emails", Status.CREATED, dueDate);
        Task addedTask = new Task("Emails", Status.CREATED, dueDate);

        when(mockService.addTask(any(TaskDTO.class))).thenReturn(addedTask);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/task")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(taskToPost)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Emails"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("CREATED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDateTime").value("2025-12-01T17:00:00"));
    }

    @Test
    void putTaskFailsWithMalformedBody() throws Exception {
        LocalDateTime dueDate = LocalDateTime.of(2025, 12, 1, 17, 00);
        TaskDTO taskToPost = new TaskDTO(null, "Catch up on emails", Status.CREATED, dueDate);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/task")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(taskToPost)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}