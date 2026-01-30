package com.shoyu.taskmanager.exception;

public class CategoryNotFoundException extends RuntimeException{
    
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
