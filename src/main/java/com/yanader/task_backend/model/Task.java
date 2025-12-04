package com.yanader.task_backend.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Task {

    @NotNull
    private String title;

    private String description;

    @NotNull
    private Status status;

    @NotNull
    private LocalDateTime dueDateTime;

    public Task(String title, Status status, LocalDateTime dueDateTime) {
        this.title = title;
        this.status = status;
        this.dueDateTime = dueDateTime;
    }

    public Task(String title, String description, Status status, LocalDateTime dueDateTime) {
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


