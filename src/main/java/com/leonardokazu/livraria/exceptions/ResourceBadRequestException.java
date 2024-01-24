package com.leonardokazu.livraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException{

    public ResourceBadRequestException() { super("Bad Resquest error");
    }

    public ResourceBadRequestException(String message) {
        super(message);
    }
}
