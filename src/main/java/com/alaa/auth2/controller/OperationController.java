package com.alaa.auth2.controller;

import com.alaa.auth2.controller.api.OperationApi;
import com.alaa.auth2.dto.UserDto;
import com.alaa.auth2.model.Operation;
import com.alaa.auth2.service.OperationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j

@Api
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class OperationController implements OperationApi {
    @Autowired
    private OperationService operationService ;

    @GetMapping("/operation")
    public ResponseEntity<List<Operation>> getOperations() {

        return ResponseEntity.ok().body(operationService.getOperations());
    }
    }



