package com.yanader.task_backend.service;

import com.yanader.task_backend.model.Task;
import com.yanader.task_backend.model.dtos.TaskDTO;

public interface TaskService {
    Task addTask(TaskDTO submittedTask);
}
