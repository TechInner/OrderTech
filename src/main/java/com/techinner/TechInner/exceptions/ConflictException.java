package com.techinner.TechInner.exceptions;

//Informações duplicadas
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }

}
