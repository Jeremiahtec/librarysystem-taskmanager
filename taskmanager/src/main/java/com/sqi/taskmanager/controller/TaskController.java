package com.sqi.taskmanager.controller;


import com.sqi.taskmanager.model.Task;
import com.sqi.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET: Retrieve all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // POST: Create a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // PUT: Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE: Remove a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

   // @Value("${school.name}")

//    private String SchoolName;
//    @Value("${school.address}")
//    private String SchoolAddress;
//    @Value("${app.version}")
//    private String AppVersion;
//    @Value("${api-key}")
//    private String ApiKey;
//
//    @GetMapping("/info")
//    public String info() {
//        return "Welcome to " + SchoolName + ".\n" +
//               "Located at " + SchoolAddress + ".\n" +
//                "App Version " + AppVersion + ".\n" +
//                "Api key " + ApiKey + ".\n";
//    }
}