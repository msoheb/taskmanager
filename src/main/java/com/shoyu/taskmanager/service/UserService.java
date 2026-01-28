package com.shoyu.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoyu.taskmanager.entity.User;
import com.shoyu.taskmanager.exception.UserAlreadyExistsException;
import com.shoyu.taskmanager.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        Optional<User> foundByEmail = userRepository.findByEmail(user.getEmail());
        if(foundByEmail.isPresent()) throw new UserAlreadyExistsException("Email already exist, try to login with your email");

        Optional<User> foundByUsername = userRepository.findByUsername(user.getUsername());
        if(foundByUsername.isPresent()) throw new UserAlreadyExistsException("Username already exist, try to login with your username");

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
