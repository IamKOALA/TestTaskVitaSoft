package com.company.VitaSoft.tasks.api.service;

import com.company.VitaSoft.tasks.impl.entity.Task;

import java.util.List;

public interface TaskService {
    public List<Task> getSubmittedTasks();

    public void processTask(Long id, String setStatus);

    public List<Task> getTasksByUserId(Long id);

    public void editDraftTask(Long id, String message);

    public void submitTask(Long id);

    public void createNewTask(Long id, String message);
}
