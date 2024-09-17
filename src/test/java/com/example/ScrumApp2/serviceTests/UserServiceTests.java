package com.example.ScrumApp2.serviceTests;

import com.example.ScrumApp2.model.User;
import com.example.ScrumApp2.repository.IUserRepository;
import com.example.ScrumApp2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTests {
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {

        User user1 = new User();
        user1.setUsername("Ana");
        user1.setPassword("donboscof5");
        user1.setEmail("ana@example.com");

        User user2 = new User();
        user2.setUsername("Abraham");
        user2.setPassword("donboscof5");
        user2.setEmail("abraham@example.com");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Ana", result.get(0).getUsername());
        assertEquals("Abraham", result.get(1).getUsername());
    }

    @Test
    void testGetUserById() {

        User user = new User();
        user.setUsername("Norbert");
        user.setPassword("donboscof5");
        user.setEmail("norbert@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("Norbert", result.getUsername());
    }

    @Test
    void testCreateUser() {

        User user = new User();
        user.setUsername("Eva");
        user.setPassword("donboscof5");
        user.setEmail("eva@example.com");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("Eva", result.getUsername());
    }

    @Test
    void testUpdateUser() {

        User existingUser = new User();
        existingUser.setUsername("Ana");
        existingUser.setPassword("donboscof5");
        existingUser.setEmail("ana@example.com");

        User updatedUser = new User();
        updatedUser.setUsername("AnaUpdated");
        updatedUser.setPassword("newpassword");
        updatedUser.setEmail("anaupdated@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("AnaUpdated", result.getUsername());
        assertEquals("newpassword", result.getPassword());
        assertEquals("anaupdated@example.com", result.getEmail());
    }

    @Test
    void testDeleteUser() {

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
