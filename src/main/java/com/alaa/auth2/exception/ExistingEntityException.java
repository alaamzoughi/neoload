package com.alaa.auth2.exception;

public class ExistingEntityException extends RuntimeException{
    public ExistingEntityException(String message) {
        super(message);
    }

    public ExistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
