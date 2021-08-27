package com.alaa.auth2.controller;

import com.alaa.auth2.controller.api.RoleApi;
import com.alaa.auth2.dto.RoleDto;
import com.alaa.auth2.service.RoleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController

@RequiredArgsConstructor
public class RoleController implements RoleApi {

    @Autowired
    private RoleService roleService ;


    @Override
    public ResponseEntity<RoleDto> saveRole( RoleDto roleDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(roleDto));
    }

}
