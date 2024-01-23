package com.leonardokazu.livraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {super("No records found for this id!");}

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
