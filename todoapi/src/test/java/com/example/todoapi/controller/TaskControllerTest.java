package com.example.todoapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"description\": \"New Task\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetAllTasks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldReturnNotFoundForInvalidTaskId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteTaskById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnNotFoundWhenDeletingNonexistentTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/{id}", 999))
                .andExpect(status().isNotFound());
    }

}
