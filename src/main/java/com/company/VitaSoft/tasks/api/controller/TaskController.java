package com.company.VitaSoft.tasks.api.controller;

import com.company.VitaSoft.common.service.UserDetailsImpl;
import com.company.VitaSoft.tasks.api.service.TaskService;
import com.company.VitaSoft.tasks.impl.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/submitted")
    public List<Task> getSubmittedTasks() {
        return taskService.getSubmittedTasks();
    }

    @PostMapping("/submitted/{id}")
    public void processTask(@PathVariable Long id, @RequestBody String setStatus) {
        taskService.processTask(id, setStatus);
    }

    @GetMapping("/my")
    public List<Task> getTasksByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl)auth.getPrincipal();

        return taskService.getTasksByUserId(userDetails.getUserId());
    }

    @PutMapping("/my/{id}")
    public void editDraftTask(@PathVariable Long id, @RequestBody String message) {
        taskService.editDraftTask(id, message);
    }

    @PostMapping("/my/{id}")
    public void submitTask(@PathVariable Long id) {
        taskService.submitTask(id);
    }

    @PostMapping("/new")
    public void createNewTask(@RequestBody String message) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl)auth.getPrincipal();

        taskService.createNewTask(userDetails.getUserId(), message);
    }

}
