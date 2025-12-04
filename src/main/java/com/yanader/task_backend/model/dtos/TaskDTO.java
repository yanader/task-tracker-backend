package com.yanader.task_backend.model.dtos;

import com.yanader.task_backend.model.Status;
import com.yanader.task_backend.model.Task;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskDTO {

    private String title;
    private String description;
    private Status status;
    private LocalDateTime dueDateTime;

    public TaskDTO(String title, Status status, LocalDateTime dueDateTime) {
        this.title = title;
        this.status = status;
        this.dueDateTime = dueDateTime;
    }

    public TaskDTO(String title, String description, Status status, LocalDateTime dueDateTime) {
        this(title, status, dueDateTime);
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }
}
