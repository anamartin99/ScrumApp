package com.example.ScrumApp2.controllerTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.ScrumApp2.controller.UserController;
import com.example.ScrumApp2.model.ERole;
import com.example.ScrumApp2.model.User;
import com.example.ScrumApp2.service.UserService;

public class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setPassword("password123");
        user1.setEmail("user1@example.com");
        user1.setRole(ERole.USER);

        user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setPassword("password123");
        user2.setEmail("user2@example.com");
        user2.setRole(ERole.USER);
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = new ArrayList<>(Arrays.asList(user1, user2));

        when(userService.getAllUsers()).thenReturn(userList);

        List<User> result = userController.getAllUser();
        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        when(userService.getUserById(1L)).thenReturn(user1);

        User result = userController.getUserById(1L);

        assertEquals(user1.getId(), result.getId());
        assertEquals(user1.getUsername(), result.getUsername());
        assertEquals(user1.getEmail(), result.getEmail());
        assertEquals(user1.getRole(), result.getRole());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testCreateUser() {
        when(userService.createUser(any(User.class))).thenReturn(user1);

        User result = userController.addUserService(user1);

        assertEquals(user1.getId(), result.getId());
        assertEquals(user1.getUsername(), result.getUsername());
        assertEquals(user1.getEmail(), result.getEmail());
        assertEquals(user1.getRole(), result.getRole());
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void testUpdateUser() {

        userController.updateUser(user1, 1L);

        verify(userService, times(1)).updateUser(eq(1L), any(User.class));
    }

    @Test
    void testDeleteUser() {

        userController.deleteUser(1L);

        verify(userService, times(1)).deleteUser(1L);
    }
}
