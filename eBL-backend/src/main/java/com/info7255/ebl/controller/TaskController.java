package com.info7255.ebl.controller;

import com.info7255.ebl.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/")
@AllArgsConstructor
public class TaskController {
    TaskService taskService;

    @GetMapping(value = "tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTasks() {
        return ResponseEntity.ok().body(
                taskService.getTasks());
    }
}
