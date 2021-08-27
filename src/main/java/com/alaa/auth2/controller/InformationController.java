package com.alaa.auth2.controller;

import com.alaa.auth2.controller.api.InformationApi;
import com.alaa.auth2.dto.ResponseDto;
import com.alaa.auth2.handlers.ErrorDto;
import com.alaa.auth2.service.InformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@Slf4j
public class InformationController implements InformationApi {
    @Autowired
    private InformationService informationService ;
   //



    //
    @Override
    public ResponseDto generateDoc(MultipartFile file, MultipartFile image,  String s ) {
        return informationService.generateDoc(file , image ,s) ;
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex)   {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        final HttpStatus NOTVALIDARGUMENT = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(NOTVALIDARGUMENT.value())
                .validationErrors(errors)
                .build();

        return new ResponseEntity<>(errorDto, NOTVALIDARGUMENT);
    }
}







