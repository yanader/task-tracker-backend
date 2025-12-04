package com.yanader.task_backend.service.impl;

import com.yanader.task_backend.mapper.TaskMapper;
import com.yanader.task_backend.model.Task;
import com.yanader.task_backend.model.dtos.TaskDTO;
import com.yanader.task_backend.repository.TaskRepository;
import com.yanader.task_backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskMapper mapper;

    @Override
    public Task addTask(TaskDTO submittedTask) {
        return validTaskDTO(submittedTask) ? taskRepository.save(mapper.convertTaskDtoToTask(submittedTask)) : null;
    }

    private boolean validTaskDTO(TaskDTO submittedTask) {
        return (submittedTask.getTitle() != null) && submittedTask.getStatus() != null && submittedTask.getDueDateTime() != null;
    }
}
