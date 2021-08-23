package com.alaa.auth2.service.implementation;


import com.alaa.auth2.model.Operation;
import com.alaa.auth2.repo.OperationRepository;

import com.alaa.auth2.service.OperationService;
import com.alaa.auth2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationRepository operationRepository ;
    @Autowired 
    private UserService userService ;

    @Override
    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public List<Operation> getOperations() {
        return operationRepository.findAll();
    }


}
