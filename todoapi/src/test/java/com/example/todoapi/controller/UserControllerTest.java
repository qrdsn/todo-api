package com.example.todoapi.controller;

import com.example.todoapi.model.User;
import com.example.todoapi.service.UserService;
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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void shouldCreateUser() throws Exception {
        String userJson = "{ \"name\": \"John Doe\", \"role\": \"COMPANY_ADMIN\" }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.role").value("COMPANY_ADMIN"));
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldGetUserById() throws Exception {
        User user = new User();
        user.setName("Alice");
        user.setRole(User.Role.SUPER_USER);
        User savedUser = userService.createUser(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", savedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedUser.getId()))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.role").value("SUPER_USER"));
    }

    @Test
    public void shouldReturnNotFoundForInvalidUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        User user = new User();
        user.setName("Charlie");
        user.setRole(User.Role.COMPANY_ADMIN);
        User savedUser = userService.createUser(user);

        String updatedUserJson = "{ \"name\": \"Charlie Updated\", \"role\": \"COMPANY_ADMIN\" }";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/{id}", savedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie Updated"))
                .andExpect(jsonPath("$.role").value("COMPANY_ADMIN"));
    }

    @Test
    public void shouldDeleteUserById() throws Exception {
        User user = new User();
        user.setName("User to Delete");
        user.setRole(User.Role.STANDARD);
        User savedUser = userService.createUser(user);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", savedUser.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnNotFoundWhenDeletingNonexistentUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
