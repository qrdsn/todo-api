package com.example.todoapi.controller;

import com.example.todoapi.model.Company;
import com.example.todoapi.service.CompanyService;
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
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateCompany() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": null}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void shouldGetAllCompanies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/companies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldGetCompanyById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/companies/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void shouldReturnNotFoundForInvalidCompanyId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/companies/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundWhenDeletingNonexistentCompany() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/companies/{id}", 999))
                .andExpect(status().isNotFound());
    }
}