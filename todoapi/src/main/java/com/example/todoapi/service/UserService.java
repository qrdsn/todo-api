package com.example.todoapi.service;

import com.example.todoapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();  // In-memory storage of users
    private final AtomicLong idGenerator = new AtomicLong(1);  // To simulate auto-increment IDs

    // Create a new user
    public User createUser(User user) {
        user.setId(idGenerator.getAndIncrement());
        users.add(user);
        return user;
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return users;
    }

    // Retrieve a user by ID
    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);  // Return null if user is not found
    }

    // Update an existing user
    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setRole(updatedUser.getRole());
            return user;
        }
        return null;
    }

    // Delete a user by ID
    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}