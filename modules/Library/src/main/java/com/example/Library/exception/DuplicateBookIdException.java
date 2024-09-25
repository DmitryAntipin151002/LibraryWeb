package com.example.Library.exception;


public class DuplicateBookIdException extends RuntimeException {
    public DuplicateBookIdException(String message) {
        super(message);
    }
}