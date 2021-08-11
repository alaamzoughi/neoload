package com.alaa.auth2.handlers;

import com.alaa.auth2.exception.EntityNotFoundException;
import com.alaa.auth2.exception.ExistingEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExistingEntityHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExistingEntityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDto> handleException(ExistingEntityException exception , WebRequest webRequest) {
        final HttpStatus conflict = HttpStatus.CONFLICT;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(conflict.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, conflict);
    }

}
