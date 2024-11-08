package com.example.todoapi.service;

import com.example.todoapi.model.Company;
import com.example.todoapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();  // In-memory storage of users
    private final AtomicLong idGenerator = new AtomicLong(1);  // Simulating auto-increment IDs

    public User createUser(User user) {
        // Assign a new ID
        user.setId(idGenerator.getAndIncrement());

        // Add the user to the company’s list of users
        if (user.getCompany() != null) {
            Company company = user.getCompany();
            if (company.getUsers() == null) {
                company.setUsers(new ArrayList<>());
            }
            company.getUsers().add(user);
        }

        // Add the user to the in-memory user list
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setRole(updatedUser.getRole());
            user.setCompany(updatedUser.getCompany());
        }
        return user;
    }

    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}