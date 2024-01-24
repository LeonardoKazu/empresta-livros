package com.leonardokazu.livraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {super("Nenhum registro foi encontrado para este id!");}

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
