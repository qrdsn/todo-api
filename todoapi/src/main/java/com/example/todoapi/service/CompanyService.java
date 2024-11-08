package com.example.todoapi.service;

import com.example.todoapi.model.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CompanyService {

    private final List<Company> companies = new ArrayList<>();  // In-memory storage of companies
    private final AtomicLong idGenerator = new AtomicLong(1);  // Simulating auto-increment IDs

    public Company createCompany(Company company) {
        // Assign a new ID
        company.setId(idGenerator.getAndIncrement());

        // Add company to in-memory list
        companies.add(company);
        return company;
    }

    public List<Company> getAllCompanies() {
        return companies;
    }

    public Company getCompanyById(Long id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteCompany(Long id) {
        return companies.removeIf(company -> company.getId().equals(id));
    }
}