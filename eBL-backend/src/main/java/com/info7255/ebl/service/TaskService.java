package com.info7255.ebl.service;

import com.info7255.ebl.dto.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(Task.builder().id("1").title("Title 1").description("Description for item 1").build());
        tasks.add(Task.builder().id("2").title("Title 2").description("Description for item 2").build());
        tasks.add(Task.builder().id("3").title("Title 3").description("Description for item 3").build());
        return tasks;
    }
}
