package com.alaa.auth2.service.implementation;


import com.alaa.auth2.repo.OperationRepository;

import com.alaa.auth2.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {
    private OperationRepository operationRepository ;
}
