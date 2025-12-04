package com.yanader.task_backend.repository;

import com.yanader.task_backend.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
