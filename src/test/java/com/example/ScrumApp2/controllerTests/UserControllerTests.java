package com.example.ScrumApp2.controllerTests;

import com.example.ScrumApp2.controller.UserController;
import com.example.ScrumApp2.model.User;
import com.example.ScrumApp2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTests {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].username").value("user2"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("user1");

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("user1"));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("newUser");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newUser"));

        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User updatedUser = new User();
        updatedUser.setUsername("updatedUser");

        mockMvc.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateUser(eq(1L), any(User.class));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1L);
    }
}
