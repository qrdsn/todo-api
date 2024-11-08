package com.example.todoapi.service;

import com.example.todoapi.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void shouldGetAllUsers() {
        User user1 = new User();
        user1.setName("User 1");
        user1.setRole(User.Role.SUPER_USER);
        userService.createUser(user1);

        User user2 = new User();
        user2.setName("User 2");
        user2.setRole(User.Role.COMPANY_ADMIN);
        userService.createUser(user2);

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void shouldGetUserById() {
        User user = new User();
        user.setName("User to Find");
        user.setRole(User.Role.STANDARD);
        User savedUser = userService.createUser(user);

        User foundUser = userService.getUserById(savedUser.getId());
        assertNotNull(foundUser);
        assertEquals(savedUser.getId(), foundUser.getId());
    }

    @Test
    public void shouldReturnNullForInvalidUserId() {
        User foundUser = userService.getUserById(999L);
        assertNull(foundUser);
    }

    @Test
    public void shouldReturnNullWhenUpdatingNonexistentUser() {
        User updatedUser = new User();
        updatedUser.setName("Nonexistent");
        updatedUser.setRole(User.Role.COMPANY_ADMIN);

        User result = userService.updateUser(999L, updatedUser);
        assertNull(result);
    }

    @Test
    public void shouldDeleteUserById() {
        User user = new User();
        user.setName("User to Delete");
        user.setRole(User.Role.SUPER_USER);
        User savedUser = userService.createUser(user);

        boolean isDeleted = userService.deleteUser(savedUser.getId());
        assertTrue(isDeleted);
        assertNull(userService.getUserById(savedUser.getId()));
    }

    @Test
    public void shouldReturnFalseWhenDeletingNonexistentUser() {
        boolean isDeleted = userService.deleteUser(999L);
        assertFalse(isDeleted);
    }
}