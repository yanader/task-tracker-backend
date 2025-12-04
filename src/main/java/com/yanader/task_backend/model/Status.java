package com.yanader.task_backend.model;

public enum Status {
    CREATED,
    IN_PROGRESS,
    COMPLETED;

    @Override
    public String toString() {
        return switch(this) {
            case CREATED -> "CREATED";
            case IN_PROGRESS -> "IN_PROGRESS";
            case COMPLETED -> "COMPLETED";
        };
    }
}
