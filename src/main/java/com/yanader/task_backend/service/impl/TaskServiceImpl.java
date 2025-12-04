package com.yanader.task_backend.service.impl;

import com.yanader.task_backend.model.Task;
import com.yanader.task_backend.model.dtos.TaskDTO;
import com.yanader.task_backend.repository.TaskRepository;
import com.yanader.task_backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task addTask(TaskDTO submittedTask) {
        return validTaskDTO(submittedTask) ? taskRepository.save(submittedTask.convertTaskDtoToTask()) : null;
    }

    private boolean validTaskDTO(TaskDTO submittedTask) {
        return (submittedTask.getTitle() != null) && submittedTask.getStatus() != null && submittedTask.getDueDateTime() != null;
    }
}
