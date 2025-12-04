package com.yanader.task_backend.mapper;

import com.yanader.task_backend.model.Task;
import com.yanader.task_backend.model.dtos.TaskDTO;

public class TaskMapper {

    public Task convertTaskDtoToTask(TaskDTO submittedTask){
        return new Task(
                submittedTask.getTitle(),
                submittedTask.getDescription(),
                submittedTask.getStatus(),
                submittedTask.getDueDateTime()
        );

    }

    public TaskDTO convertTaskToTaskDto(Task task){
        return new TaskDTO(
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDateTime()
        );
    }
}
