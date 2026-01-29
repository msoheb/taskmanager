package com.shoyu.taskmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.shoyu.taskmanager.service.UserService;
import com.shoyu.taskmanager.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
       User user =  userService.getUserById(id);
       return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<User> searchUser(@RequestParam(required = false) String email, @RequestParam(required = false) String username) {
        if(email != null) {
            User user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
                
        } else if(username != null) {
            User user =  userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
