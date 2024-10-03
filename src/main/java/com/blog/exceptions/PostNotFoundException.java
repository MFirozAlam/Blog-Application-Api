package com.blog.exceptions;
 // Use your own package structure.

public class PostNotFoundException extends RuntimeException {

    // Default constructor
    public PostNotFoundException() {
        super("Post not found");
    }

    // Constructor that allows passing a custom message
    public PostNotFoundException(String message) {
        super(message);
    }

    // Constructor that allows passing a cause
    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause
    public PostNotFoundException(Throwable cause) {
        super(cause);
    }
}

