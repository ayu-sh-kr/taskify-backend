package com.resotechs.controllers;

import com.resotechs.dtos.TaskDto;
import com.resotechs.services.business.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskControllers {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestParam("userId") long userId, @RequestBody TaskDto taskDto){
        return taskService.createTaskForUser(userId, taskDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTaskByUser(@RequestParam("userId") long userId){
        return taskService.getTasksFromUser(userId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTaskByUser(@RequestParam("userId") long userId, @RequestParam("taskId") long taskId){
        return taskService.deleteTaskByUser(userId, taskId);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateTaskByUser(@RequestParam("userId") long userId, @RequestParam("taskId") long taskId, @RequestBody TaskDto taskDto){
        return taskService.updateTaskById(taskId, userId, taskDto);
    }
}
