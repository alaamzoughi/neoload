package com.alaa.auth2.service.implementation;


import com.alaa.auth2.repo.AdminRepository;
import com.alaa.auth2.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository ;
}
