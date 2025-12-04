package com.yanader.task_backend.controller;

import com.yanader.task_backend.model.Task;
import com.yanader.task_backend.model.dtos.TaskDTO;
import com.yanader.task_backend.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    @PostMapping(value ="/task")
    public ResponseEntity<Task> addNewAlbum(@RequestBody TaskDTO taskToAdd) {
        Task addedTask = taskService.addTask(taskToAdd);
        if (addedTask == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body");
        } else {
            return new ResponseEntity<>(addedTask, HttpStatus.CREATED);
        }
    }
}
