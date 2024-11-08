package com.example.todoapi.service;

import com.example.todoapi.model.Task;
import com.example.todoapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();  // In-memory storage of tasks
    private final AtomicLong idGenerator = new AtomicLong(1);  // To simulate auto-increment IDs

    public Task createTask(Task task) {
        // Set an ID for the new task and add it to the list
        task.setId(idGenerator.getAndIncrement());
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);  // Return null if task is not found
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
        }
        return task;
    }

    public boolean deleteTask(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}
