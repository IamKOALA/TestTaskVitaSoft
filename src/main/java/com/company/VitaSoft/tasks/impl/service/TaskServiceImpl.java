package com.company.VitaSoft.tasks.impl.service;

import com.company.VitaSoft.tasks.api.service.TaskService;
import com.company.VitaSoft.tasks.impl.entity.Task;
import com.company.VitaSoft.tasks.impl.enums.ETaskStatus;
import com.company.VitaSoft.tasks.impl.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No task found")
class TaskNotFoundException extends RuntimeException {
}

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Invalid status to set")
class IncorrectStatusException extends RuntimeException {
}

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getSubmittedTasks() {
        List<Task> taskList = taskRepository.findSubmitted();

        for (Task task : taskList) {
            String msg = task.getMessage();
            StringBuilder formatMessageBuilder = new StringBuilder();

            for (int i = 0; i < msg.length() - 1; i++) {
                formatMessageBuilder.append(msg.charAt(i));
                formatMessageBuilder.append('-');
            }
            formatMessageBuilder.append(msg.charAt(msg.length() - 1));

            task.setMessage(formatMessageBuilder.toString());
        }
        return taskList;
    }

    @Override
    public void processTask(Long id, String setStatus) {
        if (!setStatus.equals(ETaskStatus.ACCEPTED.toString()) && !setStatus.equals(ETaskStatus.DECLINED.toString())) {
            throw new IncorrectStatusException();
        }

        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            task.get().setStatus(ETaskStatus.valueOf(setStatus));
            taskRepository.save(task.get());
        } else {
            throw new TaskNotFoundException();
        }
    }

    @Override
    public List<Task> getTasksByUserId(Long id) {
        return taskRepository.findByUserId(id);
    }

    @Override
    public void editDraftTask(Long id, String message) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            if(task.get().getStatus() != ETaskStatus.DRAFT) {
                throw new IncorrectStatusException();
            }
            task.get().setMessage(message);
            taskRepository.save(task.get());
        } else {
            throw new TaskNotFoundException();
        }
    }

    @Override
    public void submitTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            task.get().setStatus(ETaskStatus.SUBMITTED);
            taskRepository.save(task.get());
        } else {
            throw new TaskNotFoundException();
        }
    }

    @Override
    public void createNewTask(Long id, String message) {
        Task task = new Task(null, id, ETaskStatus.DRAFT, message);
        taskRepository.save(task);
    }
}
