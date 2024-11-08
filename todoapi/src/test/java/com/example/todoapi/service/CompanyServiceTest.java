package com.example.todoapi.service;

import com.example.todoapi.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CompanyServiceTest {

    private CompanyService companyService;

    @BeforeEach
    public void setUp() {
        companyService = new CompanyService();
    }

    @Test
    public void shouldCreateCompany() {
        Company company = new Company();
        Company createdCompany = companyService.createCompany(company);
        assertNotNull(createdCompany.getId());
    }

    @Test
    public void shouldGetAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        assertNotNull(companies);
    }

    @Test
    public void shouldGetCompanyById() {
        Company company = companyService.createCompany(new Company());
        Company foundCompany = companyService.getCompanyById(company.getId());
        assertEquals(company.getId(), foundCompany.getId());
    }

    @Test
    public void shouldReturnNullForInvalidCompanyId() {
        Company foundCompany = companyService.getCompanyById(999L);
        assertNull(foundCompany);
    }

    @Test
    public void shouldDeleteCompanyById() {
        Company company = companyService.createCompany(new Company());
        boolean isDeleted = companyService.deleteCompany(company.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void shouldReturnFalseWhenDeletingNonexistentCompany() {
        boolean isDeleted = companyService.deleteCompany(999L);
        assertFalse(isDeleted);
    }
}
