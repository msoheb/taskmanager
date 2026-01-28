package com.shoyu.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shoyu.taskmanager.entity.User;
import com.shoyu.taskmanager.exception.UserAlreadyExistsException;
import com.shoyu.taskmanager.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUserSuccessfully() {
        User user = User.builder()
                .name("Test User")
                .username("testuser")
                .email("test@test.com")
                .password("password")
                .department("IT")
                .designation("Developer")
                .role("USER")
                .build();

        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        User savedUser = User.builder()
                .id(1L)
                .name("Test User")
                .username("testuser")
                .email("test@test.com")
                .password("password")
                .department("IT")
                .designation("Developer")
                .role("USER")
                .build();
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.createUser(user);
        
        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Test User", result.getName());
        assertEquals("test@test.com", result.getEmail());
        
        
    }

    @Test
    void shouldThrowExceptionWhenEmailExists() {
        User user = User.builder()
                .name("Test User")
                .username("testuser")
                .email("test@test.com")
                .password("password")
                .department("IT")
                .designation("Developer")
                .role("USER")
                .build();

        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.createUser(user);
        });
        
    }

    @Test
    void shouldThrowExceptionWhenUsernameExists() {
        User user = User.builder()
                .name("Test User")
                .username("testuser")
                .email("test@test.com")
                .password("password")
                .department("IT")
                .designation("Developer")
                .role("USER")
                .build();
        
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.createUser(user);
        });
    }
    
}
