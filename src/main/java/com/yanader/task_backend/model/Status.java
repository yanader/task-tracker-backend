package com.yanader.task_backend.model;

public enum Status {
    CREATED,
    IN_PROGRESS,
    COMPLETED;

    @Override
    public String toString() {
        return switch(this) {
            case CREATED -> "Created";
            case IN_PROGRESS -> "In Progress";
            case COMPLETED -> "Completed";
        };
    }
}
