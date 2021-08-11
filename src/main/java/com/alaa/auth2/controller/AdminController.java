package com.alaa.auth2.controller;


import com.alaa.auth2.controller.api.AdminApi;
import com.alaa.auth2.service.AdminService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController implements AdminApi {
    private AdminService adminService ;



}
