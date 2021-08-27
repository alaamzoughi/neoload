package com.alaa.auth2.controller.api;

import com.alaa.auth2.dto.RoleDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api
@RequestMapping("/api")
public interface RoleApi {

    @PostMapping("/role/save")
    public ResponseEntity<RoleDto> saveRole(@Valid @RequestBody RoleDto roleDto) ;
}
