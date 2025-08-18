package com.junior.boletapp.app.exceptions;

public class NotFoundException extends AppException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String resource, String id) {
        super(resource + " not found with ID: " + id);
    }
}
