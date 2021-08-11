package com.alaa.auth2.controller;


import com.alaa.auth2.dto.ResponseDto;
import com.alaa.auth2.dto.RoleToUserForm;
import com.alaa.auth2.dto.UserDto;
import com.alaa.auth2.handlers.ErrorDto;
import com.alaa.auth2.service.UserService;
import io.swagger.annotations.Api;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(userDto));
    }

    @PostMapping("/role/addtouser")
    public ResponseDto saveRole(@RequestBody RoleToUserForm form) {
        return (ResponseDto) userService.addRoleToUser(form.getUsername(), form.getRoleName());

    }

    @PutMapping("/reset/pass/{id}")
    public ResponseDto resetPassword(@PathVariable Integer id, @RequestBody String password) throws NotFoundException {
       return userService.resetPassword(id , password) ;

    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseDto deleteUser(@PathVariable Integer id) throws NotFoundException {
        return userService.delete(id) ;


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



