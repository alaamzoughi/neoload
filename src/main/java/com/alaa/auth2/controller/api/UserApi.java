package com.alaa.auth2.controller.api;

import com.alaa.auth2.dto.ResponseDto;
import com.alaa.auth2.dto.RoleToUserForm;
import com.alaa.auth2.dto.UserDto;
import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequestMapping("/api")
@Api
public interface UserApi {

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers();

    @PostMapping("/user/save")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) ;
    @PostMapping("/role/addtouser")
    public ResponseDto saveRole(@RequestBody RoleToUserForm form) ;

    @PutMapping("/reset/pass/{id}")
    public ResponseDto resetPassword(@PathVariable Integer id, @RequestBody String password) throws NotFoundException ;

    @DeleteMapping("/user/delete/{id}")
    public ResponseDto deleteUser(@PathVariable Integer id) throws NotFoundException ;







}
