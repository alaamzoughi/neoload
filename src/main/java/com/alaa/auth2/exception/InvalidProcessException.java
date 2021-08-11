package com.alaa.auth2.exception;

public class InvalidProcessException extends RuntimeException{
    public InvalidProcessException(String message) {
        super(message);
    }

    public InvalidProcessException(String message, Throwable cause) {
        super(message, cause);
    }


}
