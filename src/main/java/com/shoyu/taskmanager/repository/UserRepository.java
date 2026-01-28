package com.shoyu.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoyu.taskmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
