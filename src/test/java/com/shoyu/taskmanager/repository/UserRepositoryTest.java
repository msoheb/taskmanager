package com.shoyu.taskmanager.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.shoyu.taskmanager.entity.User;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUser() {

        User user = User.builder()
                .name("Shoyu")
                .username("msoheb")
                .email("test@test.com")
                .password("password")
                .department("backend")
                .designation("Lead Software Engineer")
                .role("ADMIN")
                .build();

        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        assertNotNull(savedUser.getId());
        assertTrue(foundUser.isPresent());

        User retrievedUser = foundUser.get();
        assertEquals("Shoyu", retrievedUser.getName());
        assertEquals("msoheb", retrievedUser.getUsername());
        assertEquals("test@test.com", retrievedUser.getEmail());
        assertEquals("backend", retrievedUser.getDepartment());
        assertEquals("Lead Software Engineer", retrievedUser.getDesignation());
        assertEquals("ADMIN", retrievedUser.getRole());
    }
}
