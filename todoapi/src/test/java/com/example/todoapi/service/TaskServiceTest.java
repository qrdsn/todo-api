package com.example.todoapi.service;

import com.example.todoapi.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    public void shouldCreateTask() {
        Task task = new Task();
        task.setDescription("New Task");
        Task createdTask = taskService.createTask(task);
        assertNotNull(createdTask.getId());
    }

    @Test
    public void shouldGetAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        assertNotNull(tasks);
    }

    @Test
    public void shouldGetTaskById() {
        Task task = taskService.createTask(new Task());
        Task foundTask = taskService.getTaskById(task.getId());
        assertEquals(task.getId(), foundTask.getId());
    }

    @Test
    public void shouldReturnNullForInvalidTaskId() {
        Task foundTask = taskService.getTaskById(999L);
        assertNull(foundTask);
    }

    @Test
    public void shouldDeleteTaskById() {
        Task task = taskService.createTask(new Task());
        boolean isDeleted = taskService.deleteTask(task.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void shouldReturnFalseWhenDeletingNonexistentTask() {
        boolean isDeleted = taskService.deleteTask(999L);
        assertFalse(isDeleted);
    }
}

